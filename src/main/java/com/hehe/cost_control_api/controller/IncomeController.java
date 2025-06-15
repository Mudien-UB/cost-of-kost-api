package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.IncomeRequest;
import com.hehe.cost_control_api.dto.response.ExpenseResponse;
import com.hehe.cost_control_api.dto.response.IncomeResponse;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.ExpenseSortType;
import com.hehe.cost_control_api.model.enums.IncomeSortType;
import com.hehe.cost_control_api.service.IncomeService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/income")
@CrossOrigin
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addIncome(@Validated @RequestBody IncomeRequest incomeRequest) {

        Users user = userService.getFromContext();

        Income income = incomeService.addIncome(
                incomeRequest.getSource(),
                incomeRequest.getAmount(),
                incomeRequest.getCategoryName(),
                LocalDate.parse(incomeRequest.getIncomeDate()),
                user,
                incomeRequest.getNote()
        );
        if(income == null){
            return BaseResponseUtil.buildResponse(HttpStatus.BAD_REQUEST,"invalid some request", null);
        }

        return BaseResponseUtil.buildResponse(HttpStatus.OK,"success", IncomeResponse.of(income));

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

        IncomeSortType sortType = switch (sortBy) {
            case "amount" -> IncomeSortType.AMOUNT;
            case "create_time" -> IncomeSortType.CREATE_TIME;
            case "income_date" -> IncomeSortType.INCOME_DATE;
            default -> IncomeSortType.INCOME_DATE;
        };

        Page<Income> response = incomeService.listIncome(
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
                    response.getContent().stream().map(IncomeResponse::of).toList(),
                    response.getNumber() + 1,
                    response.getSize(),
                    response.getTotalElements(),
                    response.getTotalPages()
            );
        }
    }


}
