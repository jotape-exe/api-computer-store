package com.computershop.app.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ZipCodeException extends SQLIntegrityConstraintViolationException {
    public ZipCodeException(String message) {
        super(message);
    }
}
