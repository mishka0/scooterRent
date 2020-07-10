package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.StatusScooter;

import java.util.List;

public interface StatusScooterRepository extends GenericRepository<StatusScooter, Integer> {
    StatusScooter findByName(String name);

    //@Deprecated
    //List<StatusScooter> getAllStatuses();
}
