package com.senla.rent;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.entity.Addition;
import com.senla.rent.entity.Addition_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AdditionRepositoryImpl extends AbstractRepository<Addition, Integer> implements AdditionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AdditionRepositoryImpl( ) {
        super(Addition.class);
    }

    @Override
    public Addition getUserAddition(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Addition> query = criteriaBuilder.createQuery(Addition.class);
        Root<Addition> from = query.from(Addition.class);
        query.select(from)
                .where(criteriaBuilder.equal(from.get(Addition_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<Addition> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Addition> criteriaQuery = criteriaBuilder.createQuery(Addition.class);
        Root<Addition> root = criteriaQuery.from(Addition.class);
        CriteriaQuery<Addition> all = criteriaQuery.select(root);
        TypedQuery<Addition> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }
}
