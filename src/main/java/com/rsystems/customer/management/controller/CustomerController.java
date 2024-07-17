package com.rsystems.customer.management.controller;

import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface for managing customer data via REST endpoints.
 */
public interface CustomerController {
    ResponseEntity<List<Customer>> getAllCustomers();

    Customer getCustomerById(Long id);

    ResponseEntity<Customer> createCustomer(CreateCustomerDTO customerDTO);

    ResponseEntity<UpdateCustomerDTO> updateCustomer(UpdateCustomerDTO updateCustomerDTO);

    ResponseEntity<String> deleteCustomer(Long id);

}
