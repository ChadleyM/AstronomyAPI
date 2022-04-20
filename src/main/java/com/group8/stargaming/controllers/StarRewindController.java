package com.group8.stargaming.controllers;

import com.group8.stargaming.models.StarRewind;
import com.group8.stargaming.repositories.StarRewindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/StarRewind")
public class StarRewindController {
    @Autowired
    StarRewindRepository starRewindRepository;

    @GetMapping("/")
    public Optional<List<StarRewind>> getDateInfo(@RequestParam String date){
        List<StarRewind> list = starRewindRepository.findAllByDate(date);
        return Optional.of(list);
    }
}
