package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.exception.InvalidGranularityException;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.Granularity;
import com.hehe.cost_control_api.repository.ExpenseRepository;
import com.hehe.cost_control_api.service.AnalyticsService;
import com.hehe.cost_control_api.util.GranularityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;

    @Override
    public List<Object[]> getExpensesOnRangeWithGranularity(Users user, LocalDate from, LocalDate to, Granularity granularity) {
        Granularity actualGranularity;
        try {
            actualGranularity = GranularityValidator.validateGranularity(from, to, granularity);
        } catch (InvalidGranularityException e) {
            actualGranularity = GranularityValidator.fallbackGranularity(from, to);
        }

        return switch (actualGranularity) {
            case DAILY -> expenseRepository.groupByDay(user, from, to);
            case WEEKLY -> expenseRepository.groupByWeek(user.getId(), from, to);
            case MONTHLY -> expenseRepository.groupByMonth(user.getId(), from, to);
        };
    }

    @Override
    public List<Object[]> getExpensesCategoryTotal(Users user, YearMonth monthAt) {
        if(monthAt != null){
            return expenseRepository.getTotalExpenseByCategoryOnMonth(user, monthAt.atDay(1), monthAt.atEndOfMonth());
        }else{
            return expenseRepository.getTotalExpenseByCategoryAllTime(user);
        }
    }
}
