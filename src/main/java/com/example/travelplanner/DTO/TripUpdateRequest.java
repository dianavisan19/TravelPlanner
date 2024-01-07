package com.example.travelplanner.DTO;

import java.util.List;

public class TripUpdateRequest {
    private String name;
    private String startDate;
    private String endDate;
    private List<ActivityDTO> activities; // Assume ActivityDTO is a class you define
    private List<ExpenseDTO> expenses;    // Assume ExpenseDTO is a class you define
    private DestinationDTO destination;
}
