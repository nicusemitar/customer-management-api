//package com.appsdeveloperblog.service;
//
//import com.appsdeveloperblog.exception.EmailNotificationServiceException;
//import com.appsdeveloperblog.exception.UserServiceException;
//import com.rsystems.customer.management.entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceMockTest {
//
//    UserServiceImpl userService;

//    @Mock
//    UserRepositoryImpl userRepository;
//
//    @Mock
//    EmailNotificationServiceImpl emailNotificationService;
//
//    User user;
//
//    @BeforeEach
//    void init() {
//
//        userService = new UserServiceImpl(userRepository, emailNotificationService);
//        user = new User("John", "Last", "john@mail.com");
//
//    }
//
//    @DisplayName("Test create a new user")
//    @Test
//    void testCreateNewUser() throws UserServiceException {
//        //Arrange
//        when(userRepository.save(user)).thenReturn(true);
//
//        User userCreated = userService.createUser(user);
//        verify(userRepository, times(1)).save(user);
//        assertEquals("John", userCreated.getFirstName());
//
//    }
//
//    @Disabled
//    @DisplayName("Test create a new user with Exception")
//    @Test
//    void testCreateNewUser_ThrowsException() {
//        //Arrange
//        when(userRepository.save(user)).thenThrow(UserServiceException.class);
//
//        //Act & Assert
//        assertThrows(UserServiceException.class,
//                () -> userService.createUser(user));
//
//    }
//
//    @Test
//    void testEmailService_ThrowsException() {
//
//        //Arrange
//        when(userRepository.save(user)).thenReturn(true);
//        doThrow(EmailNotificationServiceException.class)
//                .when(emailNotificationService)
//                .scheduleEmailConfirmation(user);
//
//        //Act & Assert
//        assertThrows(UserServiceException.class, () ->
//                userService.createUser(user));
//    }
//
//    @Test
//    void testEmailService_Invocation() throws UserServiceException {
//
//        //Arrange
//        when(userRepository.save(user)).thenReturn(true);
//        doNothing().when(emailNotificationService).scheduleEmailConfirmation(user);
//
//        //Act & Assert
//        userService.createUser(user);
//        verify(emailNotificationService, times(1)).scheduleEmailConfirmation(user);
//    }
//
//    @Test
//    void testEmailService_doCallRealMethod() throws UserServiceException {
//
//        //Arrange
//        when(userRepository.save(user)).thenReturn(true);
//
//        doCallRealMethod().when(emailNotificationService).scheduleEmailConfirmation(user);
//
//        //Act & Assert
//        userService.createUser(user);
//        verify(emailNotificationService, times(1)).scheduleEmailConfirmation(user);
//    }
//
//
//}