package com.revature.DAOS;

import com.revature.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<Users, Long> {
    static Optional<Users> findByUserId(Integer user) {
        return null;
    }
}
