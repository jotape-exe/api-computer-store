package com.computershop.app.service;

import java.util.ArrayList;

public interface CrudService<T> {
    T findById(Long id);

    ArrayList<T> findAll();

    T create(T t);

    T update(T t);

    void delete(Long id);


}
