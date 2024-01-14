package com.example.travelplanner;

import com.example.travelplanner.DTO.CreateTripRequest;
import com.example.travelplanner.controller.TripController;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.service.TripService;
import com.example.travelplanner.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)

public class TripControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tripController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void getAllTripsTest() throws Exception {
        Trip trip1 = new Trip();
        Trip trip2 = new Trip();

        when(tripService.getAll()).thenReturn(List.of(trip1, trip2));

        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void testGetTripById() throws Exception {
        Long tripId = 1L;
        Trip trip = new Trip();
        trip.setTripId(tripId);

        when(tripService.getById(tripId)).thenReturn(Optional.of(trip));

        mockMvc.perform(get("/trips/" + tripId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripId").value(tripId));
    }






}
