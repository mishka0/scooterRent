package com.senla.rent.dao;

import com.senla.rent.api.dao.SubscriptionInfoRepository;
import com.senla.rent.entity.Addition;
import com.senla.rent.entity.Subscription;
import com.senla.rent.entity.SubscriptionInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubscriptionInfoRepositoryImpl extends AbstractRepository<SubscriptionInfo, Integer> implements SubscriptionInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public SubscriptionInfoRepositoryImpl() {
        super(SubscriptionInfo.class);
    }

    @Override
    public List<SubscriptionInfo> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionInfo> criteriaQuery = criteriaBuilder.createQuery(SubscriptionInfo.class);
        Root<SubscriptionInfo> root = criteriaQuery.from(SubscriptionInfo.class);
        CriteriaQuery<SubscriptionInfo> all = criteriaQuery.select(root);
        TypedQuery<SubscriptionInfo> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }
}
