package com.kone.utils.entity;

import java.util.Date;

public class ProductSeries {
    private Long productSeriesId;

    private Long productSeriesNameId;

    private Long productId;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    private Product product;

    public Long getProductSeriesId() {
        return productSeriesId;
    }

    public void setProductSeriesId(Long productSeriesId) {
        this.productSeriesId = productSeriesId;
    }

    public Long getProductSeriesNameId() {
        return productSeriesNameId;
    }

    public void setProductSeriesNameId(Long productSeriesNameId) {
        this.productSeriesNameId = productSeriesNameId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}