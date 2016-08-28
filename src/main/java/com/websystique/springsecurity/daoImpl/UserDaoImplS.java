package com.websystique.springsecurity.daoImpl;

import com.websystique.springsecurity.dao.UserDaoS;
import com.websystique.springsecurity.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 28.08.2016.
 */
@Repository("userDaoS")
public class UserDaoImplS implements UserDaoS {

    @Autowired
    private SessionFactory sessionFactory;

    public User getUserByEmail(String email) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.like("email", email)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session.isOpen()) && (session != null)) {
                session.close();
            }
        }
        return user;
    }

}
