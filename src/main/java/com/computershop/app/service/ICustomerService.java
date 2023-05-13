package com.computershop.app.service;

import com.computershop.app.service.exceptions.ZipCodeException;

import java.util.ArrayList;

public interface ICustomerService<T> {
    T findById(Long id);

    ArrayList<T> findAll();

    T create(T t)throws ZipCodeException;

    T update(T t);

    void delete(Long id);
}
