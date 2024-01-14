package com.example.travelplanner.service;

import com.example.travelplanner.DTO.*;
import com.example.travelplanner.entity.*;
import com.example.travelplanner.exceptions.DestinationNotFoundException;
import com.example.travelplanner.exceptions.UserNotFoundException;
import com.example.travelplanner.repository.DestinationRepository;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    public TripRepository tripRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DestinationRepository destinationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Trip createTrip(CreateTripRequest newTripRequest){
        User user = userRepository.findById(newTripRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + (newTripRequest.getUserId())));
        Trip trip = modelMapper.map(newTripRequest, Trip.class);
        trip.setUser(user);
        return tripRepository.save(trip);
    }

    public List<Trip> getAll(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getById(Long id){
        return tripRepository.findById(id);
    }

    public List<Trip> getTripsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return tripRepository.findTripsByUser(user);
    }

    public Trip updateTrip(Long tripId, TripUpdateRequest tripUpdateRequest) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found: " + tripId));

        trip.setName(tripUpdateRequest.getName());
        trip.setStartDate(tripUpdateRequest.getStartDate());
        trip.setEndDate(tripUpdateRequest.getEndDate());

        if (tripUpdateRequest.getDestinationId() != null) {
            Destination destination = destinationRepository.findById(tripUpdateRequest.getDestinationId())
                    .orElseThrow(() -> new DestinationNotFoundException("Destination not found: " + tripUpdateRequest.getDestinationId()));
            trip.setDestination(destination);
        }

        return tripRepository.save(trip);
    }


    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found: " + tripId);
        }
        tripRepository.deleteById(tripId);
    }




    public Trip addDestination(CreateDestinationRequest createDestinationRequest){
        Long tripId = createDestinationRequest.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found: " + tripId));
        Destination destination = new Destination();
        destination.setTrip(trip);
        destination.setName(createDestinationRequest.getName());
        destination.setCountry(createDestinationRequest.getCountry());
        if(trip.getDestination().equals(createDestinationRequest.getName())){
            throw new EntityExistsException("Destinaion already exists in the trip.");
        }
        trip.setDestination(destination);
        return tripRepository.save(trip);
    }

}
