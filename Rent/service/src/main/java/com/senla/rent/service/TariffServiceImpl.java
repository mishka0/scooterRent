package com.senla.rent.service;

import com.senla.rent.api.dao.TariffRepository;
import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.api.service.TariffService;
import com.senla.rent.entity.Tariff;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
        return tariffRepository.findAll(page, limit)
                .stream()
                .map(tariff -> modelMapper.map(tariff, TariffDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addTariff(TariffEditDTO tariffDTO) {
        Tariff tariff = modelMapper.map(tariffDTO, Tariff.class);
        tariffRepository.insert(tariff);
    }

    @Override
    public void updateTariff(Integer id, TariffEditDTO tariffDTO) {
        if (tariffRepository.existById(id)) {
            Tariff tariffOld = tariffRepository.findById(id);
            modelMapper.map(tariffDTO, tariffOld);
            tariffRepository.update(tariffOld);
        } else {
            /*logger*/
            throw new SecurityException("Tariff with id: " + id + " not exist!");
        }
    }

    @Override
    public Tariff getTariffById(Integer tariffId) {
        return tariffRepository.findById(tariffId);
    }

    @Override
    public Tariff getTariffByName(String name) {
        return tariffRepository.findByName(name);
    }
}
