package com.KabadiHunt.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {
    private BigDecimal amount;
    private String Currency="INR";

    public String getCurrency() {
        return Currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}