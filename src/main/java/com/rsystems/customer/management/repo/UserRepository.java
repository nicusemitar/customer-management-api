package com.rsystems.customer.management.repo;

import com.rsystems.customer.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
