package com.websystique.springsecurity.serviceImpl;

import com.websystique.springsecurity.dao.UserDaoS;
import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.service.UserServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 28.08.2016.
 */
@Service("userServiceS")
@Transactional
public class UserServiceImplS implements UserServiceS {

    @Autowired
    private UserDaoS userDaoS;

    public User getUserByEmail(String email) throws SQLException {
        return userDaoS.getUserByEmail(email);
    }
}
