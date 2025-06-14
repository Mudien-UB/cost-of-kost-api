package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.IncomeRequest;
import com.hehe.cost_control_api.dto.response.IncomeResponse;
import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.IncomeService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
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

        if(user == null){
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED," unauthorized request", null);
        }

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


}
