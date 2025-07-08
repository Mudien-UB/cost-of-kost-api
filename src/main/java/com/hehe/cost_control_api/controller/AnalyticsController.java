package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.response.ExpenseCategoryTotalResponse;
import com.hehe.cost_control_api.dto.response.FeedbackMessageResponse;
import com.hehe.cost_control_api.dto.response.FinancialInsightResponse;
import com.hehe.cost_control_api.dto.response.PeriodGranularity;
import com.hehe.cost_control_api.model.FeedbackMessage;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.Granularity;
import com.hehe.cost_control_api.service.AnalyticsService;
import com.hehe.cost_control_api.service.FeedbackMessageService;
import com.hehe.cost_control_api.service.FinancialInsightService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController()
@RequestMapping("/analytics")
@Validated
@RequiredArgsConstructor
@CrossOrigin
public class AnalyticsController {

    private final UserService userService;
    private final FinancialInsightService financialInsightService;
    private final FeedbackMessageService feedbackMessageService;
    private final AnalyticsService analyticsService;

    @GetMapping("/insight")
    public ResponseEntity<?> getInsight() {
        Users user = userService.getFromContext();

        Double totalToday = financialInsightService.calculateTotalExpenseToday(user);
        Double totalMonth = financialInsightService.calculateTotalExpenseThisMonth(user);
        Float healthScore = financialInsightService.calculateFinancialHealthScore(user);
        Float percentageMonth = financialInsightService.percentageExpenseThisMonth(user);

        FeedbackMessage feedbackMessage = (healthScore == null) ? feedbackMessageService.generateDefaultFeedbackMessage() :  feedbackMessageService.generateFeedbackMessage(percentageMonth);

        FinancialInsightResponse response = new FinancialInsightResponse(
                totalToday,
                totalMonth,
                healthScore,
                percentageMonth,
                FeedbackMessageResponse.of(feedbackMessage)
        );

        return BaseResponseUtil.buildResponse(HttpStatus.OK, "insight", response);
    }

    @GetMapping("/granularity")
    public ResponseEntity<?> getExpensesOnRangeWithGranularity(

            @RequestParam
            Granularity granularity,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to

    ) {
        Users user = userService.getFromContext();
        if(user ==  null){
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED, "unauthorized", null);
        }
        List<Object[]> result = analyticsService.getExpensesOnRangeWithGranularity(user, from, to, granularity);
        if(result == null || result.isEmpty()) {
            return BaseResponseUtil.buildResponse(HttpStatus.OK, "empty", List.of());
        }else{
            return BaseResponseUtil.buildResponse(
                    HttpStatus.OK,
                    "data retrivied on granularity " + granularity.toString(),
                    result.stream().map(PeriodGranularity::of).toList()
            );
        }
    }

    @GetMapping("/totalExpensePerCategory")
    public ResponseEntity<?> getTotalExpensePerCategory(
            @RequestParam(required = false)
            YearMonth monthAt
    ){
        Users user = userService.getFromContext();
        if(user ==  null){
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED, "unauthorized", null);
        }
        List<Object[]> result = analyticsService.getExpensesCategoryTotal(user, monthAt);
        if(result == null || result.isEmpty()) {
            return BaseResponseUtil.buildResponse(HttpStatus.OK, "empty", List.of());
        }else {
            return BaseResponseUtil.buildResponse(
                    HttpStatus.OK,
                    "data retrivied on month " + monthAt.toString(),
                    result.stream().map(ExpenseCategoryTotalResponse::of).toList()
            );
        }
    }


}
