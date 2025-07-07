package com.hehe.cost_control_api.model.enums;

import lombok.Getter;

@Getter
public enum Granularity {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY");

    private final String name;

    Granularity(String name) {
        this.name = name;
    }

}

