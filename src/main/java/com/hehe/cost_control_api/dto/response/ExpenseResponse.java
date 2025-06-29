package com.hehe.cost_control_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hehe.cost_control_api.model.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseResponse(
        String id,
        String categoryName,
        String reason, Double amount,
        String note,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate expenseDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createTime
) {

    public static ExpenseResponse of(Expense expense) {
        if (expense == null) {
            return null;
        }
        return new ExpenseResponse(
                expense.getId().toString(),
                expense.getCategory().getName(),
                expense.getReason(),
                expense.getAmount(),
                expense.getNote() == null ? null : expense.getNote(),
                expense.getExpenseDate(),
                expense.getCreateTime()
        );
    }

}
