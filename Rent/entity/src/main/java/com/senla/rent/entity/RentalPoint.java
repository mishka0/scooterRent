package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rental_point")
@Getter @Setter
public class RentalPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalPoint")
    private Set<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalPoint that = (RentalPoint) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }

    @Override
    public String toString() {
        return "RentalPoint{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
