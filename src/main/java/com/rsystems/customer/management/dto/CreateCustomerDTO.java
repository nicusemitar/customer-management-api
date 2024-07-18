package com.rsystems.customer.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rsystems.customer.management.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a new customer.
 *
 * @param id          the ID of the customer
 * @param name        the name of the customer
 * @param phoneNumber the phone number of the customer
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public record CreateCustomerDTO(
        @NotNull(message = "ID is required") Long id,
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Phone number is required") String phoneNumber) {

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
