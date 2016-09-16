package com.spring.nikita.controller;

import com.spring.nikita.help_model.HelpCartEditor;
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
        model.addAttribute("testS", orderLines.getProduct());
        return "cart";
    }


    @RequestMapping(value = "/cart/edit/{order_Line_Id}", method = RequestMethod.GET)
    public String editCartGet(@PathVariable("order_Line_Id") int order_LineId, Model model,
                              @ModelAttribute OrderLines orderLines) throws SQLException {
        model.addAttribute("orderLine", orderLinesService.getOrderLine(order_LineId));
        return "editCart";
    }


//    @RequestMapping(value = "/cart/edit/{order_Line_Id}", method = RequestMethod.POST)
//    public String editCartPost(@ModelAttribute OrderLines orderLines,
//                               Model model, @ModelAttribute Product product, RedirectAttributes attributes) throws SQLException {
//
//        System.out.println(orderLines.getBoughtQuantity());
//
//        return "redirect:/cart";
//    }


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


        try {
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

        } catch (Exception e) {
            System.out.println("nulllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
            String nullValue = "Purchase quantity can not equals null";
            attributes.addFlashAttribute("nullValue", nullValue);
        }
        return "redirect:/cart";
    }

}
