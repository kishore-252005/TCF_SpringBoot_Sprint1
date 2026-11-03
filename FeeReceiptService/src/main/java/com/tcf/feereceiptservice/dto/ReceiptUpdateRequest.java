
package com.tcf.feereceiptservice.dto;

import jakarta.validation.constraints.NotBlank;

public class ReceiptUpdateRequest {

    @NotBlank(message = "Payment mode is required")
    private String paymentMode;

    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

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
}
