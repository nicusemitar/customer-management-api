package com.rsystems.customer.management.service.impl;

import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import com.rsystems.customer.management.exception.CustomerAlreadyExistsException;
import com.rsystems.customer.management.exception.CustomerNotFoundException;
import com.rsystems.customer.management.repo.FileBaseRepository;
import com.rsystems.customer.management.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing customer data.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final FileBaseRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(FileBaseRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Fetches the list of all customers.
     *
     * @return a list of all customers
     */
    @Override
    public List<Customer> fetchCustomerList() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     * @throws CustomerNotFoundException if no customer with the specified ID is found
     */
    @Override
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    /**
     * Adds a new customer.
     *
     * @param customerDTO the DTO containing the details of the customer to add
     * @return the newly added customer
     * @throws CustomerAlreadyExistsException if a customer with the specified ID already exists
     */
    @Override
    public Customer addCustomer(CreateCustomerDTO customerDTO) throws CustomerAlreadyExistsException {
        Optional<Customer> existingCustomerId = customerRepository.findById(customerDTO.id());
        if (existingCustomerId.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with id " + customerDTO.id() + " " +
                    "already exists, please provide another id.");
        }

        Customer newCustomer = new Customer(
                customerDTO.id(), customerDTO.name(), customerDTO.phoneNumber());
        logger.debug("Creating new customer with id: {}", customerDTO.id());
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    /**
     * Updates an existing customer.
     *
     * @param updateCustomerDTO the DTO containing the details of the customer to update
     * @return the updated customer
     * @throws CustomerNotFoundException if no customer with the specified ID is found
     */
    @Override
    public Customer updateCustomer(UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException {
        Optional<Customer> existingCustomer = customerRepository.findById(updateCustomerDTO.id());
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setName(updateCustomerDTO.name());
            logger.debug("Updating customer with id: {}", updateCustomerDTO.id());
            customerRepository.save(updatedCustomer);
            return updatedCustomer;
        } else {
            throw new CustomerNotFoundException("Unable to find customer with id: " + updateCustomerDTO.id() +
                    "-update operation failed");
        }
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     * @throws CustomerNotFoundException if no customer with the specified ID is found
     */
    @Override
    public void deleteCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            logger.debug("Deleting customer with id: {}", id);
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Unable to find customer with id: " + id);
        }
    }
}
