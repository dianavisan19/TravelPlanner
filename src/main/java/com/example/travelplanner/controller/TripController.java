package com.example.travelplanner.controller;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    public TripService tripService;

    @GetMapping
    public List<Trip> getAllTrips(){
        return tripService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable Long id) {
        return tripService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Trip> saveNewTrip(@RequestBody Trip trip){
        tripService.save(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }
}
