package com.senla.rent.entity;

import javax.persistence.*;
import java.time.Duration;
import java.util.Objects;

@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "duration")
    private Duration durationTariff;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(id, tariff.id) &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(cost, tariff.cost) &&
                Objects.equals(durationTariff, tariff.durationTariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, durationTariff);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", durationTariff=" + durationTariff +
                '}';
    }
}
