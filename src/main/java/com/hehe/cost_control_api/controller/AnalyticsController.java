package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.response.FinancialInsightResponse;
import com.hehe.cost_control_api.dto.response.UserResponse;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.FinancialInsightService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/analytics")
@Validated
@RequiredArgsConstructor
@CrossOrigin
public class AnalyticsController {

    private final UserService userService;
    private final FinancialInsightService financialInsightService;

    @GetMapping("/insight")
    public ResponseEntity<?> getInsight() {
        Users user = userService.getFromContext();

        Double totalToday = financialInsightService.calculateTotalExpenseToday(user);
        Double totalMonth = financialInsightService.calculateTotalExpenseThisMonth(user);
        Float percentMonth = financialInsightService.percentageExpenseThisMonth(user);
        Float healthScore = financialInsightService.calculateFinancialHealthScore(user);
        Float savingPercent = financialInsightService.calculateTodaySavingPercentage(user);

        String savingFeedback = financialInsightService.getSavingFeedback(savingPercent);

        FinancialInsightResponse response = new FinancialInsightResponse(
                totalToday,
                totalMonth,
                percentMonth,
                healthScore,
                savingPercent,
                savingFeedback
        );

        return BaseResponseUtil.buildResponse(HttpStatus.OK, "insight", response);
    }


}
