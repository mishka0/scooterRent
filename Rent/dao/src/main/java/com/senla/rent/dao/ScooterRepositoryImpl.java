package com.senla.rent.dao;

import com.senla.rent.api.dao.ScooterRepository;
import com.senla.rent.dao.exceptions.NullDaoException;
import com.senla.rent.entity.Scooter;
import com.senla.rent.entity.Scooter_;
import com.senla.rent.entity.User;
import com.senla.rent.entity.User_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
                .where(builder.equal(from.get(Scooter_.RENTAL_POINT), id));
        List<Scooter> scooters = entityManager.createQuery(query).getResultList();
        if (scooters.isEmpty())
        {
            throw new NullDaoException("Collection is null!");
        }
        return scooters;
    }
}
