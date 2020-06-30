package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "addition")
@Getter @Setter
public class Addition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "balance")
    private Double balance;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addition addition = (Addition) o;
        return Objects.equals(id, addition.id) &&
                Objects.equals(firstName, addition.firstName) &&
                Objects.equals(lastName, addition.lastName) &&
                Objects.equals(phone, addition.phone) &&
                Objects.equals(discount, addition.discount) &&
                Objects.equals(balance, addition.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, discount, balance);
    }

    @Override
    public String toString() {
        return "Addition{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", discount=" + discount +
                ", balance=" + balance +
                '}';
    }
}
