package com.senla.rent.api.dao;

import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.StatusScooter;

public interface StatusScooterRepository extends GenericRepository<StatusScooter, Integer> {
    StatusScooter findByName(String name);
}
