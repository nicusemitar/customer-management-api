package com.rsystems.customer.management.exception;

/**
 * Exception thrown when a customer already exists.
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    /**
     * Constructs a new CustomerAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
