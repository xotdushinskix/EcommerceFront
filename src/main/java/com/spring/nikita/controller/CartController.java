package com.spring.nikita.controller;

import com.spring.nikita.model.OrderLines;
import com.spring.nikita.model.User;
import com.spring.nikita.service.OrderLinesService;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(@ModelAttribute OrderLines orderLines, Model model) throws SQLException {
        String login = getPrincipal();

        User user = userService.getUserByLogin(login);

        model.addAttribute("allLines", orderLinesService.getNotApprovedLineByUserId(user.getId()));
        model.addAttribute("testS", orderLines.getProduct());
        System.out.println(orderLines.getProduct());
        return "cart";
    }

}
