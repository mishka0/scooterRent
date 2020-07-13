package com.senla.rent.dao;

import com.senla.rent.api.dao.ScooterRepository;
import com.senla.rent.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class ScooterRepositoryImpl extends AbstractRepository<Scooter, Integer> implements ScooterRepository {

    public ScooterRepositoryImpl() {
        super(Scooter.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Scooter> getScootersFromPoint(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> from = query.from(Scooter.class);
        from.fetch(Scooter_.statusScooter);
        query.select(from)
                .where(builder.equal(from.get(Scooter_.rentPoint), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Scooter findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> from = query.from(Scooter.class);
        from.fetch(Scooter_.statusScooter, JoinType.LEFT);
        from.fetch(Scooter_.rentPoint, JoinType.LEFT);
        query.select(from)
                .where(builder.equal(from.get(Scooter_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Scooter getHistoryScooter(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> from = query.from(Scooter.class);
        from.fetch(Scooter_.histories, JoinType.LEFT);
        from.fetch(Scooter_.statusScooter, JoinType.LEFT);
        from.fetch(Scooter_.rentPoint, JoinType.LEFT);
        query.select(from)
                .where(builder.equal(from.get(Scooter_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<Scooter> getStayScootersFromPoint(Integer idPoint) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> from = query.from(Scooter.class);
        from.fetch(Scooter_.statusScooter, JoinType.LEFT);
        Join<Scooter, StatusScooter> statsScoot = from.join(Scooter_.statusScooter, JoinType.LEFT);
        Predicate rentPointIdPredicate = builder.equal(from.get(Scooter_.rentPoint), idPoint);
        Predicate statusScooterPredicate = builder.equal(statsScoot.get(StatusScooter_.status), "Stay");
        query.select(from).where(rentPointIdPredicate, statusScooterPredicate);
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<Scooter> findAll(Integer page, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> query = builder.createQuery(Scooter.class);
        Root<Scooter> from = query.from(Scooter.class);
        from.fetch(Scooter_.statusScooter, JoinType.LEFT);
        from.fetch(Scooter_.rentPoint, JoinType.LEFT);
        query.orderBy(builder.asc(from.get(Scooter_.id)));
        TypedQuery<Scooter> readyQuery = entityManager.createQuery(query);
        readyQuery.setFirstResult(page * limit);
        readyQuery.setMaxResults(limit);
        return readyQuery.getResultList();
    }
}
