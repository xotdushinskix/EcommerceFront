package com.spring.nikita.controller;

import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.Product;
import com.spring.nikita.model.User;
import com.spring.nikita.service.OrderLinesService;
import com.spring.nikita.service.ProductService;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


/**
 * Created by nikita on 08.09.16.
 */
@Controller
public class CartController extends GetUserName{

    @Autowired
    private OrderLinesService orderLinesService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(@ModelAttribute OrderLines orderLines, Model model) throws SQLException {
        String login = getPrincipal();

        User user = userService.getUserByLogin(login);

        model.addAttribute("allLines", orderLinesService.getNotApprovedLineByUserId(user.getId()));
        model.addAttribute("testS", orderLines.getProduct());
        System.out.println(orderLines.getProduct());
        return "cart";
    }



    @RequestMapping(value = "/cart/edit/{orderLineId}", method = RequestMethod.GET)
    public String editCartGet(@PathVariable("orderLineId")int orderLineId, Model model) throws SQLException {
        model.addAttribute("orderLine", orderLinesService.getOrderLine(orderLineId));
        return "editCart";
    }



    @RequestMapping(value = "/cart/edit/{orderLineId}", method = RequestMethod.POST)
    public String editCartPost(@RequestParam("newPurchQuantity") int newPurchQuantity, @ModelAttribute OrderLines orderLines, Model model) throws SQLException {
        System.out.println("lufaaaa");
        System.out.println(newPurchQuantity);
        System.out.println("lufaaaa");


//        orderLines = orderLinesService.getOrderLine(orderLineId);
        int orderLineId = orderLines.getOrderLineId();
        System.out.println(orderLineId);
        System.out.println(orderLinesService.getOrderLine(orderLineId).getProduct().getProductBrand());
//        //product = orderLines.getProduct();
//        int prodStock = product.getProductStock();
//        int startBoughtQuantity = orderLines.getBoughtQuantity();
//        System.out.println(startBoughtQuantity);
        //int productStock = orderLines.getProduct().getProductStock();

//        return "redirect:/cart";

        String returnString = null;

        orderLines = orderLinesService.getOrderLine(orderLineId);
        System.out.println();
        System.out.println(orderLines.getProduct().getProductModel());
        System.out.println();
        return "redirect:/cart";

//        if (newPurchQuantity > orderLines.getBoughtQuantity()) {
//            String moreThanStock = "Purchase quantity can not be more than in stock";
//            model.addAttribute("moreThanStock", moreThanStock);
//            returnString = "redirect:/cart";
//        }
//
//        if (orderLines.getBoughtQuantity() < newPurchQuantity) {
//            System.out.println();
//            System.out.println("<<<<<<<<<<");
//            System.out.println();
//            int newStock = productService.getProduct() - (newPurchQuantity - startBoughtQuantity);
//            product.setProductStock(newStock);
//            productService.editProduct(product);
//            orderLine.setBoughtQuantity(newPurchQuantity);
//            orderLinesService.editOrderLine(orderLine);
//            returnString = "redirect:/cart";
//        } else if (startBoughtQuantity > newPurchQuantity) {
//            System.out.println();
//            System.out.println(">>>>>>>>>>");
//            System.out.println();
//            int newStock = prodStock + (startBoughtQuantity - newPurchQuantity);
//            product.setProductStock(newStock);
//            productService.editProduct(product);
//            orderLine.setBoughtQuantity(newPurchQuantity);
//            orderLinesService.editOrderLine(orderLine);
//            returnString = "redirect:/cart";
//        }
//
//        return returnString;
    }

}
