package com.KabadiHunt.dto;

import jakarta.validation.constraints.*;

public class RecycleFormRequestDto {

    @NotBlank(message = "User ID is required")
    private String orderId;

    @NotBlank(message = "Organisation ID is required")
    private String organisationId;

    @NotBlank(message="User id is required")
    private String userId;

    @NotBlank(message = "Item type is required")
    private String itemType;

    @NotNull(message = "Date is required")
    private String date;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    private String imagePath;

    @NotNull(message = "Weight is required")
    @Min(value = 1, message = "Weight must be at least 1")
    private Integer weight;

    @NotBlank(message = "longitude is required")
    private Double longitude;
    @NotBlank(message = "latitude is required")
    private Double latitude;

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
    public String getOrderId(){
        return orderId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


}

