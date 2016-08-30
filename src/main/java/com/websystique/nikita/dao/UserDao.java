package com.websystique.nikita.dao;

import com.websystique.nikita.model.User;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 28.08.2016.
 */
public interface UserDao {

    User getUserByLogin(String login) throws SQLException;

}
