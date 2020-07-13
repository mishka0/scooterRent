package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "subscription_info")
@Getter
@Setter
public class SubscriptionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "time")
    private Duration time;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriptionInfo")
    private Set<Subscription> subscriptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionInfo that = (SubscriptionInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, time);
    }

    @Override
    public String toString() {
        return "SubscriptionInfo{" +
                "idTown=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", time=" + time +
                '}';
    }
}
