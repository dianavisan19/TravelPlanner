package com.example.travelplanner.controller;

import com.example.travelplanner.DTO.TripCreateRequest;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.service.TripService;
import com.example.travelplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    public TripService tripService;

    @Autowired
    public UserService userService;

    @GetMapping
    public List<Trip> getAllTrips(){
        return tripService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable Long id) {
        return tripService.getById(id);
    }

    @PostMapping("/create/{userId}")
    public Trip createTrip(@PathVariable Long userId, @RequestBody Trip trip) {
        return tripService.createTrip(userId, trip);
    }

    @GetMapping("/user/{userId}")
    public List<TripCreateRequest> getUserTrips(@PathVariable Long userId) {
        List<TripCreateRequest> userTrips = tripService.getTripsByUserId(userId);
        return userTrips;
    }
    @PutMapping("/{tripId}")
    public Trip updateTrip(@PathVariable Long tripId, @RequestBody Trip trip) {
        return tripService.updateTrip(tripId, trip);
    }
    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok().build();
    }

}
