package com.senla.rent.dao;

import com.senla.rent.api.dao.StatusScooterRepository;
import com.senla.rent.entity.StatusScooter;
import org.springframework.stereotype.Repository;

@Repository
public class StatusScooterRepositoryImpl extends AbstractRepository<StatusScooter, Integer> implements StatusScooterRepository {
    public StatusScooterRepositoryImpl() {
        super(StatusScooter.class);
    }
}
