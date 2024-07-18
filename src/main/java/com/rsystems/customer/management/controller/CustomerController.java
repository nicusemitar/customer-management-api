package com.rsystems.customer.management.controller;

import com.rsystems.customer.management.dto.CreateCustomerDTO;
import com.rsystems.customer.management.dto.UpdateCustomerDTO;
import com.rsystems.customer.management.entity.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface for managing customer data via REST endpoints.
 */
public interface CustomerController {
    @Operation(
            description = "Returns all existing customers in the file"
    )
    ResponseEntity<List<Customer>> getAllCustomers();

    @Operation(
            description = "Retrieves a customer by given ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the customer to retrieve",
                            required = true

                    )
            },
            responses = {
                    @ApiResponse(
                            description = "When customer is present in the file",
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))
                    )
            }
    )
    Customer getCustomerById(Long id);

    @Operation(
            description = "Creates a new customer and returns the created customer",
            requestBody = @RequestBody(
                    description = "Customer data to create",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateCustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Customer created successfully",
                            responseCode = "201",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    )
            }
    )
    ResponseEntity<Customer> createCustomer(CreateCustomerDTO customerDTO);

    @Operation(
            description = "Updates the name of an existing customer by given ID and returns the updated customer",
            requestBody = @RequestBody(
                    description = "Customer name to update",
                    required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateCustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Customer name updated successfully",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UpdateCustomerDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    )
            }
    )
    ResponseEntity<UpdateCustomerDTO> updateCustomer(UpdateCustomerDTO updateCustomerDTO);

    @Operation(
            summary = "Delete a customer",
            description = "Deletes a customer by given ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the customer to delete",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "Customer deleted successfully",
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string")
                            )
                    )
            }
    )
    ResponseEntity<String> deleteCustomer(Long id);

}
