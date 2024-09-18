package com.appsdeveloperblog.service;

import com.appsdeveloperblog.exception.UserServiceException;
import com.appsdeveloperblog.model.User;

public interface UserService {
    User createUser(User user) throws UserServiceException;
}