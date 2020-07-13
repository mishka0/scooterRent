package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Addition;

public interface AdditionRepository extends GenericRepository<Addition, Integer> {

    Addition getUserAddition(Integer id);
}
