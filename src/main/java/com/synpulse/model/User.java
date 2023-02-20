package com.synpulse.model;

import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collation = "users")
public class User {
    public UUID id;
    public String firstName;
    public String lastName;

    public User() {}

    public User(@JsonProperty("id")         UUID id,
                @JsonProperty("firstName")  String firstName,
                @JsonProperty("lastName")   String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}