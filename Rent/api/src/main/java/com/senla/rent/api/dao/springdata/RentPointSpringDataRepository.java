package com.senla.rent.api.dao.springdata;

import com.senla.rent.api.dto.projections.RentPointProj;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.entity.RentPoint_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public interface RentPointSpringDataRepository extends JpaRepository<RentPoint,Integer>, JpaSpecificationExecutor<RentPoint> {
    @EntityGraph(attributePaths = {"town"})
    RentPoint findByAddress(String address);

    @Query("select c from RentPoint c join fetch c.town where c.id = :id")
    RentPoint findByIdFetchTown(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM rental_point WHERE rental_point.point_id = ?",
            nativeQuery = true)
    RentPoint findByIdNative(@Param("id") Integer id);

    static Specification<RentPoint> spec() {
        return new Specification<RentPoint>() {
            @Override
            public Predicate toPredicate(Root<RentPoint> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(RentPoint_.id), 1);
            }
        };
    }

    @Query("select c from RentPoint c join fetch c.town where c.id = :id")
    RentPointProj getRentProj(@Param("id") Integer id);

}

