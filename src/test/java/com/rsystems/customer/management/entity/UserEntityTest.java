package com.rsystems.customer.management.entity;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DataJpaTest
class UserEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private User user;

    @Test
    void testUserEntity_whenValidUserDetailsProvided_returnStoredDetails() {

        // Arrange
        user = new User("John", "Last", "john@mail.com");

        // Act
        User storedUserDb = testEntityManager.persistAndFlush(user);

        // Assert
        Assertions.assertTrue(storedUserDb.getId() > 0);
    }

    @Test
    void testUserEntity_invalidEmailProvided() {

        // Arrange
        user = new User("John", "Last", "johnmail.com");

        // Act
        ConstraintViolationException constraintViolationException = assertThrowsExactly(
                ConstraintViolationException.class,
                () -> testEntityManager.persistAndFlush(user),
                "Should throw Validation exception for invalid email"
        );

        // Assert
        Assertions.assertNotNull(constraintViolationException);
        Assertions.assertTrue(constraintViolationException.getMessage().contains("must be a well-formed email address"),
                "Expected email validation error message");

    }

    @Test
    void testUniqueUserId() {

        // Arrange
        user = new User("John", "Last", "john@mail.com");
        User secondUser = new User("Jane", "Doe", "jane@mail.com");


        // Act
        User storedFirstUser = testEntityManager.persistAndFlush(user); // persist first user
        User storedSecondUser = testEntityManager.persistAndFlush(secondUser); // persist second user

        // Assert
        Assertions.assertNotNull(storedFirstUser.getId());
        Assertions.assertNotNull(storedSecondUser.getId());

        // Assert that the IDs are different
        Assertions.assertNotEquals(storedFirstUser.getId(), storedSecondUser.getId(), "User IDs should be unique.");
    }

}