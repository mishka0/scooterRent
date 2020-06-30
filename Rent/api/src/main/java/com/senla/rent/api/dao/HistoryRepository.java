package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.History;

import java.util.List;
import java.util.Set;

public interface HistoryRepository extends GenericRepository<History, Integer> {

    List<History> getUserHistory(Integer id);
}
