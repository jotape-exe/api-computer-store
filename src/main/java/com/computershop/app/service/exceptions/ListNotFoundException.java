package com.computershop.app.service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ListNotFoundException extends EntityNotFoundException {
    public ListNotFoundException(String message) {
        super(message);
    }
}
