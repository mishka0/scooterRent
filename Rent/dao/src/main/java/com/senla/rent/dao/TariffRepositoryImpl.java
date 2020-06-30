package com.senla.rent.dao;

import com.senla.rent.api.dao.TariffRepository;
import com.senla.rent.entity.Tariff;
import org.springframework.stereotype.Repository;

@Repository
public class TariffRepositoryImpl extends AbstractRepository<Tariff, Integer> implements TariffRepository {
    public TariffRepositoryImpl() {
        super(Tariff.class);
    }
}
