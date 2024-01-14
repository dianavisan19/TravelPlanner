package com.example.travelplanner.DTO;

import com.example.travelplanner.validators.StartDateBeforeEndDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTripRequest {
    @NotNull(message = "Trip ID cannot be null")
    private Long userId;
    private String name;
    private String startDate;
    private String endDate;
}
