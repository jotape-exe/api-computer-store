package com.computershop.app.model.dto;

import com.computershop.app.enums.StatusOrder;
import com.computershop.app.model.dto.request.ClientRequest;
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
    private ClientRequest clientRequest;

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

    public ClientRequest getClientResponse() {
        return clientRequest;
    }

    public void setClientResponse(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }
}
