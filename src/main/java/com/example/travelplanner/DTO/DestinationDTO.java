package com.example.travelplanner.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DestinationDTO {
    private Long destinationId;
    private String name;
    private String description;
    private String country;

}
