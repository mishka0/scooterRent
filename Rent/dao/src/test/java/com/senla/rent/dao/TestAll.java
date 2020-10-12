package com.senla.rent.dao;

import com.senla.rent.api.dao.springdata.RentPointSpringDataRepository;
import com.senla.rent.api.service.springdata.RentPointSpringDataService;
import com.senla.rent.configs.HibernateTestConfig;
import com.senla.rent.configs.LaunchTestConfiguration;
import com.senla.rent.entity.RentPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LaunchTestConfiguration.class, HibernateTestConfig.class})
@Transactional
public class TestAll {
    @Autowired
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void workWithJdbc() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "Select * from rental_point where rental_point.point_id = 1";

        ResultSet resultSet =  connection.createStatement().executeQuery(sql);

        RentPoint rentPoint = new RentPoint();
       while (resultSet.next()) {
           rentPoint.setId(resultSet.getInt(1));
           rentPoint.setAddress(resultSet.getString(2));
       }
        System.err.println(rentPoint);
    }

    @Test
    public void testJPQL(){

        String sql = "Select a from RentPoint a where a.id=1";
        Query query = entityManager.createQuery(sql);
        System.err.println(query.getResultList());
    }

    @Test
    public void testNativeQuery(){
        String sql = "Select * from rental_point where rental_point.point_id = 1";
        Query query = entityManager.createNativeQuery(sql, RentPoint.class);
        RentPoint rentPoint = (RentPoint) entityManager.createNativeQuery(sql, RentPoint.class).getSingleResult();

        System.err.println(rentPoint);

    }
}
