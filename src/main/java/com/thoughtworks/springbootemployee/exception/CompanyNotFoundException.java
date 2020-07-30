package com.thoughtworks.springbootemployee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {

    }

    public CompanyNotFoundException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void exceptionHandle() {

    }
}
