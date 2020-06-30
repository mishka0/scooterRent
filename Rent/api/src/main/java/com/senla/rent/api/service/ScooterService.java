package com.senla.rent.api.service;

import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.scooter.ScooterInfoDTO;

import java.util.List;

public interface ScooterService {
    ScooterDTO getScooterInfo(Integer id);

    List<ScooterInfoDTO> getScootersFromPoint(Integer idPoint);

}
