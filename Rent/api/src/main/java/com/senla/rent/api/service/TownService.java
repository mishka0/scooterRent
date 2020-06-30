package com.senla.rent.api.service;

import com.senla.rent.api.dto.town.TownDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;

import java.util.List;

public interface TownService {
    List<TownWithRentPointDTO> getTowns();

    TownWithRentPointDTO getTownInfo(Integer id);
}
