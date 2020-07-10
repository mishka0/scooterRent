package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.History;

import java.util.List;

public interface HistoryRepository extends GenericRepository<History, Integer> {

    List<History> getUserHistory(Integer id);

    List<History> getScooterHistory(Integer id);

    boolean existNonClosed(Integer idUser);

    History getNonClosedRent(Integer idUser);
}
