package com.senla.rent.service;

import com.senla.rent.api.dao.TariffRepository;
import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.api.service.TariffService;
import com.senla.rent.entity.Tariff;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;
    private final ModelMapper modelMapper;

    public TariffServiceImpl(TariffRepository tariffRepository, ModelMapper modelMapper) {
        this.tariffRepository = tariffRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TariffDTO> getAllTariffs(Integer page, Integer limit)
    {
        try {
            return tariffRepository.findAll(page, limit)
                    .stream()
                    .map(tariff -> modelMapper.map(tariff, TariffDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get all tariffs ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get all tariffs!");
        }
    }

    @Override
    public void addTariff(TariffEditDTO tariffDTO) {
        try {
            tariffRepository.insert(modelMapper.map(tariffDTO, Tariff.class));
        } catch (RuntimeException exception) {
            log.error("Can't add tariff ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add tariff!");
        }
    }

    @Override
    public void updateTariff(Integer id, TariffEditDTO tariffDTO) {
        try {
            Tariff tariffToUpdate = tariffRepository.findById(id);
            modelMapper.map(tariffDTO, tariffToUpdate);
            tariffRepository.update(tariffToUpdate);
        } catch (RuntimeException exception) {
            log.error("Can't update tariff! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't update tariff!");
        }
    }

    @Override
    public Tariff getTariffById(Integer tariffId) {
        try {
            return tariffRepository.findById(tariffId);
        } catch (RuntimeException exception) {
            log.error("Can't get tariff by ID ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get tariff by ID!");
        }
    }

    @Override
    public Tariff getTariffByName(String name) {
        try {
            return tariffRepository.findByName(name);
        } catch (RuntimeException exception) {
            log.error("Can't get tariff by name ! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get tariff by name!");
        }
    }
}
