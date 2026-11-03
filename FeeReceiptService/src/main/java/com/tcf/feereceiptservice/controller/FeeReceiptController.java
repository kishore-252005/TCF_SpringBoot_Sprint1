
package com.tcf.feereceiptservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcf.feereceiptservice.dto.ReceiptRequest;
import com.tcf.feereceiptservice.dto.ReceiptUpdateRequest;
import com.tcf.feereceiptservice.entity.FeeReceipt;
import com.tcf.feereceiptservice.service.IFeeReceiptService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receipts")
public class FeeReceiptController {

    private final IFeeReceiptService service;

    public FeeReceiptController(IFeeReceiptService service) {
        this.service = service;
    }

    // POST - Generate Receipt
    @PostMapping("/generate")
    public FeeReceipt generateReceipt(
            @Valid @RequestBody ReceiptRequest request) {

        return service.generateReceipt(request);
    }

    // GET - All Receipts
    @GetMapping
    public List<FeeReceipt> getAllReceipts() {
        return service.getAllReceipts();
    }

    // GET - Receipt By ID
    @GetMapping("/{id}")
    public FeeReceipt getReceiptById(@PathVariable Long id) {
        return service.getReceiptById(id);
    }

    // GET - Receipt By Number
    @GetMapping("/number/{receiptNumber}")
    public FeeReceipt getReceiptByNumber(
            @PathVariable String receiptNumber) {

        return service.getReceiptByNumber(receiptNumber);
    }

    // GET - Receipts By Student ID
    @GetMapping("/student/{studentId}")
    public List<FeeReceipt> getReceiptsByStudent(
            @PathVariable Long studentId) {

        return service.getReceiptsByStudent(studentId);
    }

    // PUT - Update Receipt
    @PutMapping("/{id}")
    public FeeReceipt updateReceipt(
            @PathVariable Long id,
            @Valid @RequestBody ReceiptUpdateRequest request) {

        return service.updateReceipt(id, request);
    }

    // DELETE - Cancel Receipt
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReceipt(
            @PathVariable Long id) {

        service.deleteReceipt(id);

        return ResponseEntity.ok("Receipt cancelled successfully");
    }
}
