package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.Granularity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface AnalyticsService {

    List<Object[]> getExpensesOnRangeWithGranularity(@NotNull Users user, @NotNull LocalDate from, @NotNull LocalDate to, @NotNull Granularity granularity);

    List<Object[]> getExpensesCategoryTotal(@NotNull Users user, YearMonth monthAt);

}
