package com.appsdeveloperblog.service;

import com.appsdeveloperblog.model.User;

public class EmailNotificationServiceImpl implements EmailNotificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        System.out.println("call this method");
    }
}
