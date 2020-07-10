package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "status_scooter")
@Getter
@Setter
public class StatusScooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "name")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusScooter")
    private Set<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusScooter that = (StatusScooter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "StatusScooter{" +
                "idTown=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
