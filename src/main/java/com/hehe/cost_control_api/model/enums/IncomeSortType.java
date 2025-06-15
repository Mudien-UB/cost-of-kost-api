package com.hehe.cost_control_api.model.enums;

public enum IncomeSortType {

    INCOME_DATE("incomeDate"),
    CREATE_TIME("createTime"),
    AMOUNT("amount");

    private final String name;

    IncomeSortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

