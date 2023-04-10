package com.computershop.app.model.dto;

import com.computershop.app.model.Address;
import com.computershop.app.model.dto.request.AddressRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClientDTO {
    private Long Id;

    @NotBlank
    @Size(min = 2, max = 140)
    private String name;

    @NotBlank
    @Size(max = 25)
    private String phone;

    @NotNull
    private AddressRequest addressRequest;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }
}
