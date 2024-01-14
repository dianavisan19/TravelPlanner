package com.example.travelplanner.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditExpenseRequest {
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;
    @NotNull(message = "Expense ID cannot be null")
    private Long expenseId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Amount cannot be blank")
    private String amount;
    @Enumerated(EnumType.STRING)
    private String type;
}
