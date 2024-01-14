package com.example.travelplanner.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripCreateRequest {
    private Long tripId;
    private String name;
    private String startDate;
    private String endDate;
}
