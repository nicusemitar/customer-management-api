package com.appsdeveloperblog.service;

import com.appsdeveloperblog.model.User;

public interface EmailNotificationService {
    void scheduleEmailConfirmation(User user);
}
