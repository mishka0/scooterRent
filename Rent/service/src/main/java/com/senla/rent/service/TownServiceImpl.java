package com.senla.rent.service;

import com.senla.rent.api.dao.TownRepository;
import com.senla.rent.api.dto.rentalpoint.RentPointDTO;
import com.senla.rent.api.dto.town.TownAddDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;
import com.senla.rent.api.service.RentPointService;
import com.senla.rent.api.service.TownService;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.entity.Town;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final RentPointService rentPointService;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, RentPointService rentPointService, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.rentPointService = rentPointService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TownWithRentPointDTO> getTowns(Integer page, Integer limit) {
        try {
            return townRepository.findAll(page, limit)
                    .stream()
                    .map(town -> modelMapper.map(town, TownWithRentPointDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get all towns! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get all towns!");
        }
    }

    @Override
    public TownWithRentPointDTO getTownInfo(Integer id) {
        try {
            return modelMapper.map(townRepository.getInfoWithScooters(id), TownWithRentPointDTO.class);
        } catch (RuntimeException exception) {
            log.error("Can't get town info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get town info!");
        }
    }

    @Override
    public void addTown(TownAddDTO townDTO) {
        try {
            townRepository.insert(modelMapper.map(townDTO, Town.class));
        } catch (RuntimeException exception) {
            log.error("Can't add town! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add town!");
        }
    }

    @Override
    public void deleteTownByID(Integer id) {
        try {
            townRepository.delete(townRepository.findById(id));
        } catch (RuntimeException exception) {
            log.error("Can't delete town by id! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't delete town by id!");
        }
    }

    @Override
    public void updateTown(Integer id, TownAddDTO townDTO) {
        try {
            Town townToUpdate = townRepository.findById(id);
            modelMapper.map(townDTO, townToUpdate);
            townRepository.update(townToUpdate);
        } catch (RuntimeException exception) {
            log.error("Can't update town! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't update town!");
        }
    }

    @Override
    public void addRentPointToTown(Integer id, RentPointDTO rentPointDTO) {
        try {
            Town town = townRepository.findById(id);
            RentPoint rentPoint = rentPointService.getRentPoint(rentPointDTO.getId());
            town.getRentPointSet().add(rentPoint);
            rentPoint.setTown(town);
        } catch (RuntimeException exception) {
            log.error("Can't add rent point to town! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add rent point to town!");
        }
    }
}

