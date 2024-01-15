package com.example.travelplanner.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripCreateRequest {
    @NotNull
    private Long tripId;
    @NotBlank
    private String name;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
}
