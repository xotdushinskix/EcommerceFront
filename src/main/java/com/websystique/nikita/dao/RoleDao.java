package com.websystique.nikita.dao;

import com.websystique.nikita.model.Role;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 30.08.2016.
 */
public interface RoleDao {

    Role getRoleByName(int role_id) throws SQLException;
    void addRole(Role role)throws SQLException;
    void editRole(Role role) throws SQLException;


}
