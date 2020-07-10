package com.senla.rent.service;

import com.senla.rent.api.dao.StatusScooterRepository;
import com.senla.rent.api.dto.statusscooter.StatusScooterAddDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import com.senla.rent.api.service.StatusScooterService;
import com.senla.rent.entity.StatusScooter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatusScooterServiceImpl implements StatusScooterService {

    private final StatusScooterRepository statusScooterRepository;
    private final ModelMapper modelMapper;

    public StatusScooterServiceImpl(StatusScooterRepository statusScooterRepository, ModelMapper modelMapper) {
        this.statusScooterRepository = statusScooterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatusScooter getByName(String name) {
        return statusScooterRepository.findByName(name);
    }

    @Override
    public List<StatusScooterDTO> getAllStatuses(Integer page, Integer limit) {
        return statusScooterRepository.findAll(page, limit)
                .stream()
                .map(statusScooter -> modelMapper.map(statusScooter, StatusScooterDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Integer id, StatusScooterDTO statusScooterDTO) {
        if (statusScooterRepository.existById(id)) {
            StatusScooter scooterOld = statusScooterRepository.findById(id);
            modelMapper.map(statusScooterDTO, scooterOld);
            statusScooterRepository.update(scooterOld);
        } else {
            throw new RuntimeException("CAN'T UPDATE NON EXIST BY ID");
        }
    }

    @Override
    public void addStatus(StatusScooterAddDTO statusScooterDTO) {
        statusScooterRepository.insert(modelMapper.map(statusScooterDTO, StatusScooter.class));
    }

    @Override
    public StatusScooter findById(Integer id) {
        return statusScooterRepository.findById(id);
    }

    @Override
    public void deleteStatus(Integer id) {
        StatusScooter statusScooter = statusScooterRepository.findById(id);
        statusScooterRepository.delete(statusScooter);
    }
}
