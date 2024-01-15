package com.example.travelplanner.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class CreateTripRequestBuilder {
    private @NotNull(message = "Trip ID cannot be null") Long userId;
    private @NotBlank String name;
    private @NotBlank String startDate;
    private @NotBlank String endDate;

    public CreateTripRequestBuilder() {
    }

    public static CreateTripRequestBuilder aCreateTripRequest() {
        return new CreateTripRequestBuilder();
    }

    public CreateTripRequestBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CreateTripRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CreateTripRequestBuilder withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public CreateTripRequestBuilder withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public CreateTripRequest build() {
        CreateTripRequest createTripRequest = new CreateTripRequest();
        createTripRequest.setUserId(userId);
        createTripRequest.setName(name);
        createTripRequest.setStartDate(startDate);
        createTripRequest.setEndDate(endDate);
        return createTripRequest;
    }
}
