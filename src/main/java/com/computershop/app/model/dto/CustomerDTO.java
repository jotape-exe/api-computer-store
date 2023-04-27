package com.computershop.app.model.dto;

import com.computershop.app.model.Customer;
import com.computershop.app.model.dto.request.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    @NotBlank
    @Size(min = 2, max = 140)
    private String name;

    @NotBlank
    @Size(max = 25)
    private String phone;

    @NotNull
    private AddressDTO addressDTO;

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

    public AddressDTO getAddressRequest() {
        return addressDTO;
    }

    public void setAddressRequest(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public Customer toEntity(){
        return new Customer(this.name,this.phone, this.addressDTO.toEntity());
    }
}

