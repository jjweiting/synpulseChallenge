package com.synpulse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequest {
    @JsonProperty("payerIBAN")
    public String payerIBAN;
    @JsonProperty("payeeIBAN")
    public String payeeIBAN;
    @JsonProperty("amount")
    public Float amount;

    /**
     * @param payerIBAN
     * 
     * @param payeeIBAN
     * 
     * @param amount
     */
    public TransactionRequest(String payerIBAN, String payeeIBAN, float amount) {
        super();
        this.payerIBAN = payeeIBAN;
        this.payeeIBAN = payeeIBAN;
        this.amount = amount;
    }
}