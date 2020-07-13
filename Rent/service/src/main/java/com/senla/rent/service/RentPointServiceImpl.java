package com.senla.rent.service;

import com.senla.rent.api.dao.RentPointRepository;
import com.senla.rent.api.dto.rentalpoint.RentPointAddDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointAllInfoDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointEditDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.service.RentPointService;
import com.senla.rent.api.service.ScooterService;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.entity.Scooter;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RentPointServiceImpl implements RentPointService {

    private final RentPointRepository rentalPointRepository;
    private final ScooterService scooterService;
    private final ModelMapper modelMapper;

    public RentPointServiceImpl(RentPointRepository rentalPointRepository, ScooterService scooterService, ModelMapper modelMapper) {
        this.rentalPointRepository = rentalPointRepository;
        this.scooterService = scooterService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RentPointWithTownDTO> getAllPoints(Integer page, Integer limit) {
        try {
            return rentalPointRepository.findAll(page, limit)
                    .stream()
                    .map(rentalPoint -> modelMapper.map(rentalPoint, RentPointWithTownDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get rent points! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get rent points!");
        }
    }

    @Override
    public RentPointAllInfoDTO getRentPointDetails(Integer idPoint) {
        try {
            return modelMapper.map(rentalPointRepository.getRentPoint(idPoint), RentPointAllInfoDTO.class);
        } catch (RuntimeException exception) {
            log.error("Can't get rent point details! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get rent point details!");
        }
    }

    @Override
    public void updateRentPoint(Integer id, RentPointEditDTO rentalPointDTO) {
        try {
            RentPoint rentPointToUpdate = rentalPointRepository.getRentPoint(id);
            modelMapper.map(rentalPointDTO, rentPointToUpdate);
            rentalPointRepository.update(rentPointToUpdate);
        } catch (RuntimeException exception) {
            log.error("Can't update rent point! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't update rent point!");
        }
    }

    @Override
    public void deleteRentPoint(Integer id) {
        try {
            rentalPointRepository.delete(rentalPointRepository.findById(id));
        } catch (RuntimeException exception) {
            log.error("Can't delete rent point! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't delete rent point!");
        }
    }

    @Override
    public void addScooterToPoint(Integer id, ScooterDTO scooterDTO) {
        try {
            RentPoint rentPoint = rentalPointRepository.getRentPoint(id);
            Scooter scooter = scooterService.getScooter(scooterDTO.getId());
            scooter.setRentPoint(rentPoint);
        } catch (RuntimeException exception) {
            log.error("Can't add scooter to rent point! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add scooter to rent point!");
        }
    }

    @Override
    public RentPoint getRentPoint(Integer id) {
        try {
            return rentalPointRepository.getRentPoint(id);
        } catch (RuntimeException exception) {
            log.error("Can't get rent point! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get rent point!");
        }
    }

    @Override
    public void addRentPoint(RentPointAddDTO rentDTO) {
        try {
            rentalPointRepository.insert(modelMapper.map(rentDTO, RentPoint.class));
        } catch (RuntimeException exception) {
            log.error("Can't add rent point! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add rent point!");
        }
    }
}
