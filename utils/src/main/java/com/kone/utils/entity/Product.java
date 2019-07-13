package com.kone.utils.entity;

import java.util.Date;

public class Product {
    private Long productId;

    private String productName;

    private String productModel;

    private String productMouldModel;

    private Long productNum;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel == null ? null : productModel.trim();
    }

    public String getProductMouldModel() {
        return productMouldModel;
    }

    public void setProductMouldModel(String productMouldModel) {
        this.productMouldModel = productMouldModel == null ? null : productMouldModel.trim();
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productMouldModel='" + productMouldModel + '\'' +
                ", productNum=" + productNum +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                ", yn=" + yn +
                '}';
    }
}