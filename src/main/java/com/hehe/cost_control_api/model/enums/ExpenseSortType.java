package com.hehe.cost_control_api.model.enums;

public enum ExpenseSortType {

    EXPENSE_DATE("expenseDate"),
    CREATE_TIME("createTime"),
    AMOUNT("amount");

    private final String name;

    ExpenseSortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
