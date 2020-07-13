package com.senla.rent.dao;

import com.senla.rent.api.dao.RoleRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, Integer> implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        Root<Role> from = query.from(Role.class);
        query.select(from)
                .where(criteriaBuilder.equal(from.get(Role_.ROLE), name));
        List<Role> roles = entityManager.createQuery(query).getResultList();
        return roles.get(0);
    }

    @Override
    public List<Role> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> from = query.from(Role.class);
        /*Add fetches*/
        query.orderBy(builder.asc(from.get(Role_.id)));
        TypedQuery<Role> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return readyQuery.getResultList();
    }
}
