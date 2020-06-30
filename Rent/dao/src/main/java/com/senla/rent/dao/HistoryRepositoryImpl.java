package com.senla.rent.dao;

import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;


@Repository
public class HistoryRepositoryImpl extends AbstractRepository<History, Integer> implements HistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public HistoryRepositoryImpl() {
        super(History.class);
    }

    @Override
    public List<History> getUserHistory(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> query = criteriaBuilder.createQuery(History.class);
        Root<History> from = query.from(History.class);
        from.fetch( History_.scooter, JoinType.INNER);

        query.select(from)
                .where(criteriaBuilder.equal(from.get(History_.USER), id));
        return entityManager.createQuery(query).getResultList();
    }
}
