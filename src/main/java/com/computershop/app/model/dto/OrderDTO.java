package com.computershop.app.model.dto;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class OrderDTO {

    @NotNull
    private Date creationDate;
    @NotBlank
    private StatusOrder statusOrder;
    @NotNull
    private CustomerDTO customer;

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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Order toEntity(){
        return new Order(this.creationDate, this.statusOrder, this.customer.toEntity());
    }
}
