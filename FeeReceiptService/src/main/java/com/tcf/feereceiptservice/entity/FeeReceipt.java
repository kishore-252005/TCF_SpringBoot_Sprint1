package com.tcf.feereceiptservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "fee_receipts")
@Data
public class FeeReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    private String receiptNumber;

    private Long paymentId;

    private Long studentId;

    private Long allocationId;

    private BigDecimal totalFee;

    private BigDecimal previousPaid;

    private BigDecimal currentPaid;

    private BigDecimal pendingAmount;

    private String paymentMode;

    private String transactionId;

    private LocalDateTime receiptDate;

    private String receiptStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "feeReceipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> receiptItems = new ArrayList<>();

}