package com.computershop.app.model;

import com.computershop.app.enums.StatusOrder;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation", columnDefinition = "DATE")
    private Date creationDate;

    @Column(name = "status", columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Customer getCostumer() {
        return customer;
    }

    public void setCostumer(Customer customer) {
        this.customer = customer;
    }

    public Order() {
    }

    public Order(Long id, Date creationDate, StatusOrder statusOrder, Customer customer) {
        this.id = id;
        this.creationDate = creationDate;
        this.statusOrder = statusOrder;
        this.customer = customer;
    }

    public Order(Date creationDate, StatusOrder statusOrder, Customer customer) {
        this.creationDate = creationDate;
        this.statusOrder = statusOrder;
        this.customer = customer;
    }

    public Order(Long id) {
        this.id = id;
    }
}

