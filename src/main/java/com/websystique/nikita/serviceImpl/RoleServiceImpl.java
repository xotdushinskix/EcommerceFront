package com.websystique.nikita.serviceImpl;

import com.websystique.nikita.dao.RoleDao;
import com.websystique.nikita.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 30.08.2016.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleDao {

    @Autowired
    private RoleDao roleDao;

    public Role getRoleByName(int role_id) throws SQLException {
        return roleDao.getRoleByName(role_id);
    }

    public void addRole(Role role) throws SQLException {
        roleDao.addRole(role);
    }

    public void editRole(Role role) throws SQLException {
        roleDao.editRole(role);
    }
}
