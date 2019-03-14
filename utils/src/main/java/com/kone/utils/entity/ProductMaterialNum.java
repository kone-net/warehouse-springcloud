package com.kone.utils.entity;

import java.util.Date;

public class ProductMaterialNum {
    private Long productMaterialNumId;

    private Long productId;

    private Long materialId;

    private Float materialNum;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    private Material material;

    public Long getProductMaterialNumId() {
        return productMaterialNumId;
    }

    public void setProductMaterialNumId(Long productMaterialNumId) {
        this.productMaterialNumId = productMaterialNumId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}