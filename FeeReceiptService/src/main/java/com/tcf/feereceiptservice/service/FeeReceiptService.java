
package com.tcf.feereceiptservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcf.feereceiptservice.dto.ReceiptRequest;
import com.tcf.feereceiptservice.dto.ReceiptUpdateRequest;
import com.tcf.feereceiptservice.entity.FeeReceipt;
import com.tcf.feereceiptservice.exception.DuplicateReceiptException;
import com.tcf.feereceiptservice.exception.PaymentNotSuccessfulException;
import com.tcf.feereceiptservice.exception.ResourceNotFoundException;
import com.tcf.feereceiptservice.repository.FeeReceiptRepository;

@Service
public class FeeReceiptService implements IFeeReceiptService {

    private final FeeReceiptRepository repository;

    public FeeReceiptService(FeeReceiptRepository repository) {
        this.repository = repository;
    }

    // POST - Generate Receipt
    @Override
    @Transactional
    public FeeReceipt generateReceipt(ReceiptRequest request) {

        Long paymentId = request.getPaymentId();

        if (repository.existsByPaymentId(paymentId)) {
            throw new DuplicateReceiptException(
                "Receipt already generated for payment ID: " + paymentId
            );
        }

        if (!"SUCCESS".equalsIgnoreCase(request.getPaymentStatus())) {
            throw new PaymentNotSuccessfulException(
                "Payment is not successful"
            );
        }

        BigDecimal totalFee = request.getTotalFee();
        BigDecimal previousPaid = request.getPreviousPaid();
        BigDecimal currentPaid = request.getCurrentPaid();

        BigDecimal pendingAmount = totalFee.subtract(
            previousPaid.add(currentPaid)
        );

        if (pendingAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Total paid amount cannot exceed total fee"
            );
        }

        FeeReceipt receipt = new FeeReceipt();

        receipt.setReceiptNumber(generateReceiptNumber());
        receipt.setPaymentId(paymentId);
        receipt.setStudentId(request.getStudentId());
        receipt.setAllocationId(request.getAllocationId());
        receipt.setTotalFee(totalFee);
        receipt.setPreviousPaid(previousPaid);
        receipt.setCurrentPaid(currentPaid);
        receipt.setPendingAmount(pendingAmount);
        receipt.setPaymentMode(request.getPaymentMode());
        receipt.setTransactionId(request.getTransactionId());
        receipt.setReceiptDate(LocalDateTime.now());
        receipt.setReceiptStatus("ACTIVE");

        return repository.save(receipt);
    }

    // Generate Receipt Number
    private String generateReceiptNumber() {

        long count = repository.count() + 1;

        return String.format("FEE-2026-%04d", count);
    }

    // GET - All Receipts
    @Override
    public List<FeeReceipt> getAllReceipts() {
        return repository.findAll();
    }

    // GET - Receipt By ID
    @Override
    public FeeReceipt getReceiptById(Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Receipt not found with ID: " + id
            ));
    }

    // GET - Receipt By Number
    @Override
    public FeeReceipt getReceiptByNumber(String receiptNumber) {

        return repository.findByReceiptNumber(receiptNumber)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Receipt not found: " + receiptNumber
            ));
    }

    // GET - Receipts By Student ID
    @Override
    public List<FeeReceipt> getReceiptsByStudent(Long studentId) {

        return repository.findByStudentId(studentId);
    }

    // PUT - Update Receipt
    @Override
    @Transactional
    public FeeReceipt updateReceipt(
            Long id, ReceiptUpdateRequest request) {

        FeeReceipt receipt = getReceiptById(id);

        if ("CANCELLED".equalsIgnoreCase(receipt.getReceiptStatus())) {
            throw new IllegalStateException(
                "Cancelled receipts cannot be updated"
            );
        }

        receipt.setPaymentMode(request.getPaymentMode());
        receipt.setTransactionId(request.getTransactionId());

        return repository.save(receipt);
    }

    // DELETE - Cancel Receipt
    @Override
    @Transactional
    public void deleteReceipt(Long id) {

        FeeReceipt receipt = getReceiptById(id);

        if ("CANCELLED".equalsIgnoreCase(receipt.getReceiptStatus())) {
            throw new IllegalStateException(
                "Receipt is already cancelled"
            );
        }

        receipt.setReceiptStatus("CANCELLED");

        repository.save(receipt);
    }
}
