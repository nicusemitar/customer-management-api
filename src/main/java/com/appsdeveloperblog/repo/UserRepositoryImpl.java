package com.appsdeveloperblog.repo;

import com.appsdeveloperblog.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean save(User user) {
        boolean returnedValue = false;
        Map<String, User> usersMap = new HashMap<>();

        if (!usersMap.containsKey(user.getId())) {
            usersMap.put(user.getId(), user);
            returnedValue = true;
        }

        return returnedValue;
    }
}
