package com.appsdeveloperblog.repo;

import com.appsdeveloperblog.model.User;

public interface UserRepository {

    boolean save(User user);
}
