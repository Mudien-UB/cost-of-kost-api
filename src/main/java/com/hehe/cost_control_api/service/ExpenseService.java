package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.ExpenseSortType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface ExpenseService {

    Expense addExpense(@NotNull String reason, @NotNull Double amount, @NotNull String categoryName, @NotNull LocalDate expenseDate, @NotNull Users user,  String note);

    Page<Expense> listExpense(@NotNull Users user,@NotNull ExpenseSortType sort,LocalDate from, LocalDate to, String categoryName , int page, int size,  boolean asc);

}
