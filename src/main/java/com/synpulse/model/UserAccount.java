package com.synpulse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "userAccounts")
public class UserAccount {
    public UUID id;
    public String iban;
    public String userId;
    public String countryCode;
    public String currency;
    public Float amount;
    public String valueDate;

    public UserAccount() {}

    public UserAccount(
        @JsonProperty("id")         UUID id,
        @JsonProperty("iban")       String iban,
        @JsonProperty("userId")     String userId,
        @JsonProperty("countryCode")String countryCode,
        @JsonProperty("currency")   String currency,
        @JsonProperty("amount")     Float amount,
        @JsonProperty("valueDate")  String valueDate) {
        super();
        this.id = id;
        this.iban = iban;
        this.userId = userId;
        this.countryCode = countryCode;
        this.currency = currency;
        this.amount = amount;
        this.valueDate = valueDate;
    }
}