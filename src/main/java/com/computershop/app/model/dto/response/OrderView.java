package com.computershop.app.model.dto.response;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.Customer;
import com.computershop.app.model.Order;

import java.util.Date;

public class OrderView {
    private Long id;
    private Date creationDate;
    private StatusOrder statusOrder;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderView(Long id, Date creationDate, StatusOrder statusOrder, Customer customer) {
        this.id = id;
        this.creationDate = creationDate;
        this.statusOrder = statusOrder;
        this.customer = customer;
    }

    public OrderView(Order order){
        this.id = order.getId();
        this.statusOrder = order.getStatusOrder();
        this.creationDate = order.getCreationDate();
        this.customer = order.getCostumer();
    }
}
