package com.rsystems.customer.management.service;

import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import com.rsystems.customer.management.exception.CustomerAlreadyExistsException;
import com.rsystems.customer.management.exception.CustomerNotFoundException;

import java.util.List;

/**
 * Service interface for managing customer data.
 */
public interface CustomerService {

    List<Customer> fetchCustomerList();

    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    Customer addCustomer(CreateCustomerDTO createCustomerDTO) throws CustomerAlreadyExistsException;

    Customer updateCustomer(UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException;

    void deleteCustomerById(Long id) throws CustomerNotFoundException;
}
