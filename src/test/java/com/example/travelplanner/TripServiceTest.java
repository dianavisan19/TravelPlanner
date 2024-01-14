package com.example.travelplanner;

import com.example.travelplanner.DTO.ActivityRequestDTO;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.entity.User;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import com.example.travelplanner.DTO.CreateTripRequest;
import com.example.travelplanner.exceptions.ActivityNotFoundException;
import com.example.travelplanner.service.ActivityService;
import com.example.travelplanner.service.TripService;
import com.example.travelplanner.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TripService tripService;

    @InjectMocks
    private ActivityService activityService;

    @Test
    void create() {
        //arrange
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        CreateTripRequest newTripRequest = new CreateTripRequest();
        newTripRequest.setUserId(userId);

        Trip trip = new Trip();
        trip.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(modelMapper.map(newTripRequest, Trip.class)).thenReturn(trip);
        when(tripRepository.save(trip)).thenReturn(trip);

        Trip result = tripService.createTrip(newTripRequest);

        assertNotNull(result);
        assertEquals(trip, result);
        verify(userRepository).findById(userId);
        verify(modelMapper).map(newTripRequest, Trip.class);
        verify(tripRepository).save(trip);
    }

    @Test
    void whenUserNotFound_createTrip_throwsException() {
        Long nonExistentUserId = 99L;
        CreateTripRequest newTripRequest = new CreateTripRequest();
        newTripRequest.setUserId(nonExistentUserId);
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> tripService.createTrip(newTripRequest));

        assertNotNull(exception);
        assertEquals("User not found with ID: " + nonExistentUserId,
                exception.getMessage());

        verify(userRepository).findById(nonExistentUserId);
    }

    @Test
    void testGetAllTrips(){
        Trip trip = new Trip();
        Trip trip2 = new Trip();

        when(tripRepository.findAll()).thenReturn(List.of(trip, trip2));

        List<Trip> result = tripService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(trip, result.get(0));
    }

    @Test
    void testGetTripByID(){
        Trip trip = new Trip();

        when(tripRepository.findById(trip.getTripId())).thenReturn(Optional.of(trip));

        assertNotNull(tripRepository.findById(trip.getTripId()));
    }

    @Test
    void testAddActivityToTrip(){
        Long tripId = 1L;
        Trip trip = new Trip();
        trip.setActivities(new ArrayList<>());
        Activity activity = new Activity();
        ActivityRequestDTO activityRequestDTO = new ActivityRequestDTO();
        activityRequestDTO.setTripId(tripId);

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
        when(modelMapper.map(activityRequestDTO, Activity.class)).thenReturn(activity);

        Trip result = activityService.addActivityToTrip(activityRequestDTO);

        assertNotNull(result);
        assertTrue(result.getActivities().contains(activity));
        verify(tripRepository).save(trip);
    }

    @Test
    void addActivityToTrip_TripNotFound() {
        Long tripId = 99L;
        ActivityRequestDTO activityRequestDTO = new ActivityRequestDTO();
        activityRequestDTO.setTripId(tripId);

        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        assertThrows(ActivityNotFoundException.class, () -> activityService.addActivityToTrip(activityRequestDTO));
    }


}
