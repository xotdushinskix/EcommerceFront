package com.spring.nikita.serviceImpl;

import com.spring.nikita.dao.RoleDao;
import com.spring.nikita.model.Role;
import com.spring.nikita.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 30.08.2016.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

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
