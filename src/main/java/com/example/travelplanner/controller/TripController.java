package com.example.travelplanner.controller;

import com.example.travelplanner.DTO.*;
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

    @PostMapping("/create")
    public Trip createTrip(@RequestBody CreateTripRequest createTripRequest) {
        return tripService.createTrip(createTripRequest);
    }
    @PostMapping("/addActivity")
    public Trip addActivityToTrip(@RequestBody CreateActivityRequest createActivityRequest) {
        return tripService.addActivityToTrip(createActivityRequest);
    }

    @PostMapping("/addExpense")
    public Trip addActivityToTrip(@RequestBody CreateExpenseRequest createExpenseRequest) {
        return tripService.addExpenseToTrip(createExpenseRequest);
    }
    @PostMapping("/addDestination")
    public Trip addDestinationToTrip(@RequestBody CreateDestinationRequest createDestinationRequest){
        return tripService.addDestination(createDestinationRequest);
    }

    @GetMapping("/user/{userId}")
    public List<Trip> getUserTrips(@PathVariable Long userId) {
       return tripService.getTripsByUserId(userId);
    }
    @PutMapping("/{tripId}")
    public Trip updateTrip(@PathVariable Long tripId, @RequestBody TripUpdateRequest tripUpdateRequest) {
        return tripService.updateTrip(tripId, tripUpdateRequest);
    }
    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok().build();
    }
}
