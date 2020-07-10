package com.senla.rent.dao;

import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.RentPoint;

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

//
//    @Override
//    public List<T> findAll(int page, int limit) {
////        log.info("Find all entities " + entity);
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
//        Root<T> root = criteriaQuery.from(entity);
//        CriteriaQuery<T> all = criteriaQuery.select(root);
//        TypedQuery<T> allQuery = entityManager.createQuery(all);
//        allQuery.setFirstResult(page * limit);
//        allQuery.setMaxResults(limit);
//        return allQuery.getResultList();
//    }

    @Override
    public T findById(PK pk) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entity);
        Root<T> from = query.from(entity);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<T> entityMeta = metamodel.entity(entity);
        query.select(from).where(criteriaBuilder.equal(from.get(entityMeta.getSingularAttribute("id")), pk));
        return entityManager.createQuery(query).getSingleResult();
//        if (object != null)
//            return object;
//        throw new NullDaoException(entity.toString().replaceAll("^.*?(\\w+)\\W*$", "$1") + " entity is null after search by Id = " + pk);
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
    public void update(T entity){
        entityManager.merge(entity);
    }
}
