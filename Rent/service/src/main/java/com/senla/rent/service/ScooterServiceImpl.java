package com.senla.rent.service;

import com.senla.rent.api.dao.ScooterRepository;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.scooter.ScooterInfoDTO;
import com.senla.rent.api.service.ScooterService;
import com.senla.rent.entity.Scooter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScooterServiceImpl implements ScooterService {

    private final ModelMapper modelMapper;

    private final ScooterRepository scooterRepository;

    public ScooterServiceImpl(ModelMapper modelMapper, ScooterRepository scooterRepository) {
        this.modelMapper = modelMapper;
        this.scooterRepository = scooterRepository;
    }

    @Override
    public ScooterDTO getScooterInfo(Integer id) {
        return modelMapper.map(scooterRepository.findById(id), ScooterDTO.class);
    }

    @Override
    public List<ScooterInfoDTO> getScootersFromPoint(Integer id) {
        return scooterRepository.getScootersFromPoint(id)
                .stream()
                .map(scooter -> modelMapper.map(scooter, ScooterInfoDTO.class))
                .collect(Collectors.toList());
    }
}
