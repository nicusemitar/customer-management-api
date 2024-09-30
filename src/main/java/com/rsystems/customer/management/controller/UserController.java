package com.rsystems.customer.management.controller;

import com.rsystems.customer.management.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<User> createUser(User user);

    ResponseEntity<List<User>> getAllCustomers();
}
