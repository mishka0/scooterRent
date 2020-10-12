package com.senla.rent.api.service;

import com.senla.rent.api.dto.rentalpoint.RentPointAddDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointEditDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.entity.RentPoint;

import java.util.List;

public interface RentPointService {

    List<RentPointWithTownDTO> getAllPoints(Integer page, Integer limit);

    List<RentPointWithTownDTO> getAllPointsJdbcTemplate(Integer page, Integer limit);

    RentPointAllInfoDTO getRentPointDetails(Integer id);

    void updateRentPoint(Integer id, RentPointEditDTO rentalPointDTO);

    void deleteRentPoint(Integer id);

    void addScooterToPoint(Integer id, ScooterDTO scooterDTO);

    RentPoint getRentPoint(Integer id);

    void addRentPoint(RentPointAddDTO rentDTO);
}
