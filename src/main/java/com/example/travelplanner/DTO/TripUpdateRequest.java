package com.example.travelplanner.DTO;

import com.example.travelplanner.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripUpdateRequest {
    private String name;
    private String startDate;
    private String endDate;
    private Long destinationId;
}
