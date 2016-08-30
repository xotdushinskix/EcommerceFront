package com.websystique.nikita.serviceImpl;

import com.websystique.nikita.dao.UserDao;
import com.websystique.nikita.model.User;
import com.websystique.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 28.08.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User getUserByLogin(String login) throws SQLException {
        return userDao.getUserByLogin(login);
    }

    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    public void editUser(User user) throws SQLException {
        userDao.editUser(user);
    }

    public void deleteUser(User user) throws SQLException {
        userDao.deleteUser(user);
    }
}
