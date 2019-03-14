package com.kone.utils.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProduct {
    private Long orderProductId;

    private Long orderId;

    private Long productId;

    private Long productNum = 0L;

    private String deliveryDate;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    private Product product;

    private Float money;

    private List<OrderProductMaterial> orderProductMaterials = new ArrayList<>();

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate == null ? null : deliveryDate.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderProductMaterial> getOrderProductMaterials() {
        return orderProductMaterials;
    }

    public void setOrderProductMaterials(List<OrderProductMaterial> orderProductMaterials) {
        this.orderProductMaterials = orderProductMaterials;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}