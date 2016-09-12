package com.spring.nikita.model;

//import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 31.08.16.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    //@NotEmpty(message = "Please, enter product brand")
    @Column(name = "product_brand")
    private String productBrand;

    //@NotEmpty(message = "Please, enter product model")
    @Column(name = "product_model")
    private String productModel;

    @Column(name = "stock")
    private int productStock;

    //@NotEmpty(message = "Please, enter mpn")
    @Column(name = "product_mpn", unique = true)
    private int productMPN;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderLines> orderLines = new ArrayList<OrderLines>();

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getProductMPN() {
        return productMPN;
    }

    public void setProductMPN(int productMPN) {
        this.productMPN = productMPN;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLines) {
        this.orderLines = orderLines;
    }
}
