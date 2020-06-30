package com.senla.rent.controller;

import com.senla.rent.api.dto.town.TownDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;
import com.senla.rent.api.service.TownService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {

    private final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public List<TownWithRentPointDTO> getTownsWithRentPoint(){
        return townService.getTowns();
    }

    @GetMapping("/{id}")
    public TownWithRentPointDTO getTownInfo(@PathVariable(name = "id") Integer id){
        return townService.getTownInfo(id);
    }
}
