package com.spring.nikita.service;

import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.Product;
import com.spring.nikita.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 08.09.16.
 */
public interface OrderLinesService {

    OrderLines getOrderLine (int orderLineId) throws SQLException;
    void editOrderLine (OrderLines orderLine) throws SQLException;
    void deleteOrderLine (OrderLines orderLine) throws SQLException;
    List<OrderLines> getAllOrderLines() throws SQLException;
    OrderLines getOrderLineBySerNameAndProduct (User user, Product product) throws SQLException;
    List<OrderLines> getAllUserLineByRequiredUser(User user) throws SQLException;
    List<OrderLines> getNotApprovedLineByUserId(int id) throws SQLException;

}
