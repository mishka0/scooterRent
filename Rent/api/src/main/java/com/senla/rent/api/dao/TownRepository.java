package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Town;


public interface TownRepository extends GenericRepository<Town, Integer> {
    Town getInfoWithScooters(Integer id);
}
