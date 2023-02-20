package com.synpulse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;

    /**
     * @param firstname
     * 
     * @param lastname
     */
    public UserRequest(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}