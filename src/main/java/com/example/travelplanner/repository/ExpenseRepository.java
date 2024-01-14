package com.example.travelplanner.repository;

import com.example.travelplanner.entity.Activity;
import com.example.travelplanner.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
