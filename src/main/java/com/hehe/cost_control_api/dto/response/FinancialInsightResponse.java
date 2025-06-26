package com.hehe.cost_control_api.dto.response;

public record FinancialInsightResponse(
        Double totalExpenseToday,
        Double totalExpenseThisMonth,
        Float financialHealthScore,
        Float percentageThisMonth,
        String savingFeedback
) {
}
