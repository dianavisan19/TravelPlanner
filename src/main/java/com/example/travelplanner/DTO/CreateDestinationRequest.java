package com.example.travelplanner.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDestinationRequest {
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;
    @NotBlank(message = "Name cannot be blank")
    @UpperCase
    private String name;
    @NotBlank(message = "Country cannot be blank")
    @UpperCase
    private String country;
}
