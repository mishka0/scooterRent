package com.senla.rent.controller.moder;

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
@RequestMapping("/moder/town")
public class ModerTownController {

    private final TownService townService;

    public ModerTownController(TownService townService) {
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
    public ResponseEntity<TownAddDTO> addTown(@RequestBody TownAddDTO townDTO) {
        townService.addTown(townDTO);
        return ResponseEntity.ok(townDTO);
    }

    @GetMapping("/{id}")
    public TownWithRentPointDTO getTownWithPoints(@PathVariable(value = "id")Integer id){
        return townService.getTownInfo(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTown(@PathVariable(value = "id") Integer id) {
        townService.deleteTownByID(id);
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
