package com.senla.rent.controller.admin;

import com.senla.rent.api.dto.rentalpoint.RentPointDTO;
import com.senla.rent.api.dto.town.TownAddDTO;
import com.senla.rent.api.dto.town.TownDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;
import com.senla.rent.api.service.TownService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/town")
public class AdminTownController {

    private final TownService townService;

    public AdminTownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public List<TownWithRentPointDTO> getAllTowns(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit)
    {
        return townService.getTowns(page, limit);
    }

    @PostMapping
    public ResponseEntity addTown(@RequestBody TownAddDTO townDTO) {
        townService.addTown(townDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TownWithRentPointDTO getTownWithPoints(@PathVariable(value = "id")Integer id){
        return townService.getTownInfo(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTown(@PathVariable(value = "id") Integer id) {
        townService.deleteTownByID(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity editTown(@PathVariable(value = "id") Integer id, @RequestBody TownAddDTO townDTO) {
        townService.updateTown(id, townDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/rentpoint")
    public void setRentpointToTown(@PathVariable(value = "id") Integer id, @RequestBody RentPointDTO rentPointDTO) {
        townService.addRentPointToTown(id, rentPointDTO);
    }
}
