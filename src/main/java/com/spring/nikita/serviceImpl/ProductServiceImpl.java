package com.spring.nikita.serviceImpl;

import com.spring.nikita.dao.ProductDao;
import com.spring.nikita.model.Product;
import com.spring.nikita.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 01.09.16.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public void addProduct(Product product) throws SQLException {
        productDao.addProduct(product);
    }

    public void deleteProduct(Product product) throws SQLException {
        productDao.deleteProduct(product);
    }

    public void editProduct(Product product) throws SQLException {
        productDao.editProduct(product);
    }

    public Product getProduct(int productId) throws SQLException {
        return productDao.getProduct(productId);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDao.getAllProducts();
    }
}
