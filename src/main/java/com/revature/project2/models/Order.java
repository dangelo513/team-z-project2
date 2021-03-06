package com.revature.project2.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="mall_user_orders")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp timeCreated;

    @ManyToOne (fetch = FetchType.EAGER, cascade={
            CascadeType.REMOVE, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST
    })// many orders to one user. References mall_users
    @JoinColumn(name = "user_id")// specify what to join?
    private User user; // change this to User
    // will refer to primary key in user table if changed to User

    @Column(nullable = false)
    private Integer itemCount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean closed;

    // no-args constructor
    public Order() { super(); }

    // constructor without ID
    public Order(Timestamp timeCreated, User user, Integer itemCount, Double price, boolean closed) {

        this.timeCreated = timeCreated;
        this.user = user;
        this.itemCount = itemCount;
        this.price = price;
        this.closed = closed;

    }

    // full constructor
    public Order(Integer id, Timestamp timeCreated, User user, Integer itemCount, Double price, boolean closed) {

        this (timeCreated, user, itemCount, price, closed);
        this.id = id;

    }

    // copy constructor
    public Order(Order copy) {
        this (copy.id, copy.timeCreated, copy.user, copy.itemCount, copy.price, copy.closed);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return closed == order.closed &&
                Objects.equals(id, order.id) &&
                Objects.equals(timeCreated, order.timeCreated) &&
                Objects.equals(user, order.user) &&
                Objects.equals(itemCount, order.itemCount) &&
                Objects.equals(price, order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeCreated, user, itemCount, price, closed);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", timeCreated=" + timeCreated +
                ", user=" + user +
                ", itemCount=" + itemCount +
                ", price=" + price +
                ", closed=" + closed +
                '}';
    }
}
