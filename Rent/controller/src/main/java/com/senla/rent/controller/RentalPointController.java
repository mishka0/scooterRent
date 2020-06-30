package com.senla.rent.controller;

import com.senla.rent.api.dto.rentalpoint.RentalPointDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointWithTownDTO;
import com.senla.rent.api.service.RentalPointService;
import com.senla.rent.api.service.ScooterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentpoint")
public class RentalPointController {

    private final RentalPointService rentalPointService;

    private final ScooterService scooterService;

    public RentalPointController(RentalPointService rentalPointService, ScooterService scooterService) {
        this.rentalPointService = rentalPointService;
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<RentalPointWithTownDTO> getAllPoints(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "30") int limit){
        return rentalPointService.getAllPoints(page, limit);
    }

    @GetMapping("/{id}")
    public RentalPointAllInfoDTO getScootersFromPoint(@PathVariable(value = "id") Integer idPoint){
        return rentalPointService.getRentPointDetails(idPoint);
    }
}
