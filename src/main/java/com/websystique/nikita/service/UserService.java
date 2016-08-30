package com.websystique.nikita.service;

import com.websystique.nikita.model.User;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 28.08.2016.
 */
public interface UserService {

    User getUserByLogin(String login) throws SQLException;
    void addUser(User user)throws SQLException;
    void editUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;

}
