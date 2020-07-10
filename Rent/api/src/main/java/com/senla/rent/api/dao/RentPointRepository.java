package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.RentPoint;

public interface RentPointRepository extends GenericRepository<RentPoint, Integer> {
    RentPoint getRentPointDetails(Integer idPoint);

    RentPoint getRentPoint(Integer id);
}
