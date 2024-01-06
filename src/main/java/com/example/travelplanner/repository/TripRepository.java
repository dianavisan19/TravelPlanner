package com.example.travelplanner.repository;

import com.example.travelplanner.entity.Destination;
import com.example.travelplanner.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}