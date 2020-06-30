package com.senla.rent.dao;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.entity.Addition;
import com.senla.rent.entity.Addition_;
import com.senla.rent.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
                .where(criteriaBuilder.equal(from.get(Addition_.USER), id));
        return entityManager.createQuery(query).getSingleResult();
    }

}
