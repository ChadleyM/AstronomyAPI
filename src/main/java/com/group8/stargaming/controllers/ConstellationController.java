package com.group8.stargaming.controllers;

import java.util.List;

import com.group8.stargaming.Exceptions.ConstellationNotFoundException;
import com.group8.stargaming.models.Constellation;
import com.group8.stargaming.repositories.ConstellationRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constellation")
public class ConstellationController {
    
    private final ConstellationRepository repository;

    ConstellationController(ConstellationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    List<Constellation> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Constellation one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ConstellationNotFoundException(id));
    }
}
