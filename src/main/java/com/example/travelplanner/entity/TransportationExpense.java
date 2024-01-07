package com.example.travelplanner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Transportation")
public class TransportationExpense extends Expense {
}
