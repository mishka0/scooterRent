package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Tariff;

public interface TariffRepository extends GenericRepository<Tariff, Integer> {
    Tariff findByName(String name);
}
