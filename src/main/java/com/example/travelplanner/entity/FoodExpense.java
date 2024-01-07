package com.example.travelplanner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Food")
public class FoodExpense extends Expense{
}
