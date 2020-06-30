package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.RentalPoint;

public interface RentalPointRepository extends GenericRepository<RentalPoint, Integer> {
    RentalPoint getRentPointDetails(Integer idPoint);
}
