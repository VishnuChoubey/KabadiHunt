package com.KabadiHunt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class GoogleLoginDto {

    @NotBlank(message = "Access token is required")
    private String accessToken;

    @NotBlank(message = "User type is required")
    @Pattern(regexp = "user|recycler", message = "User type must be either 'user' or 'recycler'")
    private String userType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

