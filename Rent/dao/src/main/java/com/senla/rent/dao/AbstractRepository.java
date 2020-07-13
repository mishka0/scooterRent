package com.senla.rent.dao;

import com.senla.rent.api.dao.generic.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;


public abstract class AbstractRepository<T, PK> implements GenericRepository<T, PK> {

    private final Class<T> entity;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractRepository(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public void insert(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(PK pk) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entity);
        Root<T> from = query.from(entity);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<T> entityMeta = metamodel.entity(entity);
        query.select(from).where(criteriaBuilder.equal(from.get(entityMeta.getSingularAttribute("id")), pk));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public boolean existById(PK id) {
        try {
            this.findById(id);
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }
}
