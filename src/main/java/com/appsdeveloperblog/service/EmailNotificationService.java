package com.appsdeveloperblog.service;

import com.rsystems.customer.management.entity.User;

public interface EmailNotificationService {
    void scheduleEmailConfirmation(User user);
}
