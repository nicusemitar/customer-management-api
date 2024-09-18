package com.appsdeveloperblog.service;

import com.appsdeveloperblog.exception.UserServiceException;
import com.appsdeveloperblog.model.User;
import com.appsdeveloperblog.repo.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

    UserRepositoryImpl userRepository;
    EmailNotificationServiceImpl emailNotificationService;

    public UserServiceImpl(UserRepositoryImpl userRepository, EmailNotificationServiceImpl emailNotificationService) {
        this.userRepository = userRepository;
        this.emailNotificationService = emailNotificationService;
    }

    @Override
    public User createUser(User user) throws UserServiceException {
        if (user == null) throw new IllegalArgumentException("User can not be null");
        boolean isUserCreated = userRepository.save(user);
        if (!isUserCreated) throw new UserServiceException("Could not create user");

        try {
            emailNotificationService.scheduleEmailConfirmation(user);
        } catch (RuntimeException exception) {
            throw new UserServiceException(exception.getMessage());
        }
        return user;
    }


}
