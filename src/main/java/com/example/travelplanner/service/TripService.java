package com.example.travelplanner.service;

import com.example.travelplanner.DTO.TripCreateRequest;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.entity.User;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    public TripRepository tripRepository;

    @Autowired
    public UserRepository userRepository;

    public Trip createTrip(Long userId, Trip newTrip){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        newTrip.setUser(user);

        return tripRepository.save(newTrip);
    }

    public List<Trip> getAll(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getById(Long id){
        return tripRepository.findById(id);
    }

    public List<TripCreateRequest> getTripsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Trip> userTrips = tripRepository.findTripsByUser(user);
        return userTrips.stream()
                .map(this::mapToTripDTO)
                .collect(Collectors.toList());
    }

    public Trip updateTrip(Long tripId, Trip updatedTripBody){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new UserNotFoundException("Trip not found with ID: " + tripId));
        trip.setName(updatedTripBody.getName());
        trip.setStartDate(updatedTripBody.getStartDate());
        trip.setEndDate(updatedTripBody.getEndDate());

        trip.setDestination(updatedTripBody.getDestination());

//        trip.getActivities().clear();
//        trip.getActivities().addAll(updatedTripBody.getActivities());
//
//        trip.getExpenses().clear();
//        trip.getExpenses().addAll(updatedTripBody.getExpenses());
        return tripRepository.save(trip);
    }
    private TripCreateRequest mapToTripDTO(Trip trip) {
        TripCreateRequest tripCreateRequest = new TripCreateRequest();
        tripCreateRequest.setTripId(trip.getTripId());
        tripCreateRequest.setName(trip.getName());
        tripCreateRequest.setStartDate(trip.getStartDate());
        tripCreateRequest.setEndDate(trip.getEndDate());
        return tripCreateRequest;
    }

    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found with ID: " + tripId);
        }
        tripRepository.deleteById(tripId);
    }
}
