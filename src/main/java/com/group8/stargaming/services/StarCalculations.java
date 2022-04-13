package com.group8.stargaming.services;

import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.repositories.StarAlmanacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StarCalculations {
    @Autowired
    StarAlmanacRepository starAlmanacRepository;

    private final double YEAR_OFFSET = 8034.5;
    private final double ARIES_OFFSET = 101.445647;
    private final int[] MONTH_OFFSET = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public Optional<StarDetails> findStar(StarDetailsID starDetailsID, String time, Double latitude, Double longitude) {
        Optional<StarDetails> starDetails = starAlmanacRepository.findById(starDetailsID);
        double dateOffset = calculatedateOffset(starDetailsID.getDate(), time);
        double calculateLST = calculatedateLST(dateOffset, longitude);
        return starDetails;
    }

    private double calculatedateLST(double dateOffset, Double longitude) {
        return 0;
    }

    private double calculatedateOffset(String date, String time) {
        int monthIndex = Integer.parseInt(date.split("-")[1]);
        int days = Integer.parseInt(date.split("-")[2]);
        double minutes = (double) Integer.parseInt(time.split(":")[1]);
        double hours = (double) Integer.parseInt(time.split(":")[0]);
        double fractionalDay = (hours + minutes / 60) / 24;
        return fractionalDay + MONTH_OFFSET[monthIndex] + days + YEAR_OFFSET;
    }
}
