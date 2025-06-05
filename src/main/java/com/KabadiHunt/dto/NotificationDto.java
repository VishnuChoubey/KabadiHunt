package com.KabadiHunt.dto;

import com.KabadiHunt.models.Owner;
import com.KabadiHunt.models.User;

import java.time.LocalDateTime;

public class NotificationDto {

    private String id;
    private User user;
    private Boolean status;
    private LocalDateTime created;
    private String message;
    private Owner owner;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Owner getOwner(){
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

