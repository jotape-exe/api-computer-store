package com.computershop.app.model.dto.request;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.Customer;
import com.computershop.app.model.Order;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class OrderDTO {

    @NotNull(message = "Invalid Input!")
    @Future
    private Date creationDate;
    @NotBlank(message = "Invalid Input!")
    private StatusOrder statusOrder;
    @NotNull(message = "Invalid Input!")
    private Long customerId;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Order toEntity(){
        Customer customer = new Customer(this.customerId);
        return new Order(this.creationDate, this.statusOrder, customer);
    }
}
