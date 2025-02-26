package com.amsoftacademy.touristsheet.adapter.in.rest.exception;

/**
 * @author Alex Pumnea
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
