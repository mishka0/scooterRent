package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "town", schema = "public")
@Getter @Setter
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "town", cascade = CascadeType.ALL)
    private Set<RentPoint> rentPointSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(id, town.id) &&
                Objects.equals(name, town.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Town{" +
                "idTown=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
