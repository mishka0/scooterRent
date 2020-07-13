package com.senla.rent.api.service;

import com.senla.rent.api.dto.scooter.ScooterAddDTO;
import com.senla.rent.api.dto.scooter.ScooterEditDTO;
import com.senla.rent.api.dto.scooter.ScooterFullInfoDTO;
import com.senla.rent.api.dto.scooter.ScooterInfoDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.entity.Scooter;

import java.util.List;

public interface ScooterService {

    ScooterFullInfoDTO getScooterInfo(Integer id);

    List<ScooterInfoDTO> getScootersFromPoint(Integer idPoint);

    List<ScooterFullInfoDTO> getScooters(Integer page, Integer limit);

    void addScooter(ScooterAddDTO scooterAddDTO);

    void updateScooter(Integer id, ScooterEditDTO scooterInfoDTO);

    void deleteScooter(Integer id);

    void setStatusScooter(Integer id, StatusScooterDTO statusScooterDTO);

    Scooter getScooter(Integer id);

    Scooter getScooterWithHistory(Integer id);

    List<ScooterInfoDTO> getStayScootersFromPoint(Integer idPoint);
}
