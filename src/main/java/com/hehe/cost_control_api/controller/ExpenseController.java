package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.ExpenseRequest;
import com.hehe.cost_control_api.dto.response.ExpenseResponse;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.ExpenseService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@Validated @RequestBody ExpenseRequest expenseRequest) {

        Users user = userService.getFromContext();
        if(user == null) {
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED, "unauthorized request", null);
        }

        Expense expense = expenseService.addExpense(
                expenseRequest.getReason(),
                expenseRequest.getAmount(),
                expenseRequest.getCategoryName(),
                LocalDate.parse(expenseRequest.getExpenseDate()),
                user,
                expenseRequest.getNote()
        );
        if(expense == null) {
            return BaseResponseUtil.buildResponse(HttpStatus.BAD_REQUEST, "bad request", null);
        }

        return BaseResponseUtil.buildResponse(HttpStatus.OK, "success", ExpenseResponse.of(expense));



    }


}
