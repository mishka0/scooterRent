package com.senla.rent.dao;

import com.senla.rent.api.dao.TownRepository;
import com.senla.rent.entity.Town;
import com.senla.rent.entity.Town_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TownRepositoryImpl extends AbstractRepository<Town, Integer> implements TownRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TownRepositoryImpl() {
        super(Town.class);
    }


    @Override
    public List<Town> findAllWithRent() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Town> query = builder.createQuery(Town.class);
        Root<Town> from = query.from(Town.class);
        from.fetch(Town_.rentalPointSet, JoinType.LEFT);
        return entityManager.createQuery(query.distinct(true)).getResultList();
    }
}
