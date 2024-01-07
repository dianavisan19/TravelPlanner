package com.example.travelplanner.controller;

import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.service.ActivityService;
import com.example.travelplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private TripRepository tripRepository;

}
