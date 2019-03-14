package com.kone.utils.dto;

import com.kone.utils.entity.OrderProduct;

import java.util.List;

public class OrderProductBatchDTO {
    private Long orderId;

    private List<OrderProduct> orderProducts;

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
