package com.group8.stargaming.controllers;

import java.util.List;
import java.util.Optional;

import com.group8.stargaming.models.StarDetailsID;
import com.group8.stargaming.utils.ValidationTools;
import org.springframework.web.bind.annotation.*;

import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.repositories.StarAlmanacRepository;

@RestController
@RequestMapping("/almanac")
public class StarAlmanacController {
    private final StarAlmanacRepository repository;

    StarAlmanacController(StarAlmanacRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/find")
    List<StarDetails> all() {
        return repository.findAll();
    }

    @GetMapping("/starPosition")
    public Optional<StarDetails> one(@RequestParam String name, @RequestParam String date) {
    	boolean isValidDate = ValidationTools.isValidDate(date);
        if (!isValidDate) {
            return null;
        }
        StarDetailsID starID = new StarDetailsID(date, name);

        return repository.findById(starID);
    }
}
