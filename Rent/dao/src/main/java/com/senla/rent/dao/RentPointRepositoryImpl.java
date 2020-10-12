package com.senla.rent.dao;

import com.senla.rent.api.dao.RentPointRepository;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.entity.RentPoint_;
import com.senla.rent.entity.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class RentPointRepositoryImpl extends AbstractRepository<RentPoint, Integer> implements RentPointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    public RentPointRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(RentPoint.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RentPoint> findAll(Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> criteriaQuery = criteriaBuilder.createQuery(RentPoint.class);
        Root<RentPoint> root = criteriaQuery.from(RentPoint.class);
        root.fetch(RentPoint_.town, JoinType.LEFT);
        CriteriaQuery<RentPoint> all = criteriaQuery.select(root);
        TypedQuery<RentPoint> allQuery = entityManager.createQuery(all);
        allQuery.setFirstResult(page * limit);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();

    }



    public RentPoint getRentPoint(Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> query = criteriaBuilder.createQuery(RentPoint.class);
        Root<RentPoint> from = query.from(RentPoint.class);
        from.fetch(RentPoint_.town, JoinType.LEFT);
        query.select(from).where(criteriaBuilder.equal(from.get(RentPoint_.id), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public List<RentPoint> getRentPointJdbcTemplate(Integer page, Integer limit) {
        List<RentPoint> points = jdbcTemplate.query(
                "select * from rental_point limit ?",
                new Object[]{(page+1)*limit},
                (rs, rowNum) ->{
                    RentPoint rentPoint = new RentPoint();
                    rentPoint.setId(rs.getInt(1));
                    rentPoint.setAddress(rs.getString(2));
                    Town town = new Town();
                    town.setId(rs.getInt(3));
                    rentPoint.setTown(town);
                    return rentPoint;
                }
        );
        return points;
    }


}
