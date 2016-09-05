package com.spring.nikita.controller;

import com.spring.nikita.model.Product;
import com.spring.nikita.model.User;
import com.spring.nikita.service.ProductService;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * Created by nikita on 01.09.16.
 */
@Controller
public class ProductController extends GetUserName{

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String allProducts(Model model) throws SQLException {
        model.addAttribute("allProducts", productService.getAllProducts());
        model.addAttribute("greeting", "Shalom");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            model.addAttribute("userName", super.getUserName());
            model.addAttribute("userLogin", super.getPrincipal());
        }
        return "mainProducts";
    }



    @RequestMapping(value = "/main/add", method = RequestMethod.GET)
    public String addProductGet(@ModelAttribute Product product) {
        return "addProduct";
    }



    @RequestMapping(value = "/main/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product, BindingResult result) throws SQLException {
        if (result.hasErrors()) {
            return "addProduct";
        }
        productService.addProduct(product);
        return "redirect:/main";
    }


}
