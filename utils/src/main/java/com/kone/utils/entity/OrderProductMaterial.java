package com.kone.utils.entity;

import java.util.Date;

public class OrderProductMaterial {
    private Long orderProductMaterialId;

    private Long orderProductId;

    private String materialName;

    private String materialUnit;

    private Float materialUnitPrice;

    private Float materialNum;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    public Long getOrderProductMaterialId() {
        return orderProductMaterialId;
    }

    public void setOrderProductMaterialId(Long orderProductMaterialId) {
        this.orderProductMaterialId = orderProductMaterialId;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit == null ? null : materialUnit.trim();
    }

    public Float getMaterialUnitPrice() {
        return materialUnitPrice;
    }

    public void setMaterialUnitPrice(Float materialUnitPrice) {
        this.materialUnitPrice = materialUnitPrice;
    }

    public Float getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Float materialNum) {
        this.materialNum = materialNum;
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