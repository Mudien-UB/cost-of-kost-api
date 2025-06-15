package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.ExpenseRequest;
import com.hehe.cost_control_api.dto.response.ExpenseResponse;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.ExpenseSortType;
import com.hehe.cost_control_api.service.ExpenseService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/expense")
@RequiredArgsConstructor
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequest expenseRequest) {

        Users user = userService.getFromContext();


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

    @GetMapping()
    public ResponseEntity<?> getExpense(
            @RequestParam(required = false, defaultValue = "") String sortBy,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to,

            @RequestParam(required = false, defaultValue = "") String categoryName,

            @RequestParam(required = false, defaultValue = "1")
            @Min(1) Integer page,

            @RequestParam(required = false, defaultValue = "10")
            @Min(5) Integer size,

            @RequestParam(required = false, defaultValue = "true") Boolean asc

    ) {

        Users user = userService.getFromContext();

        ExpenseSortType  sortType = switch (sortBy) {
            case "amount" -> ExpenseSortType.AMOUNT;
            case "create_time" -> ExpenseSortType.CREATE_TIME;
            case "expense_date" -> ExpenseSortType.EXPENSE_DATE;
            default -> ExpenseSortType.EXPENSE_DATE;
        };

        Page<Expense> response = expenseService.listExpense(
                user,
                sortType,
                from,
                to,
                categoryName,
                page - 1 ,
                size,
                asc
        );

        if(response.getContent().isEmpty()) {
            return BaseResponseUtil.buildResponsePageable(
                    HttpStatus.OK,
                    "empty",
                    null,
                    0,
                    0,
                    0L,
                    0
            );
        }else{
            return BaseResponseUtil.buildResponsePageable(
                    HttpStatus.OK,
                    "list expenses retrieved",
                    response.getContent().stream().map(ExpenseResponse::of).toList(),
                    response.getNumber() + 1,
                    response.getSize(),
                    response.getTotalElements(),
                    response.getTotalPages()
            );
        }
    }


}
