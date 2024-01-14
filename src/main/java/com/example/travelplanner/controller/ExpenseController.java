package com.example.travelplanner.controller;

import com.example.travelplanner.DTO.CreateExpenseRequest;
import com.example.travelplanner.DTO.EditActivityRequest;
import com.example.travelplanner.DTO.EditExpenseRequest;
import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Expense;
import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.service.ExpenseService;
import com.example.travelplanner.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addExpense")
    public Trip addExpenseToTrip(@RequestBody CreateExpenseRequest createExpenseRequest) {
        return expenseService.addExpenseToTrip(createExpenseRequest);
    }

    @PutMapping("/editExpense")
    public Expense editExpenseOfTrip(@Valid @RequestBody EditExpenseRequest editExpenseRequest) {
        return expenseService.editExpenseOfTrip(editExpenseRequest);
    }

    @DeleteMapping("/deleteExpense/{expenseId}")
    public ResponseEntity<?> deleteExpenseOfTrip(@Valid @PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok().build();
    }
}
