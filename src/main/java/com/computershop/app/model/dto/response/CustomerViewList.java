package com.computershop.app.model.dto.response;

import com.computershop.app.model.Address;
import com.computershop.app.model.Customer;

public class CustomerViewList {
    private Long id;
    private String name;
    private String phone;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerViewList(Customer customer){
        this.address = customer.getAddress();
        this.id = customer.getId();
        this.phone = customer.getPhone();
        this.name = customer.getName();
    }
}
