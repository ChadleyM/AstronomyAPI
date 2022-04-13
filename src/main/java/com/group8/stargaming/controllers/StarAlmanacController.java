package com.group8.stargaming.controllers;

import java.util.List;
import java.util.Optional;

import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.models.StarPosition;
import com.group8.stargaming.services.StarCalculations;
import com.group8.stargaming.services.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.repositories.StarAlmanacRepository;

@RestController
@RequestMapping("/almanac")
public class StarAlmanacController {

    @Autowired
    ValidationTools validationTools;

    @Autowired
    StarCalculations starCalculations;

    @GetMapping("/starPosition")
    public ResponseEntity<Object> one(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                      @RequestParam Double latitude, @RequestParam Double longitude) {
//        if (!validationTools.isValidDate(date)) {
//            return new ResponseEntity<>("Invalid date specified. A date for the year 2022 is expected in the format: yyyy-mm-dd", HttpStatus.BAD_REQUEST);
//        }
        StarDetailsID starID = new StarDetailsID(date, name);
        Optional<StarDetails> starPosition = starCalculations.findStar(starID, time, latitude, longitude);
        if (starPosition.isPresent())
            return new ResponseEntity<>(starPosition, HttpStatus.OK);
        else
            return new ResponseEntity<>("The position of the requested star can not be calculated", HttpStatus.BAD_REQUEST);
    }
}
