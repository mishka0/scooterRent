package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subscription")
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer id;

    @Column(name = "time_left")
    private Duration timeLeft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subs_info_id")
    private SubscriptionInfo subscriptionInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscription")
    private Set<History> histories;

    public Subscription(Duration timeLeft, SubscriptionInfo subscriptionInfo, User user) {
        this.timeLeft = timeLeft;
        this.subscriptionInfo = subscriptionInfo;
        this.user = user;
    }

    public Subscription() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeLeft, that.timeLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeLeft);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "idTown=" + id +
                ", timeLeft=" + timeLeft +
                '}';
    }
}
