package com.group8.stargaming.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group8.stargaming.models.PlanetDetails;
import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.models.StarName;
import com.group8.stargaming.repositories.StarNameRepository;
import com.group8.stargaming.services.PlanetCalculations;
import com.group8.stargaming.services.StarCalculations;
import com.group8.stargaming.services.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.group8.stargaming.models.StarDetails;

@RestController
@RequestMapping("/v1")
public class GameController {
    @Autowired
    ValidationTools validationTools;
    @Autowired
    StarCalculations starCalculations;

    @Autowired
    PlanetCalculations planetCalculations;

    @Autowired
    private final StarNameRepository repository;

    GameController(StarNameRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/startGame")
    public ResponseEntity<Object> startgame(@RequestParam String date, @RequestParam String time,
                                            @RequestParam double latitude, @RequestParam double longitude) {
        if (!validationTools.isValidDate(date, time)) {
            return new ResponseEntity<>("Invalid date specified. A date for the year 2022 is expected in the format: yyyy-mm-dd", HttpStatus.BAD_REQUEST);
        }
        int number = 4;
        Optional<StarName> name = StarNameRepository.findById(number);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/guessStar")
    public ResponseEntity<Object> guessStar(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                            @RequestParam double latitude, @RequestParam double longitude,
                                            @RequestParam Optional<Double> obsAltitude, @RequestParam Optional<Double> obsAzimuth) {

        if (!validationTools.isValidDate(date, time)) {
            return new ResponseEntity<>("Invalid date specified. A date for the year 2022 is expected in the format: yyyy-mm-dd", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);
        StarDetailsID starID = new StarDetailsID(date, name);
        Optional<StarDetails> starPosition = starCalculations.findStar(starID, dateTime, latitude, longitude, obsAltitude, obsAzimuth);

        if(starPosition.isPresent()){
            Double azimithCorrection = starPosition.get().getAzimuthCorrection();
            Double altitudeCorrectionCorrection = starPosition.get().getAltitudeCorrection();

            if((azimithCorrection < -2 || azimithCorrection > 2) && (altitudeCorrectionCorrection < -2 || altitudeCorrectionCorrection > 2)){
                return new ResponseEntity<>("Incorrect try again ", HttpStatus.OK);
            }
            return new ResponseEntity<>("Hooray you found the star", HttpStatus.OK);
        }
        return new ResponseEntity<>("The position of the requested star can not be calculated", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/guessPlanet")
    public ResponseEntity<Object> guessPlanet(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                              @RequestParam double latitude, @RequestParam double longitude) {

        if (!validationTools.isValidDate(date, time)) {
            return new ResponseEntity<>("Invalid date specified. A date for the year 2022 is expected in the format: yyyy-mm-dd", HttpStatus.BAD_REQUEST);
        }

        try{
            Optional<PlanetDetails> planetDetails = planetCalculations.findPlanet(name, latitude, longitude, date, time);
            if(planetDetails.isPresent()){
                Double azimithCorrection = planetDetails.get().getAzimuthCorrection();
                Double altitudeCorrectionCorrection = planetDetails.get().getAltitudeCorrection();

                if((azimithCorrection < -2 || azimithCorrection > 2) && (altitudeCorrectionCorrection < -2 || altitudeCorrectionCorrection > 2)){
                    return new ResponseEntity<>("Incorrect try again ", HttpStatus.OK);
                }
                return new ResponseEntity<>("Hooray you found the star", HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("3rd party API bad request", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>("The position of the requested planet can not be calculated", HttpStatus.BAD_REQUEST);
    }
}
