package com.example.travelplanner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String name;
    private String startDate;
    private String endDate;
    private String notes;

    //actiti will get saved along with trip
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "trip")
    private List<Expense> expenses;
}
