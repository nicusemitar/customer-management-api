package com.rsystems.customer.management.controller.impl;

import com.rsystems.customer.management.controller.CustomerController;
import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import com.rsystems.customer.management.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customer data.
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerControllerImpl(final CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves all customers.
     *
     * @return a ResponseEntity containing a list of all customers and an HTTP status of OK
     */
    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerService.fetchCustomerList();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Creates a new customer.
     *
     * @param customerDTO the DTO containing the details of the customer to create
     * @return a ResponseEntity containing the newly created customer and an HTTP status of CREATED
     */
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDTO customerDTO) {
        Customer newCustomer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    /**
     * Updates an existing customer.
     *
     * @param updateCustomerDTO the DTO containing the details of the customer to update
     * @return a ResponseEntity containing the updated customer and an HTTP status of OK
     */
    @PutMapping("")
    public ResponseEntity<UpdateCustomerDTO> updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO) {
        UpdateCustomerDTO updatedCustomer = UpdateCustomerDTO.toDTO(customerService.updateCustomer(updateCustomerDTO));
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     * @return a ResponseEntity containing a confirmation message and an HTTP status of ACCEPTED
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer with id " + id + ", successfully deleted!", HttpStatus.ACCEPTED);
    }

}
