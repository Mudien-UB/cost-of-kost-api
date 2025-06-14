package com.hehe.cost_control_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class ExpenseRequest {

    @NotBlank(message = "categoryName cannot be blank")
    @Length(max = 100, message = "max character is 100")
    private String categoryName;

    @NotBlank(message = "reason cannot be blank")
    @Length(max = 100, message = "max character is 100")
    private String reason;

    @NotNull(message = "amount cannot be null")
    @Positive(message = "amount must be greater than 0")
    private Double amount;

    @Length(max = 200, message = "max character is 200")
    private String note;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "expenseDate must be in yyyy-MM-dd format example: 2020-12-21")
    private String expenseDate;

}
