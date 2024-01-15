package com.example.travelplanner.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityRequestDTO {
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String time;
    @UpperCase
    private String location;
}
