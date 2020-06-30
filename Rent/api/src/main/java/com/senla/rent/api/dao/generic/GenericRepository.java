package com.senla.rent.api.dao.generic;

import java.util.List;

public interface GenericRepository<T, PK> {

    void insert(T entity);

    List<T> findAll(int page, int limit);

    T findById(PK pk);

    void update(T entity);

    void delete(T entity);

    void deleteByID(PK id);
}
