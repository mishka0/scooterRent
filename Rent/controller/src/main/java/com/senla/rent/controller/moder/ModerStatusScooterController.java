package com.senla.rent.controller.moder;

import com.senla.rent.api.dto.statusscooter.StatusScooterAddDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.api.service.StatusScooterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moder/status")
public class ModerStatusScooterController {
    private final StatusScooterService statusScooterService;

    public ModerStatusScooterController(StatusScooterService statusScooterService) {
        this.statusScooterService = statusScooterService;
    }

    @GetMapping
    public List<StatusScooterDTO> getStatuses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return statusScooterService.getAllStatuses(page, limit);
    }

    @PutMapping("/{id}")
    public void editStatus(
            @PathVariable(value = "id") Integer id,
            @RequestBody StatusScooterDTO statusScooterDTO) {
        statusScooterService.updateStatus(id, statusScooterDTO);
    }

    @PostMapping
    public void addStatusScooter(@RequestBody StatusScooterAddDTO statusScooterDTO) {
        statusScooterService.addStatus(statusScooterDTO);
    }
}
