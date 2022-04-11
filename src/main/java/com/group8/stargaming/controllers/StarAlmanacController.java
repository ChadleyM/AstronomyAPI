package com.group8.stargaming.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group8.stargaming.models.StarAlmanac;
import com.group8.stargaming.repositories.StarAlmanacRepository;

@RestController
@RequestMapping("/almanac")
public class StarAlmanacController {
    private final StarAlmanacRepository repository;

    StarAlmanacController(StarAlmanacRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/find")
    List<StarAlmanac> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    void one(@PathVariable Long id) {
    	StarAlmanac sa = new StarAlmanac("Jenny", "11/04/2022");
    	repository.save(sa);
        return;
    }
}
