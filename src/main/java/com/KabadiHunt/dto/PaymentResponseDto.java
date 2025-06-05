package com.KabadiHunt.dto;

import java.math.BigDecimal;

public class PaymentResponseDto {
    private String paymentId;    // Razorpay order ID
    private BigDecimal amount;  // Amount in rupees
    private String currency;
    private String apiKey;      // Razorpay public key

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
