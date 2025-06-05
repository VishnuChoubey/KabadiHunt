package com.KabadiHunt.dto;

public class ApiResponseDto {

    private Boolean success;
    private String message;
    private Object data;

    public ApiResponseDto(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponseDto(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

