package com.senla.rent.dao;

import com.senla.rent.api.dao.RentPointRepository;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.entity.RentPoint_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class RentPointRepositoryImpl extends AbstractRepository<RentPoint, Integer> implements RentPointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RentPointRepositoryImpl() {
        super(RentPoint.class);
    }

    @Override
    public List<RentPoint> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> criteriaQuery = criteriaBuilder.createQuery(RentPoint.class);
        Root<RentPoint> root = criteriaQuery.from(RentPoint.class);
        root.fetch(RentPoint_.town, JoinType.LEFT);
        CriteriaQuery<RentPoint> all = criteriaQuery.select(root);
        TypedQuery<RentPoint> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public RentPoint getRentPointDetails(Integer idPoint) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> query = criteriaBuilder.createQuery(RentPoint.class);
        Root<RentPoint> from = query.from(RentPoint.class);
        from.fetch(RentPoint_.town, JoinType.LEFT);
        from.fetch(RentPoint_.scooters, JoinType.LEFT);
        query.where(criteriaBuilder.equal(from.get(RentPoint_.id), idPoint));
        return entityManager.createQuery(query).getSingleResult();
    }

    public RentPoint getRentPoint(Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> query = criteriaBuilder.createQuery(RentPoint.class);
        Root<RentPoint> from = query.from(RentPoint.class);
        from.fetch(RentPoint_.town, JoinType.LEFT);
        query.where(criteriaBuilder.equal(from.get(RentPoint_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }


}
