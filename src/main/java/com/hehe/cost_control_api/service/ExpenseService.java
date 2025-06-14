package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public interface ExpenseService {

    Expense addExpense(@NotNull String reason, @NotNull Double amount, @NotNull String categoryName, @NotNull LocalDate expenseDate, @NotNull Users user,  String note);


}
