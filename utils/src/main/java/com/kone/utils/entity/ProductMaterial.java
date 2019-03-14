package com.kone.utils.entity;

import java.util.Date;

public class ProductMaterial {
    private Long productMaterialId;

    private Long productId;

    private Long productNum;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    public Long getProductMaterialId() {
        return productMaterialId;
    }

    public void setProductMaterialId(Long productMaterialId) {
        this.productMaterialId = productMaterialId;
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
}