package com.kone.utils.entity;

import java.util.Date;

public class MaterialDetails {
    private Long materialDetailsId;

    private Long productId;

    private Long materialId;

    private Long productMaterialId;

    private Float materialUseNum;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Product product;

    private ProductMaterial productMaterial;

    public Long getMaterialDetailsId() {
        return materialDetailsId;
    }

    public void setMaterialDetailsId(Long materialDetailsId) {
        this.materialDetailsId = materialDetailsId;
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

    public Long getProductMaterialId() {
        return productMaterialId;
    }

    public void setProductMaterialId(Long productMaterialId) {
        this.productMaterialId = productMaterialId;
    }

    public Float getMaterialUseNum() {
        return materialUseNum;
    }

    public void setMaterialUseNum(Float materialUseNum) {
        this.materialUseNum = materialUseNum;
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

    public ProductMaterial getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(ProductMaterial productMaterial) {
        this.productMaterial = productMaterial;
    }
}