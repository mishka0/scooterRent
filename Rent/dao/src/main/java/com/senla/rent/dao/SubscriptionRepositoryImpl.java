package com.senla.rent.dao;

import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class SubscriptionRepositoryImpl extends AbstractRepository<Subscription, Integer> implements SubscriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SubscriptionRepositoryImpl() {
        super(Subscription.class);
    }

    @Override
    public List<Subscription> getUsersSubs(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscription> query = criteriaBuilder.createQuery(Subscription.class);
        Root<Subscription> from = query.from(Subscription.class);
        from.fetch(Subscription_.subscriptionInfo,JoinType.INNER);
        query.select(from).where(criteriaBuilder.equal(from.get(Subscription_.user), id));
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<Subscription> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
        Root<Subscription> from = query.from(Subscription.class);
        /*Add fetches*/
        query.orderBy(builder.asc(from.get(Subscription_.id)));
        TypedQuery<Subscription> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return readyQuery.getResultList();
    }
}
