package com.rsystems.customer.management.dto;

import com.rsystems.customer.management.entity.Customer;

/**
 * Data Transfer Object for creating a new customer.
 *
 * @param id          the ID of the customer
 * @param name        the name of the customer
 * @param phoneNumber the phone number of the customer
 */
public record CreateCustomerDTO(Long id, String name, String phoneNumber) {

    /**
     * Converts a Customer entity to a CreateCustomerDTO.
     *
     * @param customer the customer entity to convert
     * @return the corresponding CreateCustomerDTO
     */
    public static CreateCustomerDTO toDTO(Customer customer) {
        return new CreateCustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber());
    }

}
