package com.spring.nikita.daoImpl;

import com.spring.nikita.dao.OrderLinesDao;
import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.Product;
import com.spring.nikita.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 01.09.16.
 */
@Repository("orderLinesDao")
public class OrderLinesDaoImpl implements OrderLinesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public OrderLines getOrderLine(int orderLineId) throws SQLException {
        Session session = null;
        OrderLines orderLines = null;
        try {
            session = sessionFactory.openSession();
            orderLines = (OrderLines) session.get(OrderLines.class, orderLineId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return orderLines;
    }



    public void editOrderLine(OrderLines orderLine) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(orderLine);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }



    public void deleteOrderLine(OrderLines orderLine) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(orderLine);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }



    public List<OrderLines> getAllOrderLines() throws SQLException {
        List<OrderLines> orderLines = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            orderLines = session.createCriteria(OrderLines.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return orderLines;
    }



    public OrderLines getOrderLineBySerNameAndProduct(User user, Product product) throws SQLException {
        Session session = null;
        OrderLines orderLines = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(OrderLines.class)
                    .add(Restrictions.like("user", user))
                    .add(Restrictions.like("product", product));
            orderLines = (OrderLines) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return orderLines;
    }



    public List<OrderLines> getAllUserLineByRequiredUserLogin(String login) throws SQLException {
        List<OrderLines>orderLines = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(OrderLines.class)
                    .add(Restrictions.like("login", login))
                    .add(Restrictions.isNotNull("orderFinal.orderId"));
            orderLines = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return orderLines;
    }
}
