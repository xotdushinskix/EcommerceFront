package com.websystique.nikita.daoImpl;

import com.websystique.nikita.dao.UserDao;
import com.websystique.nikita.model.User;
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
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    public User getUserByLogin(String login) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.like("login", login)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return user;
    }
}
