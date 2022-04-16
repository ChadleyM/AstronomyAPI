package com.group8.stargaming.services;

import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.repositories.StarAlmanacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class StarCalculations {
    @Autowired
    StarAlmanacRepository starAlmanacRepository;

    private final double ARIES_OFFSET = 101.445647;
    private final int[] MONTH_OFFSET = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public Optional<StarDetails> findStar(StarDetailsID starDetailsID, LocalDateTime dateTime, double latitude,
                                          double longitude, Optional<Double> obsAltitude, Optional<Double> obsAzimuth) {
        Optional<StarDetails> starDetails = starAlmanacRepository.findById(starDetailsID);
        double dateOffset = calculateDateOffset(dateTime);
        double lst = calculateDateLST(dateOffset, longitude, dateTime);
        if (starDetails.isPresent()) {
            double rightAscension = 360 - starDetails.get().getGha();
            double hourAngle = lst - rightAscension;
            double altitude = calculateAltitude(starDetails.get().getDeclination(), latitude, hourAngle);
            double azimuth = calculateAZ(starDetails.get().getDeclination(), altitude, latitude, hourAngle);
            starDetails.get().setAltitude(altitude);
            starDetails.get().setAzimuth(azimuth);

            if (obsAltitude.isPresent() && obsAzimuth.isPresent()) {
                double altCorrection = starDetails.get().getAltitude() - obsAltitude.get();
                if (starDetails.get().getAltitude() > 0) {
                    starDetails.get().setAltitudeCorrection(altCorrection);
                } else {
                    starDetails.get().setAltitudeCorrection(-1.0);
                }
                double azCorrection = (starDetails.get().getAzimuth() - obsAzimuth.get()) % 180;
                starDetails.get().setAzimuthCorrection(azCorrection);
            }

            return starDetails;
        }
        return starDetails;
    }

    private double calculateAZ(double declination, double altitude, double latitude, double hourAngle) {
        double a =  Math.acos((Math.sin(declination) - Math.sin(altitude) * Math.sin(latitude)) / (Math.cos(altitude) * Math.cos(latitude)));
        if (Math.sin(hourAngle) < 0)
            return a;
        else
            return 360 - a;
    }

    private double calculateAltitude(double declination, double latitude, double hourAngle) {
        return Math.asin(Math.sin(declination) * Math.sin(latitude) + Math.cos(declination) * Math.cos(latitude) * Math.cos(hourAngle));
    }

    private double calculateDateLST(double dateOffset, Double longitude, LocalDateTime time) {
        double fractionalTime = time.getHour() + ((double) time.getMinute() + (double) time.getSecond() / 60) / 60;
        return ARIES_OFFSET * dateOffset + longitude + 15 * fractionalTime;
    }

    private double calculateDateOffset(LocalDateTime dateTime) {
        double year_offset = 8034.5;
        int sast_offset = 2;

        int monthIndex = dateTime.getMonthValue() - 1;
        int days = dateTime.getDayOfMonth();
        double minutes = dateTime.getMinute();
        double hours = dateTime.getHour() - sast_offset;
        if (hours > 0) {
            double fractionalDay = (hours + minutes / 60) / 24;
            return fractionalDay + MONTH_OFFSET[monthIndex] + days + year_offset;
        } else {
            hours = 24 + hours;
            days = days - 1;
            double fractionalDay = (hours + minutes / 60) / 24;
            return fractionalDay + MONTH_OFFSET[monthIndex] + days + year_offset;
        }
    }
}
