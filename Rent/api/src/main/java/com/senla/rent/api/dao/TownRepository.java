package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Town;
import com.sun.xml.internal.stream.Entity;

import java.util.List;

public interface TownRepository extends GenericRepository<Town, Integer> {
    List<Town> findAllWithRent();
}
