package com.group8.stargaming.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
                .filter((c) -> c.starListContainsEdges(body.getStarListIDs()))
                .collect(Collectors.toList());
    }

    @GetMapping("/validate/{name}")
    boolean validate(@RequestBody ConstellationValidate body, @PathVariable String name) {
        Constellation constellation = repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Constellation not found"));

        boolean match;
        for (ConstellationEdge ce: constellation.getEdgeList()) {
            match = false;
            for (EdgeRequest er : body.getEdgeList()) {
                if (ce.getStar1().getId().equals(er.vertex1) && ce.getStar2().getId().equals(er.vertex2)
                        || ce.getStar1().getId().equals(er.vertex2) && ce.getStar2().getId().equals(er.vertex1)) {
                    match = true;
                }
            }

            if (!match) {
                return false;
            }
        }

        return true;
    }

    @GetMapping("/test")
    void test(@RequestBody TestBody body) {
        System.out.println(body.test);
    }


    @Data
    private static class ConstellationMapping {
        private List<Long> starListIDs;

        public ConstellationMapping() {}

        public ConstellationMapping(List<Long> starListIDs) {
            this.starListIDs = starListIDs;
        }
    }

    @Data
    private static class ConstellationValidate {
        private List<EdgeRequest> edgeList;

        ConstellationValidate() {}

        ConstellationValidate(List<EdgeRequest> edgeList) {
            this.edgeList = edgeList;
        }
    }

    @Data
    private static class EdgeRequest {
        private Long vertex1;
        private Long vertex2;

        EdgeRequest() {}

        public EdgeRequest(Long vertex1, Long vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    @Data
    private static class TestBody {
        private List<String> test;
    }
    
}
