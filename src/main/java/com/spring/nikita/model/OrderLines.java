package com.spring.nikita.model;

import javax.persistence.*;

/**
 * Created by nikita on 31.08.16.
 */
@Entity
@Table(name = "order_lines")
public class OrderLines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private int orderLineId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "bought_quantity")
    private int boughtQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderFinal orderFinal;

    public OrderLines() {

    }


    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }

    public OrderFinal getOrderFinal() {
        return orderFinal;
    }

    public void setOrderFinal(OrderFinal orderFinal) {
        this.orderFinal = orderFinal;
    }
}
