package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.FeedbackMessage;
import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.repository.FeedbackMessageRepository;
import com.hehe.cost_control_api.service.FinancialInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class FinancialInsightServiceImpl implements FinancialInsightService {

    // Konstanta
    private static final int DAYS_IN_MONTH = 30;
    private static final double HEALTHY_BUDGET_RATIO = 0.8;
    private static final double DEFAULT_HEALTHY_MINIMUM = 2_500_000;
    private static final double MINIMUM_INCOME_THRESHOLD = 1_500_000;

    @Override
    public Double calculateTotalExpenseToday(Users user) {
        LocalDate today = LocalDate.now();
        return user.getExpenseList().stream()
                .filter(expense -> expense.getExpenseDate().equals(today))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public Double calculateTotalExpenseThisMonth(Users user) {
        LocalDate now = LocalDate.now();
        return user.getExpenseList().stream()
                .filter(expense -> isInCurrentMonth(expense.getExpenseDate()))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public Float percentageExpenseThisMonth(Users user) {
        double incomeThisMonth = calculateTotalIncomeThisMonth(user);
        double totalExpense = calculateTotalExpenseThisMonth(user);
        double healthyBudget = calculateHealthyBudgetLimit(incomeThisMonth);

        float raw = (float) ((1 - (totalExpense / healthyBudget)) * 100);
        return Math.max(0f, Math.min(100f, Math.round(raw)));
    }


//    @Override
//    public Float percentageComparedToDailyAverageThisMonth(Users user) {
//        double totalExpenseThisMonth = calculateTotalExpenseThisMonth(user);
//
//        Set<LocalDate> activeDates = user.getExpenseList().stream()
//                .filter(e -> isInCurrentMonth(e.getExpenseDate()))
//                .map(Expense::getExpenseDate)
//                .collect(Collectors.toSet());
//
//        long activeDays = activeDates.size();
//        if (activeDays == 0 || totalExpenseThisMonth == 0) return 0f;
//
//        double dailyAverage = totalExpenseThisMonth / activeDays;
//        double todayExpense = calculateTotalExpenseToday(user);
//        double percentage = (todayExpense / dailyAverage) * 100;
//
//        return (float) Math.round(percentage);
//    }
//
//    @Override
//    public Float percentageComparedToHealthyDailyBudget(Users user) {
//        double incomeThisMonth = calculateTotalIncomeThisMonth(user);
//        double healthyBudgetMonthly = calculateHealthyBudgetLimit(incomeThisMonth);
//        double healthyDailyBudget = healthyBudgetMonthly / DAYS_IN_MONTH;
//
//        double todayExpense = calculateTotalExpenseToday(user);
//        double percentage = (todayExpense / healthyDailyBudget) * 100;
//
//        return (float) Math.round(percentage);
//    }

//    @Override
//    public Float calculateTodaySavingPercentage(Users user) {
//        double incomeThisMonth = calculateTotalIncomeThisMonth(user);
//        double healthyBudgetMonthly = calculateHealthyBudgetLimit(incomeThisMonth);
//        double healthyDailyBudget = healthyBudgetMonthly / DAYS_IN_MONTH;
//
//        double todayExpense = calculateTotalExpenseToday(user);
//        double savingPercent = 100 - ((todayExpense / healthyDailyBudget) * 100);
//
//        return (float) Math.round(savingPercent);
//    }

    @Override
    public Float calculateFinancialHealthScore(Users user) {
        double income = calculateTotalIncomeThisMonth(user);
        double expense = calculateTotalExpenseThisMonth(user);
        double healthyBudget = calculateHealthyBudgetLimit(income);
        double healthyDailyBudget = healthyBudget / DAYS_IN_MONTH;

        double scoreSaving = scoreFromSavingPercent(user, expense, healthyBudget);
        double scoreExpenseRatio = scoreFromExpenseIncomeRatio(expense, income);
        double scoreConsistency = scoreFromConsistency(user);
        double scoreOverbudget = scoreFromOverbudgetDays(user, healthyDailyBudget);

        if(user.getExpenseList().isEmpty() && user.getIncomeList().isEmpty()) {
            return null;
        }

        if (income > 0) {
            return round1(
                    scoreSaving * 0.35 +
                            scoreExpenseRatio * 0.25 +
                            scoreConsistency * 0.20 +
                            scoreOverbudget * 0.20
            );
        } else {
            return round1(
                    scoreSaving * 0.50 +
                            scoreConsistency * 0.30 +
                            scoreOverbudget * 0.20
            );
        }
    }

    // ====================== UTIL & PRIVATE =========================

    private static double calculateTotalIncomeThisMonth(Users user) {
        LocalDate now = LocalDate.now();
        return user.getIncomeList().stream()
                .filter(income -> isInCurrentMonth(income.getIncomeDate()))
                .mapToDouble(Income::getAmount)
                .sum();
    }

    private static double calculateHealthyBudgetLimit(double totalIncomeThisMonth) {
        if (totalIncomeThisMonth > MINIMUM_INCOME_THRESHOLD) {
            return totalIncomeThisMonth * HEALTHY_BUDGET_RATIO;
        } else {
            return DEFAULT_HEALTHY_MINIMUM;
        }
    }

    private double scoreFromSavingPercent(Users user, double expense, double healthyBudget) {
        double savingPercent = 100 - ((expense / healthyBudget) * 100);
        if (savingPercent >= 25) return 10;
        else if (savingPercent >= 0) return (savingPercent / 25.0) * 10;
        else return 0;
    }

    private double scoreFromExpenseIncomeRatio(double expense, double income) {
        if (income <= 0) return 0;
        double ratio = expense / income;
        if (ratio <= 0.5) return 10;
        else if (ratio <= 1.0) return (1 - ratio) * 20;
        else return 0;
    }

    private double scoreFromConsistency(Users user) {
        long activeDays = user.getExpenseList().stream()
                .filter(e -> isInCurrentMonth(e.getExpenseDate()))
                .map(Expense::getExpenseDate)
                .distinct()
                .count();
        return Math.min((activeDays / 25.0) * 10, 10);
    }

    private double scoreFromOverbudgetDays(Users user, double dailyBudget) {
        Map<LocalDate, Double> dailyTotals = user.getExpenseList().stream()
                .filter(e -> isInCurrentMonth(e.getExpenseDate()))
                .collect(Collectors.groupingBy(
                        Expense::getExpenseDate,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        long overDays = dailyTotals.values().stream()
                .filter(total -> total > dailyBudget)
                .count();

        if (overDays <= 2) return 10;
        else if (overDays <= 5) return (1 - ((overDays - 2) / 3.0)) * 10;
        else return 0;
    }

    private static boolean isInCurrentMonth(LocalDate date) {
        LocalDate now = LocalDate.now();
        return date.getMonth() == now.getMonth() && date.getYear() == now.getYear();
    }

    private Float round1(double score) {
        return (float) Math.round(score * 10.0) / 10f;
    }
}
