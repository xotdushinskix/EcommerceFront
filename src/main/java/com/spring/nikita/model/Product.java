package com.spring.nikita.model;

//import org.hibernate.validator.constraints.NotEmpty;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    //@NotBlank(message = "Please, enter product brand")
    @Column(name = "product_brand")
    private String productBrand;

    //@NotBlank(message = "Please, enter product model")
    @Column(name = "product_model")
    private String productModel;

    @Column(name = "stock")
    private int productStock;

    //@NotNull(message = "Please, enter mpn")
    @Column(name = "product_mpn", unique = true)
    private int productMPN;

    //@NotNull
    @Column(name = "reserved_stock")
    private int reservedStock;

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

    public int getReservedStock() {
        return reservedStock;
    }

    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }
}
