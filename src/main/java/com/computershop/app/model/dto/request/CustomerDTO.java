package com.computershop.app.model.dto.request;

import com.computershop.app.model.Address;
import com.computershop.app.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    @NotBlank(message = "Invalid Input!")
    @Size(min = 2, max = 140)
    private String name;

    @NotBlank(message = "Invalid Input!")
    @Size(max = 25)
    private String phone;

    @NotBlank(message = "Invalid Input!")
    private String zipCode;

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer toEntity(){
        Address address = new Address(this.zipCode);
        return new Customer(this.name,this.phone, address);
    }
    public Customer toEntity(Customer customer){
        Address address = new Address(this.zipCode);
        customer.setName(this.name);
        customer.setPhone(this.phone);
        customer.setAddress(address);
        return customer;
    }
}

