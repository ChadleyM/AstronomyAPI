package com.group8.stargaming.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group8.stargaming.models.PlanetDetails;
import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.services.PlanetCalculations;
import com.group8.stargaming.services.StarCalculations;
import com.group8.stargaming.services.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.group8.stargaming.models.StarDetails;

@RestController
@RequestMapping("/v1")
public class StarAlmanacController {

    @Autowired
    ValidationTools validationTools;

    @Autowired
    StarCalculations starCalculations;

    @Autowired
    PlanetCalculations planetCalculations;

    @GetMapping("/starPosition")
    public ResponseEntity<Object> starPosition(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                      @RequestParam double latitude, @RequestParam double longitude,
                                      @RequestParam Optional<Double> obsAltitude, @RequestParam Optional<Double> obsAzimuth) {
        if (!validationTools.isValidDate(date, time)) {
            return new ResponseEntity<>("Invalid date specified. A date for the year 2022 is expected in the format: yyyy-mm-dd", HttpStatus.BAD_REQUEST);
        }

        if (obsAltitude.isPresent() ^ obsAzimuth.isPresent() ) {
            return new ResponseEntity<>("Both an altitude and azimuth must be supplied to use star finding service", HttpStatus.BAD_REQUEST);
        } else if (!validationTools.isValidObsInput(obsAltitude, obsAzimuth)) {
            return new ResponseEntity<>("Incorrect ranges for observer altitude or azimuth. Altitude is in the range of [0, 90] and Azimuth is in the range of [-180, 180]", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);
        StarDetailsID starID = new StarDetailsID(date, name);
        Optional<StarDetails> starPosition = starCalculations.findStar(starID, dateTime, latitude, longitude, obsAltitude, obsAzimuth);

        if (starPosition.isPresent())
            return new ResponseEntity<>(starPosition, HttpStatus.OK);
        else
            return new ResponseEntity<>("The position of the requested star can not be calculated", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/planetPosition")
    public ResponseEntity<Object> planetPosition(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                               @RequestParam Double latitude, @RequestParam Double longitude,
                                               @RequestParam Optional<Double> obsAltitude, @RequestParam Optional<Double> obsAzimuth) throws JsonProcessingException {

        Optional<PlanetDetails> planetDetails = planetCalculations.findPlanet(name, latitude, longitude, date, time);
        return new ResponseEntity<>(planetDetails, HttpStatus.OK);
    }
}
