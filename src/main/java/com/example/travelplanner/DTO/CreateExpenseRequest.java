package com.example.travelplanner.DTO;

import com.example.travelplanner.entity.ExpenseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpenseRequest {
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Amount cannot be blank")
    private String amount;
    @Enumerated(EnumType.STRING)
    private String type;
}
