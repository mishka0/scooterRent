package com.senla.rent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

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

    @Column(name = "is_closed")
    private boolean isClosed;

    public History() {
    }

    public History(Scooter scooter, Tariff tariff, LocalDate dateRide, LocalTime timeStart, User user, Subscription subscription,  boolean isSubscription, boolean isClosed) {
        this.scooter = scooter;
        this.tariff = tariff;
        this.dateRide = dateRide;
        this.timeStart = timeStart;
        this.user = user;
        this.subscription = subscription;
        this.isClosed = isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return isClosed == history.isClosed &&
                id.equals(history.id) &&
                cost.equals(history.cost) &&
                dateRide.equals(history.dateRide) &&
                timeStart.equals(history.timeStart) &&
                timeEnd.equals(history.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, dateRide, timeStart, timeEnd, isClosed);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", cost=" + cost +
                ", dateRide=" + dateRide +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", isClosed=" + isClosed +
                '}';
    }
}
