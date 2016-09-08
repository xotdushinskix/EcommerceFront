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
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by nikita on 08.09.16.
 */
@Controller
public class AddToCartController extends GetUserName {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderLinesService orderLinesService;

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/main/add/{productId}", method = RequestMethod.GET)
    public String getCartPage(@PathVariable("productId") int productId, @ModelAttribute Product product,
                              Model model) throws SQLException {
        product = productService.getProduct(productId);
        model.addAttribute("productForSale", product);
        return "addProductToCart";
    }



    @RequestMapping(value = "/main/add/{productId}", method = RequestMethod.POST)
    public String postCartPage(@PathVariable("productId") int productId, @RequestParam("purchQuantity") int purchQuantity,
                               @ModelAttribute Product product, @ModelAttribute User user) throws SQLException {

        user = userService.getUserByLogin(super.getPrincipal());
        product = productService.getProduct(productId);

        int stock = product.getProductStock();
        int stockAfterPurchase = stock - purchQuantity;
        product.setProductStock(stockAfterPurchase);

        OrderLines orderLines = new OrderLines();
        orderLines.setBoughtQuantity(purchQuantity);
        orderLines.setProduct(product);
        orderLines.setUser(user);

        user.getOrderLines().add(orderLines);
        product.getOrderLines().add(orderLines);

        userService.editUser(user);
        productService.editProduct(product);

        return "redirect:/main";
    }

}
