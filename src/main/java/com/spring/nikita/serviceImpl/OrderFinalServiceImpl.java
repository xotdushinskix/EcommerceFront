package com.spring.nikita.serviceImpl;

import com.spring.nikita.dao.OrderFinalDao;
import com.spring.nikita.model.OrderFinal;
import com.spring.nikita.model.OrderLines;
import com.spring.nikita.service.OrderFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by nikita on 01.09.16.
 */
@Service("orderFinalService")
public class OrderFinalServiceImpl implements OrderFinalService {

    @Autowired
    private OrderFinalDao orderFinalDao;

    public OrderFinal getOrderById(int orderId) throws SQLException {
        return orderFinalDao.getOrderById(orderId);
    }

    public void addOrder(OrderFinal orderFinal) throws SQLException {
        orderFinalDao.addOrder(orderFinal);
    }
}
