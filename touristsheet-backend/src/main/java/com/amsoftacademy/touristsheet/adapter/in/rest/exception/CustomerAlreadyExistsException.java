package com.amsoftacademy.touristsheet.adapter.in.rest.exception;

/**
 * @author Alex Pumnea
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
