package com.example.travelplanner.exceptions;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(String message) {
        super(message);
    }
    public TripNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
