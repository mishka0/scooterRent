package com.senla.rent.controller.admin;

import com.senla.rent.api.dto.rentalpoint.*;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.scooter.ScooterInfoDTO;
import com.senla.rent.api.service.RentPointService;
import com.senla.rent.api.service.ScooterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/admin/rentpoint")
public class AdminRentPointController {

    private final RentPointService rentPointService;
    private final ScooterService scooterService;

    public AdminRentPointController(RentPointService rentPointService, ScooterService scooterService) {
        this.rentPointService = rentPointService;
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<RentPointWithTownDTO> getRentPoints(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        return rentPointService.getAllPoints(page, limit);
    }

    @PostMapping
    public void addRentPoint(@RequestBody RentPointAddDTO rentDTO){
        rentPointService.addRentPoint(rentDTO);
    }
    
    @GetMapping("/{id}")
    public RentPointAllInfoDTO getRentPointByID(@PathVariable(value = "id") Integer id) {
        return rentPointService.getRentPointDetails(id);
    }

    @PutMapping("/{id}")
    public void editRentPoint(
            @PathVariable(value = "id") Integer id,
            @RequestBody RentPointEditDTO rentalPointDTO) {
        rentPointService.updateRentPoint(id, rentalPointDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRentPoint(@PathVariable(value = "id") Integer id){
        rentPointService.deleteRentPoint(id);
    }


    @GetMapping("/{id}/scooter")
    public List<ScooterInfoDTO> getScootersFromPoint(
            @PathVariable(value = "id") Integer id
    ) {
        return scooterService.getScootersFromPoint(id);
    }

    @PostMapping("{id}/scooter")
    public void addScooterToRentPoint(@PathVariable(value = "id") Integer id,  @RequestBody ScooterDTO scooterDTO){
        rentPointService.addScooterToPoint(id, scooterDTO);
    }
}
