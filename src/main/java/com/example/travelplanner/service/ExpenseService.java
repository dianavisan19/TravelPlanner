package com.example.travelplanner.service;

import com.example.travelplanner.DTO.CreateExpenseRequest;
import com.example.travelplanner.DTO.EditActivityRequest;
import com.example.travelplanner.DTO.EditExpenseRequest;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Expense;
import com.example.travelplanner.entity.ExpenseType;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.exceptions.ActivityNotFoundException;
import com.example.travelplanner.exceptions.ExpenseNotFoundException;
import com.example.travelplanner.repository.ExpenseRepository;
import com.example.travelplanner.repository.TripRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    public ExpenseRepository expenseRepository;

    @Autowired
    public TripRepository tripRepository;

    public Trip addExpenseToTrip(CreateExpenseRequest createExpenseRequest) {
        Long tripId = createExpenseRequest.getTripId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found: " + tripId));
        Expense expense = new Expense();
        expense.setName(createExpenseRequest.getName());
        expense.setAmount(Double.valueOf(createExpenseRequest.getAmount()));
        expense.setType(ExpenseType.valueOf(createExpenseRequest.getType().toUpperCase()));
        if(trip.getExpenses().stream().anyMatch(existing ->
                existing.getName().equals(createExpenseRequest.getName()))){
            throw new EntityExistsException("Expense already exists in the trip.");
        }
        trip.getExpenses().add(expense);
        tripRepository.save(trip);
        return trip;
    }

    public Expense editExpenseOfTrip(EditExpenseRequest editExpenseRequest) {
        Trip trip = tripRepository.findById(editExpenseRequest.getTripId())
                .orElseThrow(() -> new ActivityNotFoundException("Trip not found with ID: " + editExpenseRequest.getTripId()));

        Expense expense = trip.getExpenses().stream()
                .filter(a -> a.getExpenseId().equals(editExpenseRequest.getExpenseId()))
                .findFirst()
                .orElseThrow(() -> new ActivityNotFoundException("Expense not found with ID: " + editExpenseRequest.getExpenseId()));

        expense.setName(editExpenseRequest.getName());
        expense.setAmount(Double.valueOf(editExpenseRequest.getAmount()));
        expense.setType(ExpenseType.valueOf(editExpenseRequest.getType()));

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(
                () -> new ExpenseNotFoundException("Expense not found with id: " + expenseId)
        );
        expenseRepository.delete(expense);
    }
}
