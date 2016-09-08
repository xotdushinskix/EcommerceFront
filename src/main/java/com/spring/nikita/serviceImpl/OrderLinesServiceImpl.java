package com.spring.nikita.serviceImpl;

import com.spring.nikita.dao.OrderLinesDao;
import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.Product;
import com.spring.nikita.model.User;
import com.spring.nikita.service.OrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 08.09.16.
 */
@Service
public class OrderLinesServiceImpl implements OrderLinesService {

    @Autowired
    private OrderLinesDao orderLinesDao;

    public OrderLines getOrderLine(int orderLineId) throws SQLException {
        return orderLinesDao.getOrderLine(orderLineId);
    }

    public void editOrderLine(OrderLines orderLine) throws SQLException {
        orderLinesDao.editOrderLine(orderLine);
    }

    public void deleteOrderLine(OrderLines orderLine) throws SQLException {
        orderLinesDao.deleteOrderLine(orderLine);
    }

    public List<OrderLines> getAllOrderLines() throws SQLException {
        return orderLinesDao.getAllOrderLines();
    }

    public OrderLines getOrderLineBySerNameAndProduct(User user, Product product) throws SQLException {
        return orderLinesDao.getOrderLineBySerNameAndProduct(user, product);
    }

    public List<OrderLines> getAllUserLineByRequiredUserId(int id) throws SQLException {
        return orderLinesDao.getAllUserLineByRequiredUserId(id);
    }

    public List<OrderLines> getNotApprovedLineByUserId(int id) throws SQLException {
        return orderLinesDao.getNotApprovedLineByUserId(id);
    }
}
