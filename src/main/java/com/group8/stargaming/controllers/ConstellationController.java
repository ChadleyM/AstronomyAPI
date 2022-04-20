package com.group8.stargaming.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.group8.stargaming.models.Constellation;
import com.group8.stargaming.models.ConstellationEdge;
import com.group8.stargaming.models.StarDetails;
import com.group8.stargaming.repositories.ConstellationRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/constellation")
public class ConstellationController {

    @Autowired
    private final ConstellationRepository repository;

    ConstellationController(ConstellationRepository repository) {
        this.repository = repository;
    }

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
    List<Constellation> mapping(@RequestBody ConstellationMapping body) {
        return repository.findAll().stream()
                .filter((c) -> c.starListContainsEdges(body.getStarList()))
                .collect(Collectors.toList());
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
    
}
