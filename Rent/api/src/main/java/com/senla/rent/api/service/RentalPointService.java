package com.senla.rent.api.service;

import com.senla.rent.api.dto.rentalpoint.RentalPointDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointWithTownDTO;

import java.util.List;

public interface RentalPointService {

    List<RentalPointWithTownDTO> getAllPoints(int page, int limit);

    RentalPointAllInfoDTO getRentPointDetails(Integer idPoint);
}
