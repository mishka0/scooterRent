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
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
        return townRepository.findAll(page, limit)
                .stream()
                .map(town -> modelMapper.map(town, TownWithRentPointDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TownWithRentPointDTO getTownInfo(Integer id) {
        try {
            Town town = townRepository.getInfoWithScooters(id);
            return modelMapper.map(town, TownWithRentPointDTO.class);
        } catch (MappingException exception) {
            throw new ServiceException(exception.getMessage());
        } catch (DataAccessException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public Town getTownByID(Integer id) {
        return townRepository.findById(id);
    }

    @Override
    public void addTown(TownAddDTO townDTO) {
        townRepository.insert(modelMapper.map(townDTO, Town.class));
    }

    @Override
    public void deleteTownByID(Integer id) {
        townRepository.delete(townRepository.findById(id));
    }

    @Override
    public void updateTown(Integer id, TownAddDTO townDTO) {
        if (townRepository.existById(id)) {
            Town townOld = townRepository.findById(id);
            modelMapper.map(townDTO, townOld);
            townRepository.update(townOld);
        } else {
            /*logger*/
            throw new SecurityException("Tariff with id: " + id + " not exist!");
        }
    }

    @Override
    public void addRentPointToTown(Integer id, RentPointDTO rentPointDTO) {
        Town town = townRepository.findById(id);
        RentPoint rentPoint = rentPointService.getRentPoint(rentPointDTO.getId());
        town.getRentPointSet().add(rentPoint);
        rentPoint.setTown(town);
    }
}

