package com.senla.rent.dao;

import com.senla.rent.api.dao.RentalPointRepository;
import com.senla.rent.entity.Addition_;
import com.senla.rent.entity.RentalPoint;
import com.senla.rent.entity.RentalPoint_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RentalPointRepositoryImpl extends AbstractRepository<RentalPoint, Integer> implements RentalPointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RentalPointRepositoryImpl() {
        super(RentalPoint.class);
    }

    @Override
    public List<RentalPoint> findAll(int page, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentalPoint> criteriaQuery = criteriaBuilder.createQuery(RentalPoint.class);
        Root<RentalPoint> root = criteriaQuery.from(RentalPoint.class);
        root.fetch(RentalPoint_.town);
        CriteriaQuery<RentalPoint> all = criteriaQuery.select(root);
        TypedQuery<RentalPoint> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public RentalPoint getRentPointDetails(Integer idPoint) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentalPoint> query = criteriaBuilder.createQuery(RentalPoint.class);
        Root<RentalPoint> from = query.from(RentalPoint.class);
        from.fetch(RentalPoint_.town);
        from.fetch(RentalPoint_.scooters);
        query.where(criteriaBuilder.equal(from.get(RentalPoint_.id), idPoint));
        return entityManager.createQuery(query).getSingleResult();
    }
}
