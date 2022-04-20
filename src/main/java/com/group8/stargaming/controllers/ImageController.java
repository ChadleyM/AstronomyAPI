package com.group8.stargaming.controllers;

import com.group8.stargaming.models.Image;
import com.group8.stargaming.repositories.ConstellationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ImageController {

    private ConstellationImageRepository imageRepository;

    ImageController(ConstellationImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @GetMapping("/image/{name}")
    public Image getConstellationImage(@PathVariable Long name) {
        return imageRepository.findById(name).orElseThrow(() -> new RuntimeException("Could not find constellation " + name));
    }
}
