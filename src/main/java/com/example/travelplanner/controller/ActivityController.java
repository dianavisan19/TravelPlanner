package com.example.travelplanner.controller;

import com.example.travelplanner.DTO.ActivityRequestDTO;
import com.example.travelplanner.DTO.EditActivityRequest;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.service.ActivityService;
import com.example.travelplanner.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private TripService tripService;

    @PostMapping("/addActivity")
    public Trip addActivityToTrip(@Valid @RequestBody ActivityRequestDTO activityRequestDTO) {
        return activityService.addActivityToTrip(activityRequestDTO);
    }

    @PutMapping("/editActivity")
    public Activity editActivityOfTrip(@Valid @RequestBody EditActivityRequest editActivityRequest) {
        return activityService.editActivityOfTrip(editActivityRequest);
    }

    @DeleteMapping("/deleteActivity")
    public ResponseEntity<?> deleteActivityOfTrip(@Valid @PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.ok().build();
    }

}
