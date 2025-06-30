package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Users;
import jakarta.validation.constraints.NotNull;

public interface FinancialInsightService {

    Double calculateTotalExpenseToday(@NotNull Users user);

    Float percentageExpenseThisMonth(@NotNull Users user);

    Double calculateTotalExpenseThisMonth(@NotNull Users user);

//    Float percentageComparedToDailyAverageThisMonth(@NotNull Users user);

//    Float percentageComparedToHealthyDailyBudget(@NotNull Users user);

    Float calculateFinancialHealthScore(@NotNull Users user);

//    Float calculateTodaySavingPercentage(@NotNull Users user);

}
