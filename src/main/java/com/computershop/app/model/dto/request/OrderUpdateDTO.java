package com.computershop.app.model.dto.request;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.Order;
import jakarta.validation.constraints.NotBlank;

public class OrderUpdateDTO {
    @NotBlank(message = "Invalid Input!")
    private StatusOrder statusOrder;

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public OrderUpdateDTO() {
    }

    public Order toEntity(Order order){
        order.setStatusOrder(this.statusOrder);
        return order;
    }
}
