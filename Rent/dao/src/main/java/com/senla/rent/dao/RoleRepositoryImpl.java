package com.senla.rent.dao;

import com.senla.rent.api.dao.RoleRepository;
import com.senla.rent.entity.Role;
import com.senla.rent.entity.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        final List<Role> roles = entityManager.createQuery(query).getResultList();
        if (roles.isEmpty()) {
            throw new RuntimeException("Role with name: " + name + " not found");
        }
        return roles.get(0);
    }

}
