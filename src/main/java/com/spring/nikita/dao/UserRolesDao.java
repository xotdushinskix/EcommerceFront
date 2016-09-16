package com.spring.nikita.dao;

import java.sql.SQLException;

/**
 * Created by nikita on 16.09.16.
 */
public interface UserRolesDao {

    void addUserRole(com.spring.nikita.model.UserRoles userRoles) throws SQLException;

}
