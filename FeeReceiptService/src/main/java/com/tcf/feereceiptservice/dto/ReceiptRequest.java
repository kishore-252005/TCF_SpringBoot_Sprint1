package com.tcf.feereceiptservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class ReceiptRequest {

    @NotNull(message = "Payment ID is required")
    private Long paymentId;

    private Long studentId;
    private Long allocationId;
    private BigDecimal totalFee;
    private BigDecimal previousPaid;
    private BigDecimal currentPaid;
    private String paymentMode;
    private String transactionId;
    private String paymentStatus;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Long allocationId) {
        this.allocationId = allocationId;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPreviousPaid() {
        return previousPaid;
    }

    public void setPreviousPaid(BigDecimal previousPaid) {
        this.previousPaid = previousPaid;
    }

    public BigDecimal getCurrentPaid() {
        return currentPaid;
    }

    public void setCurrentPaid(BigDecimal currentPaid) {
        this.currentPaid = currentPaid;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}