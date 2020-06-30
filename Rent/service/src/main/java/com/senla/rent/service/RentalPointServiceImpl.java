package com.senla.rent.service;

import com.senla.rent.api.dao.RentalPointRepository;
import com.senla.rent.api.dto.rentalpoint.RentalPointDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentalPointWithTownDTO;
import com.senla.rent.api.service.RentalPointService;
import com.senla.rent.entity.RentalPoint;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalPointServiceImpl implements RentalPointService {

    private final RentalPointRepository rentalPointRepository;

    private final ModelMapper modelMapper;

    public RentalPointServiceImpl(RentalPointRepository rentalPointRepository, ModelMapper modelMapper) {
        this.rentalPointRepository = rentalPointRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RentalPointWithTownDTO> getAllPoints(int page, int limit) {
        return rentalPointRepository.findAll(page, limit)
                .stream()
                .map(rentalPoint -> modelMapper.map(rentalPoint, RentalPointWithTownDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RentalPointAllInfoDTO getRentPointDetails(Integer idPoint) {
        RentalPoint rentalPoint = rentalPointRepository.getRentPointDetails(idPoint);
        RentalPointAllInfoDTO rentalPointDetailsDTO = modelMapper.map(rentalPoint, RentalPointAllInfoDTO.class);
        return rentalPointDetailsDTO;
    }
}
