package com.rsystems.customer.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rsystems.customer.management.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for updating an existing customer.
 *
 * @param id   the ID of the customer
 * @param name the name of the customer
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public record UpdateCustomerDTO(
        @NotNull(message = "ID is required") Long id,
        @NotBlank(message = "Name is required") String name
) {

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
