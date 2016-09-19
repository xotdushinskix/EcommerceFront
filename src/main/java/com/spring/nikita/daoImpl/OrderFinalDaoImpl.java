package com.spring.nikita.daoImpl;

import com.spring.nikita.dao.OrderFinalDao;
import com.spring.nikita.model.OrderFinal;
import com.spring.nikita.model.OrderLines;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by nikita on 01.09.16.
 */
@Repository("orderFinalDao")
public class OrderFinalDaoImpl implements OrderFinalDao {

    @Autowired
    private SessionFactory sessionFactory;


    public OrderFinal getOrderById(int orderId) throws SQLException {
        Session session = null;
        OrderFinal orderFinal = null;
        try {
            session = sessionFactory.openSession();
            orderFinal = (OrderFinal) session.get(OrderFinal.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return orderFinal;
    }



    public void addOrder(OrderFinal orderFinal) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orderFinal);
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
