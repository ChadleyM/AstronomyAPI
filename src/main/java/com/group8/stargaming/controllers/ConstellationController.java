package com.group8.stargaming.controllers;

import java.util.List;

import com.group8.stargaming.models.Constellation;
import com.group8.stargaming.models.ConstellationEdge;
import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.repositories.ConstellationRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.group8.stargaming.models.Image;
import com.group8.stargaming.repositories.ConstellationImageRepository;

@RestController
@RequestMapping("/constellation")
public class ConstellationController {

    @Autowired
    private final ConstellationRepository repository;

    @Autowired
    private ConstellationRepository repository;

    @Autowired
    private ConstellationImageRepository imageRepository;


    @GetMapping("/")
    List<Constellation> all() {
        return repository.findAll();
    }

    @GetMapping("/{name}")
    Constellation one(@PathVariable String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Could not find constellation: " + name));
    }

    @GetMapping("/mapping")
    void mapping(@RequestBody ConstellationMapping body) {
//        List<Constellation> constellations = repository.findAllByStars(body.getStarList())
//                .orElseThrow(() -> new RuntimeException("No constellation to map"));


    }

    @GetMapping("/validate")
    void validate(@RequestBody ConstellationValidate body) {
//        Constellation constellation = repository.findByEdgeList(body.getEdgeList())
//                .orElseThrow(() -> new RuntimeException("You did not draw it correctly"));
//
//        System.out.println(constellation);
    }


    @Data
    private static class ConstellationMapping {
        private List<StarDetails> starList;

        public ConstellationMapping() {}

        public ConstellationMapping(List<StarDetails> starList) {
            this.starList = starList;
        }
    }

    @Data
    private static class ConstellationValidate {
        private List<ConstellationEdge> edgeList;

        public ConstellationValidate() {}

        public ConstellationValidate(List<ConstellationEdge> edgeList) {
            this.edgeList = edgeList;
        }
    }
    
    @GetMapping("/image/{name}")
    Image one(@PathVariable String name) {
        return imageRepository.findByName(name).orElseThrow(() -> new RuntimeException("Could not find constellation " + name));
    }

}
