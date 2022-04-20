package com.group8.stargaming.controllers;

import com.algorithmia.APIException;
import com.algorithmia.AlgorithmException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.group8.stargaming.models.MoonTracker;
import com.group8.stargaming.services.MoonTrackerCalculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/moonTracker")
public class MoonTrackerController {

    @Autowired
    MoonTrackerCalculations moonTrackerCalculations;

    @GetMapping("/moonPhase")
    public ResponseEntity<Object> moonPhase(@RequestParam String date,
                                            @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String time) throws JsonProcessingException, APIException, AlgorithmException {

        Optional<MoonTracker> moonTracker = moonTrackerCalculations.findMoon("moon", latitude,longitude,date,time);
        return  new ResponseEntity<>(moonTracker, HttpStatus.OK);
    }


}
