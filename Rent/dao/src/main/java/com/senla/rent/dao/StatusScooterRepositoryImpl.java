package com.senla.rent.dao;

import com.senla.rent.api.dao.StatusScooterRepository;
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
public class StatusScooterRepositoryImpl extends AbstractRepository<StatusScooter, Integer> implements StatusScooterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public StatusScooterRepositoryImpl() {
        super(StatusScooter.class);
    }

    @Override
    public StatusScooter findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StatusScooter> query = criteriaBuilder.createQuery(StatusScooter.class);
        Root<StatusScooter> from = query.from(StatusScooter.class);
        query.select(from)
                .where(criteriaBuilder.equal(from.get(StatusScooter_.status), name));
        entityManager.createQuery(query).getSingleResult();
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<StatusScooter> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StatusScooter> query = builder.createQuery(StatusScooter.class);
        Root<StatusScooter> from = query.from(StatusScooter.class);
        query.orderBy(builder.asc(from.get(StatusScooter_.id)));
        TypedQuery<StatusScooter> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return readyQuery.getResultList();
    }
}
