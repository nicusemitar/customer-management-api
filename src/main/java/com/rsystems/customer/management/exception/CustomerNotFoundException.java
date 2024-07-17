package com.rsystems.customer.management.exception;

/**
 * Exception thrown when a customer is not found.
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
