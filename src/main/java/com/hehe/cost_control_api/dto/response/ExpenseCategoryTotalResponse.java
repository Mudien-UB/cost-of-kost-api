package com.hehe.cost_control_api.dto.response;

public record ExpenseCategoryTotalResponse(
        String name,
        Double total
) {
    public static ExpenseCategoryTotalResponse of(Object[] value) {
        if(value == null || value.length == 0){
            return null;
        }
        return new ExpenseCategoryTotalResponse(String.valueOf(value[0]), (Double) value[1]);
    }
}
