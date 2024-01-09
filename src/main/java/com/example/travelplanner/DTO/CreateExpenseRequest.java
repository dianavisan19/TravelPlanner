package com.example.travelplanner.DTO;

import com.example.travelplanner.entity.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpenseRequest {
    private Long tripId;
    private String name;
    private String amount;
    private String type;
}
