package com.group8.stargaming.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group8.stargaming.models.PlanetDetails;
import com.group8.stargaming.models.StarDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.group8.stargaming.models.MoonTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MoonTrackerCalculations {

        private final static String URL = "https://api.astronomyapi.com/api/v2/bodies/positions/{body}?latitude={latitude}&longitude={longitude}&elevation={elevation}&from_date={date}&to_date={date}&time={time}";
        private final static String API_KEY = System.getenv("astronomyAPIKey");

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private ObjectMapper objectMapper;


        public Optional<MoonTracker> findMoon(String name, double latitude, double longitude, String date, String time) throws JsonProcessingException {
                Map<String, Object> requestParams = packageRequestParams(name, latitude, longitude, date, time);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", API_KEY);
                HttpEntity<String> request = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, request, String.class, requestParams);
                Map<String, Object> jsonResponseBody = objectMapper.readValue(response.getBody(), Map.class);

                Optional<MoonTracker> moonDetails = buildMoonDetails(jsonResponseBody);
                return moonDetails;
        }

        private Optional<MoonTracker> buildMoonDetails(Map<String, Object> jsonResponseBody) {
                try {

                        Map<String, Object> data = (Map<String, Object>) jsonResponseBody.get("data");
                        Map<String, Object> table = (Map<String, Object>) data.get("table");
                        ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) table.get("rows");
                        Map<String, Object> row = rows.get(0);
                        ArrayList<Map<String, Object>> cells = (ArrayList<Map<String, Object>>) row.get("cells");
                        Map<String, Object> cell = cells.get(0);
                        Map<String, Object> extraInfo = (Map<String, Object>) cell.get("extraInfo");
                        Map<String, Object> phase = (Map<String, Object>) extraInfo.get("phase");


                        String phaseName = phase.get("string").toString();


                        double phaseValNum = Double.parseDouble((String) phase.get("fraction"));

                        MoonTracker moonTracker = new MoonTracker(phaseValNum, phaseName);
                        return Optional.of(moonTracker);


                } catch (NullPointerException e) {
                        System.err.println("The returned response was not expected.");
                        return Optional.of(new MoonTracker(0.0, "NA"));
                }


        }

        private Map<String, Object> packageRequestParams(String name, double latitude, double longitude, String date, String time) {
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("latitude", latitude);
                paramsMap.put("longitude", longitude);
                paramsMap.put("date", date);
                paramsMap.put("time", time);
                paramsMap.put("elevation", 20);
                paramsMap.put("body", name);
                return paramsMap;
        }

}