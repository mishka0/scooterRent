package com.senla.rent.api.service;

import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<TariffDTO> getAllTariffs(Integer page, Integer limit);

    void addTariff(TariffEditDTO tariffDTO);

    void updateTariff(Integer id, TariffEditDTO tariffDTO);

    Tariff getTariffById(Integer tariffId);

    Tariff getTariffByName(String name);
}
