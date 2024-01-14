package com.example.travelplanner.entity;

import com.example.travelplanner.validators.StartDateBeforeEndDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") //foreign key in the trips table
    private User user;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private List<Activity> activities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private List<Expense> expenses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
