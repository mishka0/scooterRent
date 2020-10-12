package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.RentPoint;

import java.util.List;

public interface RentPointRepository extends GenericRepository<RentPoint, Integer> {

    RentPoint getRentPoint(Integer id);

    List<RentPoint> getRentPointJdbcTemplate(Integer page, Integer limit);
}
