package com.rsystems.customer.management.controller.impl;

import com.rsystems.customer.management.controller.UserController;
import com.rsystems.customer.management.entity.User;
import com.rsystems.customer.management.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User Controller")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserControllerImpl(final UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllCustomers() {
        List<User> allUsers = userService.fetchUserList();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}

