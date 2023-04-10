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
    private Costumer costumer;

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

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}

