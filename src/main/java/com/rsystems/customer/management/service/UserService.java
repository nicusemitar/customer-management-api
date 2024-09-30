package com.rsystems.customer.management.service;

import com.appsdeveloperblog.exception.UserServiceException;
import com.rsystems.customer.management.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserServiceException;
    List<User> fetchUserList();

}