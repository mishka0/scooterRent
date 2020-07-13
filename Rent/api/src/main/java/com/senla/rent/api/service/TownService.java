package com.senla.rent.api.service;

import com.senla.rent.api.dto.rentalpoint.RentPointDTO;
import com.senla.rent.api.dto.town.TownAddDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;

import java.util.List;

public interface TownService {
    List<TownWithRentPointDTO> getTowns(Integer page, Integer limit);

    TownWithRentPointDTO getTownInfo(Integer id);

    void addTown(TownAddDTO townDTO);

    void deleteTownByID(Integer id);

    void updateTown(Integer id, TownAddDTO townDTO);

    void addRentPointToTown(Integer id, RentPointDTO rentPointDTO);
}
