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
        if (scooter.getStatusScooter() == null) {
            scooter.setStatusScooter(statusScooterService.getByName("Stay"));
        } else {
            scooter.setStatusScooter(statusScooterService.findById(scooterAddDTO.getStatusScooter().getId()));
        }
        scooterRepository.insert(scooter);
    }

    @Override
    public void updateScooter(Integer id, ScooterEditDTO scooterInfoDTO) {
        try {
            Scooter scooterToUpdate = scooterRepository.findById(id);
            modelMapper.map(scooterInfoDTO, scooterToUpdate);
            scooterRepository.update(scooterToUpdate);
        } catch (RuntimeException exception) {
            log.error("Scooter with id: " + id + " not exist! Message exception: " + exception.getMessage());
            throw new ServiceException("Scooter with id: " + id + " not exist!");
        }
    }

    @Override
    public void deleteScooter(Integer id) {
        try {
            scooterRepository.delete(scooterRepository.findById(id));
        } catch (RuntimeException exception) {
            log.error("Can't delete scooter! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't delete scooter!");
        }
    }

    @Override
    public void setStatusScooter(Integer id, StatusScooterDTO statusScooterDTO) {
        try {
            StatusScooter statusScooter = statusScooterService.findById(statusScooterDTO.getId());
            Scooter scooter = scooterRepository.findById(id);
            scooter.setStatusScooter(statusScooter);
            scooterRepository.update(scooter);
        } catch (RuntimeException exception) {
            log.error("Can't set status scooter! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't set status scooter!");
        }
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
        try {
            return scooterRepository.getHistoryScooter(id);
        } catch (RuntimeException exception) {
            log.error("Can't get scooter with history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get scooter with history!");
        }
    }

    @Override
    public List<ScooterInfoDTO> getStayScootersFromPoint(Integer idPoint) {
        try {
            return scooterRepository.getStayScootersFromPoint(idPoint)
                    .stream()
                    .map(scooter -> modelMapper.map(scooter, ScooterInfoDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get list of scooters with status Stay! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get list of scooters with status Stay!");
        }
    }
}
