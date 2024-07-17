package com.rsystems.customer.management.dto;

import com.rsystems.customer.management.entity.Customer;

/**
 * Data Transfer Object for updating an existing customer.
 *
 * @param id   the ID of the customer
 * @param name the name of the customer
 */
public record UpdateCustomerDTO(Long id, String name) {

    /**
     * Converts a Customer entity to an UpdateCustomerDTO.
     *
     * @param customer the customer entity to convert
     * @return the corresponding UpdateCustomerDTO
     */
    public static UpdateCustomerDTO toDTO(Customer customer) {
        return new UpdateCustomerDTO(customer.getId(), customer.getName());
    }
}
