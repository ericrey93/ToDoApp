package com.revature.Services;

import com.revature.DAOS.ToDoDao;
import com.revature.DAOS.UserDao;
import com.revature.Models.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    UserDao userDao;

    @Autowired
    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    public Users registerUser(Users user) {
        if((user.getUsername() == null)&&(user.getPassword()== null)) {
            return null;
        } else {
            Users newUser = new Users();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            return userDao.save(newUser);
        }
    }

    public Users persistUser(Users user) {
        return userDao.save(user);
    }

    public Users findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Users findById(long toDoId) {
        return userDao.getReferenceById(toDoId);
    }
}
