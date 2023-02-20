package com.synpulse.model;

import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collation = "transactions")
public class Transaction {
    public UUID     id;
    public String   payerIBAN;
    public String   payeeIBAN;
    public Float    amount;

    public Transaction() {}

    public Transaction(
        @JsonProperty("id")         UUID id,
        @JsonProperty("payerIBAN")  String payerIBAN,
        @JsonProperty("payeeIBAN")  String payeeIBAN,
        @JsonProperty("amount")     Float amount) {
        super();
        this.id = id;
        this.payerIBAN = payerIBAN;
        this.payeeIBAN = payeeIBAN;
        this.amount = amount;
    }
}