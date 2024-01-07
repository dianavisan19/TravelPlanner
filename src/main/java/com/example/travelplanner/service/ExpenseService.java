package com.example.travelplanner.service;

import com.example.travelplanner.entity.Expense;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.repository.ExpenseRepository;
import com.example.travelplanner.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    public ExpenseRepository expenseRepository;

    @Autowired
    public TripRepository tripRepository;

}
