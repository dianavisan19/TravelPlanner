package com.example.travelplanner.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityDTO {
    private Long activityId;
    private String name;
    private String date;
    private String time;
    private String location;
}
