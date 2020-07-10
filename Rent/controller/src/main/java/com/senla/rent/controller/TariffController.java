package com.senla.rent.controller;

import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.api.service.TariffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
public class TariffController {
    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    public List<TariffDTO> getAllTariffs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit ){
        return tariffService.getAllTariffs(page, limit);
    }
}
