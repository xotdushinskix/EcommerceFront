package com.spring.nikita.service;

import java.sql.SQLException;

/**
 * Created by nikita on 16.09.16.
 */
public interface UserRolesService {

    void addUserRole(com.spring.nikita.model.UserRoles userRoles) throws SQLException;

}
