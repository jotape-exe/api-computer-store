package com.computershop.app.model.dto.request;

import jakarta.validation.constraints.NotNull;

public class ClientRequest {
    @NotNull
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}