package com.senla.rent.service;

import com.senla.rent.api.dao.StatusScooterRepository;
import com.senla.rent.api.dto.statusscooter.StatusScooterAddDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.api.service.StatusScooterService;
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
public class StatusScooterServiceImpl implements StatusScooterService {

    private final StatusScooterRepository statusScooterRepository;
    private final ModelMapper modelMapper;

    public StatusScooterServiceImpl(StatusScooterRepository statusScooterRepository, ModelMapper modelMapper) {
        this.statusScooterRepository = statusScooterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatusScooter getByName(String name) {
        try {
            return statusScooterRepository.findByName(name);
        } catch (RuntimeException exception) {
            log.error("Can't get status scooter by name! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get status scooter by name!");
        }
    }

    @Override
    public List<StatusScooterDTO> getAllStatuses(Integer page, Integer limit) {
        try {
            return statusScooterRepository.findAll(page, limit)
                    .stream()
                    .map(statusScooter -> modelMapper.map(statusScooter, StatusScooterDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get all statuses ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get all statuses!");
        }
    }

    @Override
    public void updateStatus(Integer id, StatusScooterDTO statusScooterDTO) {
        try {
            StatusScooter scooterToUpdate = statusScooterRepository.findById(id);
            modelMapper.map(statusScooterDTO, scooterToUpdate);
            statusScooterRepository.update(scooterToUpdate);
        } catch (RuntimeException exception) {
            log.error("Can't update status! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't update all statuses!");
        }
    }

    @Override
    public void addStatus(StatusScooterAddDTO statusScooterDTO) {
        try {
            statusScooterRepository.insert(modelMapper.map(statusScooterDTO, StatusScooter.class));
        } catch (RuntimeException exception) {
            log.error("Can't add status ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add status!");
        }
    }

    @Override
    public StatusScooter findById(Integer id) {
        try {
            return statusScooterRepository.findById(id);
        } catch (RuntimeException exception) {
            log.error("Can't find status ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't find status!");
        }
    }

    @Override
    public void deleteStatus(Integer id) {
        try {
            statusScooterRepository.delete(statusScooterRepository.findById(id));
        } catch (RuntimeException exception) {
            log.error("Can't delete status ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't delete status!");
        }
    }
}
