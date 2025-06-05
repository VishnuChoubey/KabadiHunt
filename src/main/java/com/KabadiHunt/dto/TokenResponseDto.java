package com.KabadiHunt.dto;

import jakarta.validation.constraints.NotBlank;

public class TokenResponseDto {

    @NotBlank(message = "Access token must not be blank")
    private String access;

    @NotBlank(message = "Refresh token must not be blank")
    private String refresh;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}

