package com.KabadiHunt.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserProfileResponse {

    private final String id;
    private final String username;
    private final String email;
    private final String role;
    private final String profileImageUrl;
    private final LocalDateTime createdAt;

    private UserProfileResponse(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.role = builder.role;
        this.profileImageUrl = builder.profileImageUrl;
        this.createdAt = builder.createdAt;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id;
        private String username;
        private String email;
        private String role;
        private String profileImageUrl;
        private LocalDateTime createdAt;

        public Builder id(String id) { this.id = id; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder role(String role) { this.role = role; return this; }
        public Builder profileImageUrl(String url) { this.profileImageUrl = url; return this; }
        public Builder createdAt(LocalDateTime date) { this.createdAt = date; return this; }

        public UserProfileResponse build() { return new UserProfileResponse(this); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileResponse that = (UserProfileResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}