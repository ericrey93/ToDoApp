package com.revature.DAOS;


import com.revature.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    static Optional<Users> findById(long userId) {
        return null;
    }
}
