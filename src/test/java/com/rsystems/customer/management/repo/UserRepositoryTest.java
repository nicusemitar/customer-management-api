package com.rsystems.customer.management.repo;

import com.rsystems.customer.management.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testFindUserByEmail__whenGivenEmail_returnDesiredUser() {

        //Arrange
        User user = new User("John", "Last", "john@mail.com");
        testEntityManager.persistAndFlush(user);

        //Act
        String emailName = "john@mail.com";
        userRepository.findUserByEmail(emailName);

        //Assert
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("John", user.getFirstName());

    }
}