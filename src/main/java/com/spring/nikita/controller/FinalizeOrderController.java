package com.spring.nikita.controller;

import com.spring.nikita.model.OrderFinal;
import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.Product;
import com.spring.nikita.service.OrderFinalService;
import com.spring.nikita.service.OrderLinesService;
import com.spring.nikita.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikita on 19.09.16.
 */
@Controller
public class FinalizeOrderController extends GetUserName {

    @Autowired
    private OrderFinalService orderFinalService;

    @Autowired
    private OrderLinesService orderLinesService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/cart/finalize_order", method = RequestMethod.POST)
    public String finalizeOrder(@ModelAttribute OrderFinal orderFinal) throws SQLException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        int userId = super.getUserId();
        orderFinal = new OrderFinal();
        orderFinal.setOrderLines(orderLinesService.getNotApprovedLineByUserId(userId));
        orderFinal.setCreatedData(dateFormat.format(date));
        orderFinal.setShipStatus(false);
        orderFinalService.addOrder(orderFinal);

        for (OrderLines orderLines1 : orderLinesService.getNotApprovedLineByUserId(userId)) {
            int reservedStock = orderLines1.getProduct().getReservedStock();
            int lineQuantity = orderLines1.getBoughtQuantity();
            int newReservedStock = reservedStock - lineQuantity;
            Product product = orderLines1.getProduct();
            product.setReservedStock(newReservedStock);
            orderLines1.setOrderFinal(orderFinal);
            int prodStock = product.getProductStock();
            int newStock = prodStock - lineQuantity;
            product.setProductStock(newStock);
            productService.editProduct(product);
            orderLinesService.editOrderLine(orderLines1);
        }

        return "redirect:/cart";
    }



    @RequestMapping(value = "/cart/final/lines/{id}", method = RequestMethod.GET)
    public String showOrderLines(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("historyLines", orderFinalService.getOrderById(id));
        return "finalLines";
    }

}
