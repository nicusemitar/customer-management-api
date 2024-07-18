package com.rsystems.customer.management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nicolae Semitar",
                        email = "nicolae.semitar@rsystems.com"
                ),
                description = "OpenAPI documentation for Customer Management API",
                title = "OpenAPI specification - Customer Management API",
                version = "1.0.0"
        ),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8080"
        )
)
public class OpenApiConfig {
}
