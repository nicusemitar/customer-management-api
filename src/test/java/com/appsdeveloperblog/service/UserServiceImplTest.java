package com.appsdeveloperblog.service;

import com.appsdeveloperblog.io.UsersDatabase;
import com.appsdeveloperblog.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    UsersDatabase usersDatabase;
    UserServiceImpl userServiceImpl;
    String createdUserId = "";

    @BeforeAll
    void setup() {
        // Create & initialize database
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();

        userServiceImpl = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
        // Arrange
        Map<String, String> userMap = new HashMap<>();
        userMap.put("1", "First User");
        userMap.put("2", "Second User");

        // Act
        createdUserId = userServiceImpl.createUser(userMap);

        // Assert
        assertNotNull(createdUserId);
    }

    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {
        // Arrange
        Map<String, String> userMap = new HashMap<>();
        userMap.put("firstName", "John");
        userMap.put("lastName", "Lee");

        // Act
        Map updatedUserDetails = userServiceImpl.updateUser(createdUserId, userMap);

        // Assert
        assertEquals("John", updatedUserDetails.get("firstName"));
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

        //Act
        Map foundUser = usersDatabase.find(createdUserId);

        // Assert
        assertNotNull(foundUser);
        assertEquals("John", foundUser.get("firstName"));
        assertEquals(createdUserId, foundUser.get("userId"));
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

        //Act
        usersDatabase.delete(createdUserId);

        //Assert
        assertNull(userServiceImpl.getUserDetails(createdUserId));
    }

}
