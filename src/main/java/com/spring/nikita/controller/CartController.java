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
public class CartController extends GetUserName {

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
        model.addAttribute("orderHistory", orderLinesService.getAllUserLineByRequiredUser(user));
        return "cart";
    }



    @RequestMapping(value = "/cart/edit/{order_Line_Id}", method = RequestMethod.GET)
    public String editCartGet(@PathVariable("order_Line_Id") int order_LineId, Model model,
                              @ModelAttribute OrderLines orderLines) throws SQLException {
        model.addAttribute("orderLine", orderLinesService.getOrderLine(order_LineId));
        return "editCart";
    }



    @RequestMapping(value = "/cart/edit/{order_Line_Id}", method = RequestMethod.POST)
    public String editCartPost(@RequestParam("boughtQuantity") int boughtQuantity, @ModelAttribute OrderLines orderLines,
                               Model model, @ModelAttribute Product product, RedirectAttributes attributes) throws SQLException {

        int order_LineId = orderLines.getOrderLineId();
        int productStock = orderLinesService.getOrderLine(order_LineId).getProduct().getProductStock();
        int productReservedStock = orderLinesService.getOrderLine(order_LineId).getProduct().getReservedStock();
        int availableStock = productStock - productReservedStock;
        int startBoughtQuantity = orderLinesService.getOrderLine(order_LineId).getBoughtQuantity();
        product = orderLinesService.getOrderLine(order_LineId).getProduct();
        orderLines = orderLinesService.getOrderLine(order_LineId);


        //try {
            if (boughtQuantity > availableStock) {
                String moreThanStock = "Purchase quantity can not be more than in available stock";
                attributes.addFlashAttribute("moreThanStock", moreThanStock);


            } else if (startBoughtQuantity < boughtQuantity) {

                int newReservedStock = productReservedStock + (boughtQuantity - startBoughtQuantity);
                product.setReservedStock(newReservedStock);
                productService.editProduct(product);

                orderLines.setBoughtQuantity(boughtQuantity);
                orderLinesService.editOrderLine(orderLines);


            } else if (startBoughtQuantity > boughtQuantity) {

                int newReservedStock1 = productReservedStock - (startBoughtQuantity - boughtQuantity);
                product.setReservedStock(newReservedStock1);
                productService.editProduct(product);

                orderLines.setBoughtQuantity(boughtQuantity);
                orderLinesService.editOrderLine(orderLines);
            }

//        } catch (Exception e) {
//            String nullValue = "Purchase quantity can not equals null";
//            attributes.addFlashAttribute("nullValue", nullValue);
        //}
        return "redirect:/cart";
    }



    @RequestMapping(value = "/main/add/{productId}", method = RequestMethod.GET)
    public String getCartPage(@PathVariable("productId") int productId, @ModelAttribute OrderLines orderLines,
                              @ModelAttribute Product product, Model model) throws SQLException {

        product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "addProductToCart";
    }



    public void newOrder(User user, Product product, int boughtQuantity) throws SQLException {
        OrderLines orderLines = new OrderLines();
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

                if (orderLines.getOrderFinal().getOrderId() == 0) {

                    if (boughtQuantity > availableStock) {
                        String moreThanInStock = "You can not add more products than available in free stock";
                        attributes.addFlashAttribute("moreThanInStock", moreThanInStock);
                        returnString = "redirect:/main";

                    } else {

                        int newBoughtQuantity = availableBoughtQuantity + boughtQuantity;

                        orderLines.setBoughtQuantity(newBoughtQuantity);
                        orderLinesService.editOrderLine(orderLines);

                        int reservedQuantity = product.getReservedStock();
                        product.setReservedStock(boughtQuantity + reservedQuantity);
                        productService.editProduct(product);
                        returnString = "redirect:/main";
                    }

                } else {
                    newOrder(user, product, boughtQuantity);
                    returnString = "redirect:/main";
                }

            } catch (Exception e) {
                newOrder(user, product, boughtQuantity);
                returnString = "redirect:/main";
            }
        }

        return returnString;
    }



    @RequestMapping(value = "/cart/delete/{orderLineId}", method = RequestMethod.GET)
    public String deleteOrderLine(Model model, @PathVariable("orderLineId") int orderLineId) throws SQLException {
        model.addAttribute("orderLine", orderLinesService.getOrderLine(orderLineId));
        return "deleteOrderLine";
    }



    @RequestMapping(value = "/cart/delete/{orderLineId}", method = RequestMethod.POST)
    public String deleteOrderLinePost(@ModelAttribute OrderLines orderLines,
                                      @PathVariable("orderLineId") int orderLineId, @ModelAttribute Product product) throws SQLException {
        orderLines = orderLinesService.getOrderLine(orderLineId);
        product = orderLines.getProduct();
        int reservedStock = product.getReservedStock();
        int lineQuantity = orderLines.getBoughtQuantity();
        int newReservedStock = reservedStock - lineQuantity;
        product.setReservedStock(newReservedStock);
        productService.editProduct(product);
        orderLinesService.deleteOrderLine(orderLines);
        return "redirect:/cart";
    }

}
