package com.group8.stargaming.services;

import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class ValidationTools {
    private static final LocalDate startDate = LocalDate.parse("2021-12-31");
    private static final LocalDate endDate = LocalDate.parse("2023-01-01");

    public boolean isValidDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            return date.isAfter(startDate) && date.isBefore(endDate);
        } catch (DateTimeException e) {
            return false;
        }
    }
}
