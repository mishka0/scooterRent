package com.senla.rent.dao;

import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.entity.History;
import com.senla.rent.entity.History_;
import com.senla.rent.entity.User;
import com.senla.rent.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

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
        from.fetch(History_.scooter, JoinType.LEFT);
        from.fetch(History_.tariff, JoinType.LEFT);
        query.select(from).where(criteriaBuilder.equal(from.get(History_.user), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<History> getScooterHistory(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> query = criteriaBuilder.createQuery(History.class);
        Root<History> from = query.from(History.class);
        from.fetch(History_.scooter, JoinType.INNER);
        from.fetch(History_.user, JoinType.LEFT).fetch(User_.addition, JoinType.LEFT);
        query.select(from).where(criteriaBuilder.equal(from.get(History_.scooter), id));
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Override
    public boolean existNonClosed(Integer idUser) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<History> from = query.from(History.class);
        Predicate userIdPredicate = criteriaBuilder.equal(from.get(History_.user), idUser);
        Predicate closedRentPredicate = criteriaBuilder.equal(from.get(History_.isClosed), false);

        query.select(from.get(History_.user)).where(userIdPredicate, closedRentPredicate);
        try {
            entityManager.createQuery(query).getSingleResult();
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Override
    public History getNonClosedRent(Integer idUser) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> query = criteriaBuilder.createQuery(History.class);
        Root<History> from = query.from(History.class);
        from.fetch(History_.tariff, JoinType.LEFT);
        Predicate userIdPredicate = criteriaBuilder.equal(from.get(History_.user), idUser);
        Predicate closedRentPredicate = criteriaBuilder.equal(from.get(History_.isClosed), false);
        query.select(from).where(userIdPredicate, closedRentPredicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<History> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> criteriaQuery = criteriaBuilder.createQuery(History.class);
        Root<History> root = criteriaQuery.from(History.class);
        CriteriaQuery<History> all = criteriaQuery.select(root);
        TypedQuery<History> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }
}
