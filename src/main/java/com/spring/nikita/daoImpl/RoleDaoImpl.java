package com.spring.nikita.daoImpl;

import com.spring.nikita.dao.RoleDao;
import com.spring.nikita.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 30.08.2016.
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;


    public Role getRoleByName(int role_id) throws SQLException {
        Session session = null;
        Role role = null;
        try {
            session = sessionFactory.openSession();
            role = (Role) session.get(Role.class, role_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return role;
    }



    public void addRole(Role role) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session.isOpen()) && (session != null)) {
                session.close();
            }
        }
    }



    public void editRole(Role role) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session.isOpen()) && (session != null)) {
                session.close();
            }
        }
    }

}
