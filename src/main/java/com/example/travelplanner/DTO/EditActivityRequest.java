package com.example.travelplanner.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditActivityRequest {
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;
    @NotNull(message = "Activity ID cannot be null")
    private Long activityId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;
    private String time;
    @UpperCase
    private String location;
}
