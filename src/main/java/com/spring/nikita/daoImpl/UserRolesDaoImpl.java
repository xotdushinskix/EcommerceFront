package com.spring.nikita.daoImpl;

import com.spring.nikita.dao.UserRolesDao;
import com.spring.nikita.model.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by nikita on 16.09.16.
 */
@Repository
public class UserRolesDaoImpl implements UserRolesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUserRole(UserRoles userRoles) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(userRoles);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }
}
