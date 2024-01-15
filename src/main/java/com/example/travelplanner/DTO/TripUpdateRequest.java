package com.example.travelplanner.DTO;

import com.example.travelplanner.entity.Destination;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String name;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    @NotNull
    private Long destinationId;
}
