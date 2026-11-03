package com.tcf.feereceiptservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiptResponse {

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

}