package com.spring.nikita.serviceImpl;

import com.spring.nikita.dao.UserRolesDao;
import com.spring.nikita.model.UserRoles;
import com.spring.nikita.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by nikita on 16.09.16.
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesDao userRolesDao;

    @Override
    public void addUserRole(UserRoles userRoles) throws SQLException {
        userRolesDao.addUserRole(userRoles);
    }
}
