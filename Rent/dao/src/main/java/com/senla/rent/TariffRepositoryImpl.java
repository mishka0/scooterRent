package com.senla.rent;

import com.senla.rent.api.dao.TariffRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TariffRepositoryImpl extends AbstractRepository<Tariff, Integer> implements TariffRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TariffRepositoryImpl() {
        super(Tariff.class);
    }

    @Override
    public List<Tariff> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> query = builder.createQuery(Tariff.class);
        Root<Tariff> from = query.from(Tariff.class);
        query.orderBy(builder.asc(from.get(Tariff_.id)));
        TypedQuery<Tariff> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Tariff findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> query = criteriaBuilder.createQuery(Tariff.class);
        Root<Tariff> from = query.from(Tariff.class);
        query.select(from)
                .where(criteriaBuilder.equal(from.get(Tariff_.name), name));
        return entityManager.createQuery(query).getSingleResult();
    }
}
