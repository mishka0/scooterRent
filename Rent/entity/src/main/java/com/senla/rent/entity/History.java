package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "history")
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @Column(name = "final_cost")
    private Double cost;

    @Column(name = "date_of_ride")
    private LocalDate dateRide;

    @Column(name = "time_ride")
    private Duration timeRide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_subscription")
    private boolean isSubscription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return isSubscription == history.isSubscription &&
                Objects.equals(id, history.id) &&
                Objects.equals(cost, history.cost) &&
                Objects.equals(dateRide, history.dateRide) &&
                Objects.equals(timeRide, history.timeRide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, dateRide, timeRide, isSubscription);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", cost=" + cost +
                ", dateRide=" + dateRide +
                ", timeRide=" + timeRide +
                ", isSubscription=" + isSubscription +
                '}';
    }
}
