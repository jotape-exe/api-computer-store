package com.computershop.app.model.dto.request;

import com.computershop.app.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddressDTO {

    @NotEmpty(message = "Invalid Input!")
    @Size(max = 8, min = 8)
    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Address toEntity(){
        return new Address(this.cep);
    }
}
