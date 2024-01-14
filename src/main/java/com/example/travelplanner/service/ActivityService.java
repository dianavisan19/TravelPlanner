package com.example.travelplanner.service;

import com.example.travelplanner.DTO.ActivityRequestDTO;
import com.example.travelplanner.DTO.EditActivityRequest;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.exceptions.ActivityNotFoundException;
import com.example.travelplanner.repository.ActivityRepository;
import com.example.travelplanner.repository.TripRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TripRepository tripRepository;
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity save(Activity activity){
        return activityRepository.save(activity);
    }

    public Trip addActivityToTrip(ActivityRequestDTO activityRequestDTO){
        Long tripId = activityRequestDTO.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ActivityNotFoundException("Trip not found with ID: " + tripId));
        Activity activity = modelMapper.map(activityRequestDTO, Activity.class);
        if(trip.getActivities().stream().anyMatch(existingActivity ->
                existingActivity.getName().equals(activityRequestDTO.getName()))){
            throw new EntityExistsException("Activity already exists in the trip.");
        }
        trip.getActivities().add(activity);
        tripRepository.save(trip);
        return trip;
    }

    public Activity editActivityOfTrip(EditActivityRequest editActivityRequest) {
        Trip trip = tripRepository.findById(editActivityRequest.getTripId())
                .orElseThrow(() -> new ActivityNotFoundException("Trip not found with ID: " + editActivityRequest.getTripId()));

        Activity activity = trip.getActivities().stream()
                .filter(a -> a.getActivityId().equals(editActivityRequest.getActivityId()))
                .findFirst()
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found with ID: " + editActivityRequest.getActivityId()));

        activity.setName(editActivityRequest.getName());
        activity.setDate(editActivityRequest.getDate());
        activity.setLocation(editActivityRequest.getLocation());
        activity.setTime(editActivityRequest.getTime());

        return activityRepository.save(activity);
    }

    public void deleteActivity(Long activityId) {
        if (!activityRepository.existsById(activityId)) {
            throw new EntityNotFoundException("Not found: " + activityId);
        }
        activityRepository.deleteById(activityId);
    }
}
