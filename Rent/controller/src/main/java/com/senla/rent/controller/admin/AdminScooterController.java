package com.senla.rent.controller.admin;

import com.senla.rent.api.dto.history.HistoryWithUserDTO;
import com.senla.rent.api.dto.scooter.ScooterAddDTO;
import com.senla.rent.api.dto.scooter.ScooterEditDTO;
import com.senla.rent.api.dto.scooter.ScooterFullInfoDTO;
import com.senla.rent.api.dto.scooter.ScooterInfoDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.api.service.HistoryService;
import com.senla.rent.api.service.ScooterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/scooter")
public class AdminScooterController {
    private final ScooterService scooterService;
    private final HistoryService historyService;

    public AdminScooterController(ScooterService scooterService, HistoryService historyService) {
        this.scooterService = scooterService;
        this.historyService = historyService;
    }

    @GetMapping
    public List<ScooterFullInfoDTO> getScooters(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit
    ) {
        return scooterService.getScooters(page, limit);
    }

    @PostMapping
    public void addScooter(@RequestBody ScooterAddDTO scooterAddDTO) {
        scooterService.addScooter(scooterAddDTO);
    }

    @GetMapping("/{id}")
    public ScooterFullInfoDTO getScooterByID(
            @PathVariable(value = "id") Integer id
    ) {
        return scooterService.getScooterInfo(id);
    }

    @PutMapping("/{id}")
    public void editScooter(
            @PathVariable(value = "id") Integer id,
            @RequestBody ScooterEditDTO scooterInfoDTO) {
        scooterService.updateScooter(id, scooterInfoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteScooter(@PathVariable(value = "id") Integer id) {
        scooterService.deleteScooter(id);
    }

    @PostMapping("/{id}/status")
    public void setStatusScooter(@PathVariable(value = "id") Integer id, @RequestBody StatusScooterDTO statusScooterDTO) {
        scooterService.setStatusScooter(id, statusScooterDTO);
    }

    @GetMapping("/{id}/history")
    public List<HistoryWithUserDTO> getHistoryScooter(@PathVariable(value = "id") Integer id){
        return historyService.getScooterHistory(id);
    }

}
