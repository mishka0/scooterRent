package com.senla.rent.dao;

import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;



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
        query.select(from)
                .where(criteriaBuilder.equal(from.get(User_.login), username));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public boolean existUser(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<User> from = query.from(User.class);
        query.select(from.get(User_.login))
                .where(criteriaBuilder.equal(from.get(User_.login), username));
        try {
            entityManager.createQuery(query).getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public Integer getUserId(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
        Root<User> root = query.from(User.class);
        query.select(root.get(User_.id)).where(builder.equal(root.get(User_.login), username));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public User getUserWithAllInfo(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        root.fetch(User_.addition);
        root.fetch(User_.subscriptions);
        query.select(root).where(builder.equal(root.get(User_.login), username));
        return entityManager.createQuery(query).getSingleResult();
    }

}

