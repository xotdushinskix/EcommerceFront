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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getCartPage(@PathVariable("productId") int productId, @ModelAttribute OrderLines orderLines,
                              @ModelAttribute Product product, Model model) throws SQLException {

        product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "addProductToCart";
    }



    @RequestMapping(value = "/main/add/{productId}", method = RequestMethod.POST)
    public String postCartPage(@PathVariable("productId") int productId,
                               @ModelAttribute Product product, @ModelAttribute User user,
                               @Validated @ModelAttribute OrderLines orderLines,
                               @RequestParam("boughtQuantity") int boughtQuantity, BindingResult result, Model model,
                               RedirectAttributes attributes) throws SQLException {

        String returnString = null;

        if (result.hasErrors()) {
            return "addProductToCart";

        } else {

            user = userService.getUserByLogin(super.getPrincipal());
            product = productService.getProduct(productId);

            try {

                orderLines = orderLinesService.getOrderLineBySerNameAndProduct(user, product);

                int productStock = product.getProductStock();
                int availableBoughtQuantity = orderLines.getBoughtQuantity();
                int availableStock = productStock - availableBoughtQuantity;

                if (boughtQuantity > availableStock) {
                    String moreThanInStock = "You can not add more products than available in free stock";
                    attributes.addFlashAttribute("moreThanInStock", moreThanInStock);
                    returnString =  "redirect:/main";

                } else {

                    int newBoughtQuantity = availableBoughtQuantity + boughtQuantity;

                    orderLines.setBoughtQuantity(newBoughtQuantity);
                    orderLinesService.editOrderLine(orderLines);

                    int reservedQuantity = product.getReservedStock();
                    product.setReservedStock(boughtQuantity + reservedQuantity);
                    productService.editProduct(product);
                }

            } catch (Exception e) {

                orderLines = new OrderLines();
                orderLines.setUser(user);
                orderLines.setProduct(product);
                orderLines.setBoughtQuantity(boughtQuantity);
                orderLinesService.editOrderLine(orderLines);

                user.getOrderLines().add(orderLines);
                product.getOrderLines().add(orderLines);

                userService.editUser(user);
                int reservedQuantity = product.getReservedStock();

                product.setReservedStock(boughtQuantity + reservedQuantity);
                productService.editProduct(product);
                returnString = "redirect:/main";
            }
        }

        return returnString;
    }

}
