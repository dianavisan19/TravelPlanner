package com.example.travelplanner.service;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    public TripRepository tripRepository;

    public Trip save(Trip trip){
       return tripRepository.save(trip);
    }

    public List<Trip> getAll(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getById(Long id){
        return tripRepository.findById(id);
    }
}
