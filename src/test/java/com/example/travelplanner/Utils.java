package com.example.travelplanner;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.DTO.CreateTripRequest;
import com.example.travelplanner.service.UserService;
@NoArgsConstructor
@AllArgsConstructor
public class Utils {
    @Autowired
    public UserService userService;

    public Trip fromDto(CreateTripRequest dto) {
        Trip trip = new Trip();
        trip.setUser(userService.getUserById(dto.getUserId()));
        trip.setName(dto.getName());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        return trip;
    }


    public CreateTripRequest toDto(Trip trip) {
        CreateTripRequest dto = new CreateTripRequest();
        dto.setUserId(trip.getUser().getUserId());
        dto.setName(trip.getName());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        return dto;
    }
}
