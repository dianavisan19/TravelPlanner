package com.example.travelplanner.entity;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public final class UserBuilder {
    private Long userId;
    private @NotBlank(message = "Username cannot be blank") String username;
    private List<Trip> trips;

    public UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withTrips(List<Trip> trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setTrips(trips);
        return user;
    }
}
