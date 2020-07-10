package com.senla.rent.controller.moder;

import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.api.service.TariffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moder/tariff")
public class ModerTariffController {

    private final TariffService tariffService;

    public ModerTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    public List<TariffDTO> getAllTariffs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit ){
        return tariffService.getAllTariffs(page, limit);
    }

    @PostMapping
    public void addTariff(@RequestBody TariffEditDTO tariffDTO){
        tariffService.addTariff(tariffDTO);
    }

    @PutMapping("/{id}")
    public void editTariff(@PathVariable(value = "id") Integer id, @RequestBody TariffEditDTO tariffDTO){
        tariffService.updateTariff(id, tariffDTO);
    }
}
