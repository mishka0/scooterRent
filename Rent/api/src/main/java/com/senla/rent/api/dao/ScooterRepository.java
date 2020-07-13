package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Scooter;

import java.util.List;

public interface ScooterRepository extends GenericRepository<Scooter, Integer> {

    List<Scooter> getScootersFromPoint(Integer id);

    Scooter getHistoryScooter(Integer id);

    List<Scooter> getStayScootersFromPoint(Integer idPoint);
}
