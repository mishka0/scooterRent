package com.senla.rent;

import com.senla.rent.api.dao.TownRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TownRepositoryImpl extends AbstractRepository<Town, Integer> implements TownRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TownRepositoryImpl() {
        super(Town.class);
    }

    @Override
    public List<Town> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Town> query = builder.createQuery(Town.class);
        Root<Town> from = query.from(Town.class);
        from.fetch(Town_.rentPointSet, JoinType.LEFT);
        query.distinct(true);
        query.orderBy(builder.asc(from.get(Town_.id)));
        TypedQuery<Town> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Town getInfoWithScooters(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Town> query = builder.createQuery(Town.class);
        Root<Town> from = query.from(Town.class);
        from.fetch(Town_.rentPointSet, JoinType.LEFT)
                .fetch(RentPoint_.scooters, JoinType.LEFT)
                .fetch(Scooter_.statusScooter, JoinType.LEFT);
        Predicate predicateTownId = builder.equal(from.get(Town_.id), id);
        query.distinct(true);
        query.orderBy(builder.asc(from.get(Town_.id)));
        query.select(from).where(predicateTownId);
        return entityManager.createQuery(query).getSingleResult();
    }
}
