package com.senla.rent.dao;

import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Integer> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByLogin(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        from.fetch(User_.roles);
        query.select(from).where(criteriaBuilder.equal(from.get(User_.login), username));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public boolean existUser(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<User> from = query.from(User.class);
        query.select(from.get(User_.login)).where(criteriaBuilder.equal(from.get(User_.login), username));
        try {
            entityManager.createQuery(query).getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public User getUserWithAllInfo(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        root.fetch(User_.addition, JoinType.LEFT);
        root.fetch(User_.subscriptions, JoinType.LEFT).fetch(Subscription_.subscriptionInfo, JoinType.LEFT);
        query.select(root).where(builder.equal(root.get(User_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }


    @Override
    public List<User> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.orderBy(builder.asc(from.get(User_.id)));
        TypedQuery<User> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return entityManager.createQuery(query).getResultList();
    }
}

