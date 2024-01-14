package com.example.travelplanner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "activities")
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;
    private String time;
    private String location;
}
