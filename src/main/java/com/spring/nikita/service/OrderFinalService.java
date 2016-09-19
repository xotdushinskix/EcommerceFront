package com.spring.nikita.service;

import com.spring.nikita.model.OrderFinal;
import com.spring.nikita.model.OrderLines;

import javax.persistence.criteria.Order;
import java.sql.SQLException;

/**
 * Created by nikita on 01.09.16.
 */
public interface OrderFinalService {

    OrderFinal getOrderById(int orderId) throws SQLException;
    void addOrder(OrderFinal orderFinal) throws SQLException;

}
