package com.example.travelplanner.DTO;

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
public class UserDTO {
    @NotNull
    private Long userId;
    @NotBlank
    private String username;
    private List<TripCreateRequest> trips;
}
