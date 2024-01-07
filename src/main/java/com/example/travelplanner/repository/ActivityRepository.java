package com.example.travelplanner.repository;

import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
