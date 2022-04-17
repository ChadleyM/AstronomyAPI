package com.group8.stargaming.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.models.StarPosition;
import com.group8.stargaming.services.StarCalculations;
import com.group8.stargaming.services.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.repositories.StarAlmanacRepository;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/almanac")
public class StarAlmanacController {

    @Autowired
    ValidationTools validationTools;

    @Autowired
    StarCalculations starCalculations;

    @GetMapping("/starPosition")
    public ResponseEntity<Object> starPosition(@RequestParam String name, @RequestParam String date, @RequestParam String time,
                                      @RequestParam Double latitude, @RequestParam Double longitude,
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
        String url = "https://api.astronomyapi.com/api/v2/bodies/positions/sun?latitude=-33.92927&longitude=18.67222&elevation=20&from_date=2022-04-07&to_date=2022-04-07&time=08:05:00";
        RestTemplate restTemplate = new RestTemplate();
        String authToken = "Basic OGMwOTYwNjctMmU2MS00NjU5LThmZmEtYWU2MWNiMzFjOTI1OjJjMjFjNDM5OWVhNjQ3ZTVlMDA2NTZjNDkzYjk4NjQzNGFkZjEyMWUyODRmOGYzNmU1OGQxZTFmODg4NGNiNmI1N2QyODc0MjQ4OTIwOGUxMGJiZTU4OGVhOGM2ZjRlNjNhZjY0MDg2Nzc5NjE4NTY3ZWI5NWUwMWE1M2U0NDE2MjcxOTExYzNjYmQ4OGVlYWUxZmNkYzcyYjk5NTFkOGQwOWZmMDRmMmNiYzQ5NWQ5MmUyOTc0MmY5YmJlOTFiYzc5NTFmZDRhNTMzYjcxY2M3ZDUzYjllOTU0ZWQ3MGQz";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> a = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(a.getBody(), Map.class);
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> table = (Map<String, Object>) data.get("table");
        System.out.println((ArrayList<String>) table.get("header"));
        return new ResponseEntity<>(a.getBody(), HttpStatus.OK);
    }
}
