package com.example.travelplanner;

import com.example.travelplanner.DTO.ActivityRequestDTO;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.repository.ActivityRepository;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import com.example.travelplanner.service.ActivityService;
import com.example.travelplanner.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private TripRepository tripRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TripService tripService;

    @InjectMocks
    private ActivityService activityService;

    private Trip trip;
    private Activity activity;
    private ActivityRequestDTO activityRequestDTO;
    @BeforeEach
    void setUp() {
        trip = new Trip();
        trip.setTripId(1L);
        trip.setActivities(new ArrayList<>());

        activity = new Activity();
        activity.setName("Hiking");

        activityRequestDTO = new ActivityRequestDTO();
        activityRequestDTO.setTripId(1L);
        activityRequestDTO.setName("Hiking");
    }
    @Test
    void testAddActivityToTrip() {
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));
        when(modelMapper.map(activityRequestDTO, Activity.class)).thenReturn(activity);

        Trip updatedTrip = activityService.addActivityToTrip(activityRequestDTO);

        assertNotNull(updatedTrip);
        assertTrue(updatedTrip.getActivities().contains(activity));

    }
}
