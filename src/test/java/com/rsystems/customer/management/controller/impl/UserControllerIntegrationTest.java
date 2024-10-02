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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

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

    @Test
    @DisplayName("GET /api/v1/users")
    void test_FetchUserIntegrationTest() {

        //Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        //Act
        ResponseEntity<List<User>> responseEntity =
                testRestTemplate.exchange("http://localhost:8080/api/v1/users",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }
                );

        //Assert
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
