package com.computershop.app.model.dto;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.dto.request.CostumerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class OrderDTO {

    private Long id;

    @NotNull
    private Date creationDate;
    @NotBlank
    private StatusOrder statusOrder;
    @NotNull
    private CostumerRequest costumerRequest;

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

    public CostumerRequest getCostumerRequest() {
        return costumerRequest;
    }

    public void setCostumerRequest(CostumerRequest costumerRequest) {
        this.costumerRequest = costumerRequest;
    }
}
