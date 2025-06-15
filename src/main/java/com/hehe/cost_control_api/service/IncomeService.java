package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.IncomeSortType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IncomeService {

    Income addIncome(@NotNull String source, @NotNull Double amount, @NotNull String categoryName, @NotNull LocalDate incomeDate, @NotNull Users user, String note);

    Page<Income> listIncome(@NotNull Users user,@NotNull IncomeSortType sort, LocalDate from, LocalDate to, String categoryName , int page, int size,  boolean asc);

}
