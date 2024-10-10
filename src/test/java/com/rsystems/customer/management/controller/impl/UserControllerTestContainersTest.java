package com.rsystems.customer.management.controller.impl;

import com.rsystems.customer.management.entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserControllerTestContainersTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    @ServiceConnection
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.13-alpine");
//            .withDatabaseName("customers_project")
//            .withUsername("postgres")
//            .withPassword("postgres");

// Using @ServiceConnection instead
//    @DynamicPropertySource
//    private static void overrideProperties(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url",postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username",postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password",postgreSQLContainer::getPassword);
//    }

    @Test
    @DisplayName("The PostgresSQLContainer is created and running")
    void testIfContainerIsCreated() {
        Assertions.assertTrue(postgreSQLContainer.isCreated());
        Assertions.assertTrue(postgreSQLContainer.isRunning());
    }

    @Test
    @DisplayName("User can be created - Integration test")
    void test_CreateUserIntegrationTest() throws JSONException {

        //Arrange
        JSONObject userJson = new JSONObject();
        userJson.put("firstName", "First Name");
        userJson.put("lastName", "Last Name");
        userJson.put("email", "email@mail.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(userJson.toString(), headers);

        //Act
        ResponseEntity<User> responseEntity =
                testRestTemplate.postForEntity("http://localhost:8080/api/v1/users", requestEntity, User.class);

        //Assert
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(userJson.getString("email"), Objects.requireNonNull(responseEntity.getBody()).getEmail());
    }

}