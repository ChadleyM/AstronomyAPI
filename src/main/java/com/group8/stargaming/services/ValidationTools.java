package com.group8.stargaming.services;

import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ValidationTools {
    private static final LocalDateTime startDate = LocalDateTime.parse("2021-12-31T23:59:59");
    private static final LocalDateTime endDate = LocalDateTime.parse("2023-01-01T00:00:00");

    public boolean isValidDate(String date, String time) {
        try {
            LocalDateTime dateObject = LocalDateTime.parse(date + "T" + time);
            return dateObject.isAfter(startDate) && dateObject.isBefore(endDate);
        } catch (DateTimeException e) {
            return false;
        }
    }

    public boolean isValidObsInput(Optional<Double> obsAltitude, Optional<Double> obsAzimuth) {
        if (obsAltitude.isPresent() && obsAzimuth.isPresent()) {
            return obsAltitude.get() >= 0 && obsAltitude.get() <= 90 && obsAzimuth.get() >= 0 && obsAzimuth.get() <= 360;
        } else {
            return false;
        }
    }
}
