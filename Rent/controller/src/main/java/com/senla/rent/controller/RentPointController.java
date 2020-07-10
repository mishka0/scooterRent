package com.senla.rent.controller;

import com.senla.rent.api.dto.rentalpoint.RentPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.api.service.RentPointService;
import com.senla.rent.api.service.ScooterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentpoint")
public class RentPointController {

    private final RentPointService rentPointService;

    private final ScooterService scooterService;

    public RentPointController(RentPointService rentPointService, ScooterService scooterService) {
        this.rentPointService = rentPointService;
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<RentPointWithTownDTO> getAllPoints(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit){
        return rentPointService.getAllPoints(page, limit);
    }

    @GetMapping("/{id}")
    public RentPointAllInfoDTO getScootersFromPoint(@PathVariable(value = "id") Integer idPoint){
        return rentPointService.getRentPointDetails(idPoint);
    }
}
