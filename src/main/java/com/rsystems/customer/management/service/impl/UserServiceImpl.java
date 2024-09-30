package com.rsystems.customer.management.service.impl;

import com.rsystems.customer.management.entity.User;
import com.rsystems.customer.management.repo.UserRepository;
import com.rsystems.customer.management.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        // check if user already exists by email
        return userRepository.save(user);
    }

}