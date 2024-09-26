package com.appsdeveloperblog.service;


import com.rsystems.customer.management.entity.User;

public class EmailNotificationServiceImpl implements EmailNotificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        System.out.println("call this method");
    }
}
