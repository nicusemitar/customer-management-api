package com.rsystems.customer.management.service.impl;

import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import com.rsystems.customer.management.exception.CustomerAlreadyExistsException;
import com.rsystems.customer.management.exception.CustomerNotFoundException;
import com.rsystems.customer.management.repo.FileBaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private FileBaseRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testFetchCustomerList() {
        // setUp
        Customer customer1 = new Customer(1L, "John Doe", "1234567890");
        Customer customer2 = new Customer(2L, "Jane Smith", "0987654321");
        List<Customer> customerList = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.fetchCustomerList();

        // Assert
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById_Success() throws CustomerNotFoundException {
        // setUp
        Customer customer = new Customer(1L, "John Doe", "1234567890");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1L);

        // Assert
        assertEquals(customer, result);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() {
        // setUp
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testAddCustomer_Success() throws CustomerAlreadyExistsException {
        // setUp
        CreateCustomerDTO customerDTO = new CreateCustomerDTO(1L, "John Doe", "1234567890");
        when(customerRepository.findById(customerDTO.id())).thenReturn(Optional.empty());

        Customer result = customerService.addCustomer(customerDTO);

        // Assert
        assertEquals(customerDTO.id(), result.getId());
        assertEquals(customerDTO.name(), result.getName());
        assertEquals(customerDTO.phoneNumber(), result.getPhoneNumber());
        verify(customerRepository, times(1)).findById(customerDTO.id());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testAddCustomer_AlreadyExists() {
        // setUp
        CreateCustomerDTO customerDTO = new CreateCustomerDTO(1L, "John Doe", "1234567890");
        Customer existingCustomer = new Customer(1L, "Existing Customer", "0987654321");
        when(customerRepository.findById(customerDTO.id())).thenReturn(Optional.of(existingCustomer));

        // Assert
        assertThrows(CustomerAlreadyExistsException.class, () -> customerService.addCustomer(customerDTO));
        verify(customerRepository, times(1)).findById(customerDTO.id());
    }

    @Test
    void testUpdateCustomer_Success() throws CustomerNotFoundException {
        // setUp
        UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO(1L, "John Doe Updated");
        Customer existingCustomer = new Customer(1L, "John Doe", "1234567890");
        when(customerRepository.findById(updateCustomerDTO.id())).thenReturn(Optional.of(existingCustomer));

        Customer result = customerService.updateCustomer(updateCustomerDTO);

        // Assert
        assertEquals(updateCustomerDTO.id(), result.getId());
        assertEquals(updateCustomerDTO.name(), result.getName());
        verify(customerRepository, times(1)).findById(updateCustomerDTO.id());
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    void testUpdateCustomer_NotFound() {
        // setUp
        UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO(1L, "John Doe Updated");
        when(customerRepository.findById(updateCustomerDTO.id())).thenReturn(Optional.empty());

        // Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(updateCustomerDTO));
        verify(customerRepository, times(1)).findById(updateCustomerDTO.id());
    }

    @Test
    void testDeleteCustomerById_Success() throws CustomerNotFoundException {
        // setUp
        Customer existingCustomer = new Customer(1L, "John Doe", "1234567890");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));

        customerService.deleteCustomerById(1L);

        // Assert
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomerById_NotFound() {
        // setUp
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }
}