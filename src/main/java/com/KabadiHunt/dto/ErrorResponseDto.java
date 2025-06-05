package com.KabadiHunt.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponseDto(Integer status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}

