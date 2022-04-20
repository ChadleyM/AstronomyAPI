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
import com.group8.stargaming.models.Image;
import com.group8.stargaming.repositories.ConstellationImageRepository;

@RestController
@RequestMapping("/constellation")
public class ConstellationController {

}
