package com.KabadiHunt.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String orderId;
    private String itemType;
    private String location;
    private String phone;
    private String status; // "pending", "completed", "paid"
    private double amount;
    private Date createdAt;

    @DBRef
    private User user; // The user who created the order

    @DBRef
    private User recycler; // The recycler assigned to the order

    public Order() {}

    public Order(String id, String orderId, String itemType, String location, String phone, String status, double amount, Date createdAt, User user, User recycler) {
        this.id = id;
        this.orderId = orderId;
        this.itemType = itemType;
        this.location = location;
        this.phone = phone;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.user = user;
        this.recycler = recycler;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String orderId;
        private String itemType;
        private String location;
        private String phone;
        private String status; // "pending", "completed", "paid"
        private double amount;
        private Date createdAt;
        private User user;
        private User recycler;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder itemType(String itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder recycler(User recycler) {
            this.recycler = recycler;
            return this;
        }

        public Order build() {
            return new Order(id, orderId, itemType, location, phone, status, amount, createdAt, user, recycler);
        }
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRecycler() {
        return recycler;
    }

    public void setRecycler(User recycler) {
        this.recycler = recycler;
    }
}
