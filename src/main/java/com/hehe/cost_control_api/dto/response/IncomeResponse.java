package com.hehe.cost_control_api.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.hehe.cost_control_api.model.Income;

import java.time.LocalDate;

public record IncomeResponse(
        String id,
        String categoryName,
        String source, Double amount,
        String note,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate incomeDate
) {

    public static IncomeResponse of(Income income) {
        if (income == null) {
            return null;
        }
        return new IncomeResponse(
                income.getId().toString(),
                income.getCategory().getName(),
                income.getSource(),
                income.getAmount(),
                income.getNote() == null ? null : income.getNote(),
                income.getIncomeDate()
        );
    }



}

