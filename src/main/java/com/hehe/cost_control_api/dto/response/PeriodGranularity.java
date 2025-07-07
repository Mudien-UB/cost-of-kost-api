package com.hehe.cost_control_api.dto.response;


public record PeriodGranularity(
        String period,
        Double total
) {

    public static PeriodGranularity of(Object[] value) {
        if(value == null || value.length == 0){
            return null;
        }
        return new PeriodGranularity(String.valueOf(value[0]), (Double) value[1]);
    }
}
