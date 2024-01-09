package com.example.travelplanner.repository;

import com.example.travelplanner.entity.Destination;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findTripsByUser(Optional<User> userId);

    Destination findDestinationById(Long destinationId);
}
