package com.computershop.app.model;

import com.computershop.app.enumeration.StatusOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column(name = "id_order")
    private Long id;

    @Column(name = "creation")
    private Date creationDate;

    @Column(name = "status")
    private StatusOrder statusOrder;


}
