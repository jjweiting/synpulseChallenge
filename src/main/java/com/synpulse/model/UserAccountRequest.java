package com.synpulse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountRequest {
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("countryCode")
    public String countryCode;

    /**
     * @param firstname
     * 
     * @param lastname
     */
    public UserAccountRequest(String userId, String countryCode) {
        super();
        this.userId = userId;
        this.countryCode = countryCode;
    }
}