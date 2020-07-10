package com.senla.rent.api.dao.generic;

import java.util.List;

public interface GenericRepository<T, PK> {

    void insert(T entity);

    List<T> findAll(Integer page, Integer limit);

    T findById(PK pk);

    void update(T entity);

    void delete(T entity);

    boolean existById(PK id);
}
