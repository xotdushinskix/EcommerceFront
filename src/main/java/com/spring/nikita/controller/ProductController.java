package com.spring.nikita.controller;

import com.spring.nikita.model.Product;
import com.spring.nikita.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

/**
 * Created by nikita on 01.09.16.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String allProducts(Model model) throws SQLException {
        model.addAttribute("allProducts", productService.getAllProducts());
        model.addAttribute("greeting", "Shalom");
        return "mainProducts";
    }



    @RequestMapping(value = "/main/add", method = RequestMethod.GET)
    public String addProductGet(@ModelAttribute Product product) {
        return "addProduct";
    }



    @RequestMapping(value = "/main/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product) throws SQLException {
        productService.addProduct(product);
        return "redirect:/main/add";
    }

}
