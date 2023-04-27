package com.computershop.app.model.dto;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.Order;
import jakarta.validation.constraints.NotBlank;

public class OrderUpdateDTO {
    @NotBlank
    private StatusOrder statusOrder;

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public OrderUpdateDTO() {
    }

    public Order toENtity(Order order){
        order.setStatusOrder(this.statusOrder);
        return order;
    }
}
