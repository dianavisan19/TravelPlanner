package com.example.travelplanner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Attraction")
public class AttractionExpense extends Expense {
}
