package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "rental_point", schema = "public")
@Getter @Setter
/*@NamedEntityGraph(
        name = "rent-point",
        attributeNodes = {
                @NamedAttributeNode("town"),

        }
)*/
public class RentPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentPoint", cascade = CascadeType.MERGE)
    private Set<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentPoint that = (RentPoint) o;
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
                "idTown=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
