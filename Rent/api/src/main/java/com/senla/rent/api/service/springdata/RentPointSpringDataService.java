package com.senla.rent.api.service.springdata;

import com.senla.rent.api.dto.projections.RentPointProj;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.entity.RentPoint;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentPointSpringDataService {
    List<RentPointWithTownDTO> getAllPoints(Pageable page);
    RentPointWithTownDTO findByAddress(String address);
    RentPointWithTownDTO findByIdFetchTown(Integer id);
    RentPointWithTownDTO findByIdNative(Integer id);
    RentPointWithTownDTO findWithSpec();
    RentPointProj getProj(Integer id);
}
