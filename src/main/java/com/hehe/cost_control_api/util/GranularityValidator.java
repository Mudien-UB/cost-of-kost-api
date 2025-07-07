package com.hehe.cost_control_api.util;

import com.hehe.cost_control_api.exception.InvalidGranularityException;
import com.hehe.cost_control_api.model.enums.Granularity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GranularityValidator {

    public static Granularity validateGranularity(LocalDate from, LocalDate to, Granularity requestedGranularity) {
        long daysBetween = ChronoUnit.DAYS.between(from, to);

        switch (requestedGranularity) {
            case DAILY -> {
                if (daysBetween > 31) throw new InvalidGranularityException("Granularity DAILY hanya untuk range <= 31 hari");
                return Granularity.DAILY;
            }
            case WEEKLY -> {
                if (daysBetween > 120) throw new InvalidGranularityException("Granularity WEEKLY hanya untuk range <= 120 hari");
                return Granularity.WEEKLY;
            }
            case MONTHLY -> {
                return Granularity.MONTHLY;
            }
            default -> throw new InvalidGranularityException("Granularity tidak dikenali");
        }
    }

    public static Granularity fallbackGranularity(LocalDate from, LocalDate to) {
        long days = ChronoUnit.DAYS.between(from, to);

        if (days <= 31) return Granularity.DAILY;
        if (days <= 120) return Granularity.WEEKLY;
        return Granularity.MONTHLY;
    }
}

