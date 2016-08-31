package com.spring.nikita.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 31.08.16.
 */
@Entity
@Table(name = "order_final")
public class OrderFinal {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "created")
    private String createdData;

    @Column(name = "ship")
    private Boolean shipStatus;

    @OneToMany(mappedBy = "orderFinal")
    private List<OrderLines> orderLines = new ArrayList<OrderLines>();

    public OrderFinal() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreatedData() {
        return createdData;
    }

    public void setCreatedData(String createdData) {
        this.createdData = createdData;
    }

    public Boolean getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Boolean shipStatus) {
        this.shipStatus = shipStatus;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLines) {
        this.orderLines = orderLines;
    }
}
