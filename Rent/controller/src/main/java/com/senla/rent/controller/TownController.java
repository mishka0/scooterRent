package com.senla.rent.controller;

import com.senla.rent.api.dto.town.TownWithRentPointDTO;
import com.senla.rent.api.service.ScooterService;
import com.senla.rent.api.service.TownService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {

    private final TownService townService;
    private final ScooterService scooterService;

    public TownController(TownService townService, ScooterService scooterService) {
        this.townService = townService;
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<TownWithRentPointDTO> getTownsWithRentPoint(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return townService.getTowns(page, limit);
    }

    @GetMapping("/{id}")
    public TownWithRentPointDTO getTownInfo(
            @PathVariable(name = "id") Integer id
    ) {
        return townService.getTownInfo(id);
    }
}
