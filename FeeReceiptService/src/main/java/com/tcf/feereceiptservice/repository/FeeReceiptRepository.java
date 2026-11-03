package com.tcf.feereceiptservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcf.feereceiptservice.entity.FeeReceipt;

@Repository
public interface FeeReceiptRepository extends JpaRepository<FeeReceipt, Long> {

    Optional<FeeReceipt> findByReceiptNumber(String receiptNumber);

    Optional<FeeReceipt> findByPaymentId(Long paymentId);

    List<FeeReceipt> findByStudentId(Long studentId);

    boolean existsByPaymentId(Long paymentId);

}