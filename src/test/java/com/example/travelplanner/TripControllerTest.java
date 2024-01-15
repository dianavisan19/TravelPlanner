package com.example.travelplanner;

import com.example.travelplanner.DTO.CreateTripRequest;
import com.example.travelplanner.DTO.TripUpdateRequest;
import com.example.travelplanner.controller.TripController;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.entity.User;
import com.example.travelplanner.service.TripService;
import com.example.travelplanner.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TripController.class)
public class TripControllerTest {

    @MockBean
    TripService tripService;
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_createTrip() throws Exception {
        Long userId = 1L;
        CreateTripRequest createTripRequest = new CreateTripRequest(userId, "Excursie", "01/01/2024", "07/01/2024");

        User user = new User();
        user.setUserId(userId);

        Trip trip = new Trip(1L, user, "Excursie", "01/01/2024", "07/01/2024", null, null, null);

        when(tripService.createTrip(createTripRequest)).thenReturn(trip);

        mockMvc.perform(post("/trips/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createTripRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getAllTrips() throws Exception {

        List<Trip> trips = Arrays.asList(
                new Trip(1L, new User(), "Trip 1", "01/01/2024", "07/01/2024", null, null, null),
                new Trip(2L, new User(), "Trip 2", "02/02/2024", "08/02/2024", null, null, null)
        );
        when(tripService.getAll()).thenReturn(trips);

        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Trip 1")))
                .andExpect(jsonPath("$[1].name", is("Trip 2")));
    }

    @Test
    public void test_getTripById() throws Exception {
        Long tripId = 1L;
        Optional<Trip> trip = Optional.of(new Trip(tripId, new User(), "Trip 1", "01/01/2024", "07/01/2024", null, null, null));

        when(tripService.getById(tripId)).thenReturn(trip);

        mockMvc.perform(get("/trips/{id}", tripId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Trip 1")))
                .andExpect(jsonPath("$.tripId", is(tripId.intValue())));
    }

    @Test
    public void test_getUserTrips() throws Exception {
        User user1 = new User();
        user1.setUserId(1L);

        List<Trip> trips = Arrays.asList(
                new Trip(1L, user1, "Trip 1", "01/01/2024", "07/01/2024", null, null, null),
                new Trip(2L, user1, "Trip 2", "02/02/2024", "08/02/2024", null, null, null)
        );
        when(tripService.getTripsByUserId(user1.getUserId())).thenReturn(trips);


        mockMvc.perform(get("/trips/user/{userId}", user1.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Trip 1")))
                .andExpect(jsonPath("$[1].name", is("Trip 2")));
    }

    @Test
    public void test_UpdateTrip() throws Exception {
        Trip trip = new Trip(1L, new User(), "Trip 1", "01/01/2024", "07/01/2024", null, null, null);
        Trip updatedTrip = new Trip(1L, new User(), "Excursie", "01/01/2024", "07/01/2024", null, null, null);

        TripUpdateRequest tripUpdateRequest = new TripUpdateRequest("Excursie", "01/01/2024", "07/01/2024", 1L);

        when(tripService.updateTrip(trip.getTripId(), tripUpdateRequest)).thenReturn(updatedTrip);

        mockMvc.perform(put("/trips/{tripId}", trip.getTripId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(tripUpdateRequest)))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteTrip() throws Exception {
        Long tripId = 1L;

        doNothing().when(tripService).deleteTrip(tripId);

        mockMvc.perform(delete("/trips/{tripId}", tripId))
                .andExpect(status().isOk());
        verify(tripService).deleteTrip(tripId);
    }
}
