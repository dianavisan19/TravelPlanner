package com.example.travelplanner.controller;

import com.example.travelplanner.entity.Destination;
import com.example.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<Destination> getAllDestination() {
        return destinationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Destination> getDestination(@PathVariable Long id){
        return destinationService.getDestinationByID(id);
    }
    @PostMapping
    public ResponseEntity<Destination> saveDestination(@RequestBody Destination destination){
        destinationService.save(destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(destination);
    }
}
