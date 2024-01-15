package com.example.travelplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    private String name;
    private String country;

    @JsonIgnore
    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private Trip trip;
}
