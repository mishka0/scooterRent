package com.senla.rent.service;

import com.senla.rent.api.dao.ScooterRepository;
import com.senla.rent.api.dto.scooter.*;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.api.service.ScooterService;
import com.senla.rent.api.service.StatusScooterService;
import com.senla.rent.entity.Scooter;
import com.senla.rent.entity.StatusScooter;
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
public class ScooterServiceImpl implements ScooterService {

    private final ModelMapper modelMapper;
    private final ScooterRepository scooterRepository;
    private final StatusScooterService statusScooterService;

    public ScooterServiceImpl(ModelMapper modelMapper, ScooterRepository scooterRepository, StatusScooterService statusScooterService) {
        this.modelMapper = modelMapper;
        this.scooterRepository = scooterRepository;
        this.statusScooterService = statusScooterService;
    }

    @Override
    public ScooterFullInfoDTO getScooterInfo(Integer id) {
        try {
            return modelMapper.map(scooterRepository.findById(id), ScooterFullInfoDTO.class);
        } catch (RuntimeException exception) {
            log.error("Can't get scooter info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get scooter info!");
        }
    }

    @Override
    public List<ScooterInfoDTO> getScootersFromPoint(Integer id) {
        try {
            return scooterRepository.getScootersFromPoint(id)
                    .stream()
                    .map(scooter -> modelMapper.map(scooter, ScooterInfoDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get list of scooters! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get list of scooters!");
        }
    }

    @Override
    public List<ScooterFullInfoDTO> getScooters(Integer page, Integer limit) {
        try {
            return scooterRepository.findAll(page, limit)
                    .stream()
                    .map(scooter -> modelMapper.map(scooter, ScooterFullInfoDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get list scooter full info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get list scooter full info!");
        }
    }

    @Override
    public void addScooter(ScooterAddDTO scooterAddDTO) {
        Scooter scooter = modelMapper.map(scooterAddDTO, Scooter.class);
        if (scooter.getStatusScooter() == null)
        {
            scooter.setStatusScooter(statusScooterService.getByName("Stay"));
        }
        else
        {
            scooter.setStatusScooter(statusScooterService.findById(scooterAddDTO.getStatusScooter().getId()));
        }
        scooterRepository.insert(scooter);
    }

    @Override
    public void updateScooter(Integer id, ScooterEditDTO scooterInfoDTO) {
        if (scooterRepository.existById(id)) {
            Scooter scooterOld = scooterRepository.findById(id);
            modelMapper.map(scooterInfoDTO, scooterOld);
            scooterRepository.update(scooterOld);
        } else {
            /*logger*/
            throw new SecurityException("Scooter with id: " + id + " not exist!");
        }
    }

    @Override
    public void deleteScooter(Integer id) {
        scooterRepository.delete(scooterRepository.findById(id));
    }

    @Override
    public void setStatusScooter(Integer id, StatusScooterDTO statusScooterDTO) {
        StatusScooter statusScooter = statusScooterService.findById(statusScooterDTO.getId());
        Scooter scooter = scooterRepository.findById(id);
        scooter.setStatusScooter(statusScooter);
        scooterRepository.update(scooter);
    }

    @Override
    public Scooter getScooter(Integer id) {
        try {
            return scooterRepository.findById(id);
        } catch (RuntimeException exception) {
            log.error("Can't get scooter! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get scooter!");
        }
    }

    @Override
    public Scooter getScooterWithHistory(Integer id) {
        return scooterRepository.getHistoryScooter(id);
    }

    @Override
    public ScooterHistoryDTO getScooterHistory(Integer id) {
        return modelMapper.map(scooterRepository.getHistoryScooter(id), ScooterHistoryDTO.class);
    }
}
