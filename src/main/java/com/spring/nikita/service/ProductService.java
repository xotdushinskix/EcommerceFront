package com.spring.nikita.service;

import com.spring.nikita.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 01.09.16.
 */
public interface ProductService {

    void addProduct(Product product) throws SQLException;
    void deleteProduct(Product product) throws SQLException;
    void editProduct(Product product) throws SQLException;
    Product getProduct(int productId) throws SQLException;
    List<Product> getAllProducts() throws SQLException;

}
