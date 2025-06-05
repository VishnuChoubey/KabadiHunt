package com.KabadiHunt.dto;

import java.time.LocalDateTime;

public class EndUserDto {
    private Long enduserId;
    private Long userId;
    private String imagePath;
    private String phone;
    private LocalDateTime createdAt;

    public Long getEnduserId() {
        return enduserId;
    }

    public void setEnduserId(Long enduserId) {
        this.enduserId = enduserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

