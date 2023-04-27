package com.computershop.app.model.dto.response;

import com.computershop.app.model.Address;
import com.computershop.app.model.Customer;
public class CustomerView {
    private String name;

    private String phone;

    private Address address;

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

    public CustomerView(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public CustomerView(Customer customer){
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
    }
}
