package com.senla.rent.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "scooter")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scooter_id")
    private Integer id;

    @Column(name = "model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusScooter statusScooter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scooter")
    private Set<History> histories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id")
    private RentalPoint rentalPoint;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return Objects.equals(id, scooter.id) &&
                Objects.equals(model, scooter.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}