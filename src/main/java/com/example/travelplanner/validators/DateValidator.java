package com.example.travelplanner.validators;

import com.example.travelplanner.entity.Trip;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<StartDateBeforeEndDate, Trip> {

    @Override
    public boolean isValid(Trip trip, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate start = LocalDate.parse(trip.getStartDate());
        LocalDate end = LocalDate.parse(trip.getEndDate());
        return start.isBefore(end);
    }
}
