package com.senla.rent.dao;

import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.dao.exceptions.NullDaoException;
import com.senla.rent.entity.User;
import com.senla.rent.entity.User_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;


public abstract class AbstractRepository<T, PK> implements GenericRepository<T, PK> {

//    private static final Logger log = LoggerFactory.getLogger(AbstractRepository.class);
    private final Class<T> entity;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractRepository(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public void insert(T entity) {
//        log.info("Insert entity: class " + entity.getClass());
        entityManager.persist(entity);
    }


    @Override
    public List<T> findAll(int page, int limit) {
//        log.info("Find all entities " + entity);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<T> root = criteriaQuery.from(entity);
        CriteriaQuery<T> all = criteriaQuery.select(root);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public T findById(PK pk) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entity);
        Root<T> from = query.from(entity);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<T> T_ = metamodel.entity(entity);
        query.select(from).where(criteriaBuilder.equal(from.get(T_.getSingularAttribute("id")), pk));
        return entityManager.createQuery(query).getSingleResult();
//        if (object != null)
//            return object;
//        throw new NullDaoException(entity.toString().replaceAll("^.*?(\\w+)\\W*$", "$1") + " entity is null after search by Id = " + pk);
    }

    @Override
    public void delete(T entity) {
//        log.info("Delete entity class = " + entity.getClass());
        entityManager.remove(entity);
//        log.info("OK!");
    }

    @Override
    public void deleteByID(PK id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(T entity){
        entityManager.merge(entity);
    };
}
