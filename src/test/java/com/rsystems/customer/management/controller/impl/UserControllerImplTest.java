package com.rsystems.customer.management.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsystems.customer.management.entity.User;
import com.rsystems.customer.management.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UserControllerImpl.class)
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private User user;

    @Test
    @DisplayName("User can be created")
    void test_CreateUser() throws Exception {

        // Arrange
        user = new User("John", "Last", "john@mail.com");
        when(userService.createUser(any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/api/v1/users").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        User createdUser = new ObjectMapper().readValue(responseBodyAsString, User.class);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(createdUser.getEmail(), user.getEmail());
    }

    @Test
    @DisplayName("Invalid email when creating User")
    void test_CreatUserWithInvalidEmailFormat_return400Status() throws Exception {
        // Arrange
        user = new User("John", "Last", "johnmail.com"); //Invalid email
        when(userService.createUser(any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/api/v1/users").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals("Invalid request content.", mvcResult.getResponse().getErrorMessage());

    }

}