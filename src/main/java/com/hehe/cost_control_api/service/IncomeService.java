package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public interface IncomeService {

    Income addIncome(@NotNull String source, @NotNull Double amount, @NotNull String categoryName, @NotNull LocalDate incomeDate, @NotNull Users user, String note);

}
