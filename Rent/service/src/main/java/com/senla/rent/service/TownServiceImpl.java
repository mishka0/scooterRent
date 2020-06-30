package com.senla.rent.service;

import com.senla.rent.api.dao.TownRepository;
import com.senla.rent.api.dto.town.TownDTO;
import com.senla.rent.api.dto.town.TownWithRentPointDTO;
import com.senla.rent.api.service.TownService;
import com.senla.rent.entity.Town;
import com.senla.rent.service.exceptions.ServiceException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TownWithRentPointDTO> getTowns() {
        return townRepository.findAllWithRent()
                .stream()
                .map(town -> modelMapper.map(town, TownWithRentPointDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TownWithRentPointDTO getTownInfo(Integer id) {
        try{
            Town town = townRepository.findById(id);
            return modelMapper.map(town, TownWithRentPointDTO.class);
        }
        catch (MappingException exception)
        {
            throw new ServiceException(exception.getMessage());
        }
        catch (DataAccessException exception)
        {
            throw new ServiceException(exception.getMessage());
        }

    }
}
