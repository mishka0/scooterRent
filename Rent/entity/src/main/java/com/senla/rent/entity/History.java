package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "final_cost")
    private Double cost;

    @Column(name = "date_of_ride")
    private LocalDate dateRide;

    @Column(name = "time_start")
    private LocalTime timeStart;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_subscription")
    private boolean isSubscription;

    @Column(name = "is_closed")
    private boolean isClosed;

    public History() {
    }

    public History(Scooter scooter, Tariff tariff, LocalDate dateRide, LocalTime timeStart, User user, boolean isSubscription, boolean isClosed) {
        this.scooter = scooter;
        this.tariff = tariff;
        this.dateRide = dateRide;
        this.timeStart = timeStart;
        this.user = user;
        this.isSubscription = isSubscription;
        this.isClosed = isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return isSubscription == history.isSubscription &&
                Objects.equals(id, history.id) &&
                Objects.equals(cost, history.cost) &&
                Objects.equals(dateRide, history.dateRide) &&
                Objects.equals(timeStart, history.timeStart) &&
                Objects.equals(timeEnd, history.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, dateRide, timeStart, timeEnd, isSubscription);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", cost=" + cost +
                ", dateRide=" + dateRide +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", isSubscription=" + isSubscription +
                '}';
    }
}
