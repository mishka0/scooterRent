package com.senla.rent.api.service;

import com.senla.rent.api.dto.statusscooter.StatusScooterAddDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.entity.StatusScooter;

import java.util.List;

public interface StatusScooterService {
    StatusScooter getByName(String name);

    List<StatusScooterDTO> getAllStatuses(Integer page, Integer limit);

    void updateStatus(Integer id, StatusScooterDTO statusScooterDTO);

    void addStatus(StatusScooterAddDTO statusScooterDTO);

    StatusScooter findById(Integer id);

    void deleteStatus(Integer id);
}
