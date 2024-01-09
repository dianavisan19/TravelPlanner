package com.example.travelplanner.service;

import com.example.travelplanner.DTO.*;
import com.example.travelplanner.entity.*;
import com.example.travelplanner.repository.DestinationRepository;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Trip updateTrip(Long tripId, TripUpdateRequest tripUpdateRequest){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new UserNotFoundException("Trip not found with ID: " + tripId));
        trip.setName(tripUpdateRequest.getName());
        trip.setStartDate(tripUpdateRequest.getStartDate());
        trip.setEndDate(tripUpdateRequest.getEndDate());
//        trip.setDestination(tripUpdateRequest.getDestinationId());
        return tripRepository.save(trip);
    }


    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found: " + tripId);
        }
        tripRepository.deleteById(tripId);
    }

    public Trip addActivityToTrip(CreateActivityRequest createActivityRequest){
        Long tripId = createActivityRequest.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + tripId));
        Activity activity = modelMapper.map(createActivityRequest, Activity.class);
        trip.getActivities().add(activity);
        tripRepository.save(trip);
        return trip;
    }
    public Trip addExpenseToTrip(CreateExpenseRequest createExpenseRequest){
        Long tripId = createExpenseRequest.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found: " + tripId));
        Expense expense = new Expense();
        expense.setName(createExpenseRequest.getName());
        expense.setAmount(Double.valueOf(createExpenseRequest.getAmount()));
        expense.setType(ExpenseType.valueOf(createExpenseRequest.getType().toUpperCase()));
        trip.getExpenses().add(expense);
        tripRepository.save(trip);
        return trip;
    }

    public Trip addDestination(CreateDestinationRequest createDestinationRequest){
        Long tripId = createDestinationRequest.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found: " + tripId));
        Destination destination = new Destination();
        destination.setTrip(trip);
        destination.setName(createDestinationRequest.getName());
        destination.setCountry(createDestinationRequest.getCountry());
        trip.setDestination(destination);
        return tripRepository.save(trip);
    }
}
