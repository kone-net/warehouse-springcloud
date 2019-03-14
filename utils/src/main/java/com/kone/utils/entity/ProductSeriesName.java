package com.kone.utils.entity;

import java.util.Date;

public class ProductSeriesName {
    private Long productSeriesNameId;

    private String productSeriesName;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    public Long getProductSeriesNameId() {
        return productSeriesNameId;
    }

    public void setProductSeriesNameId(Long productSeriesNameId) {
        this.productSeriesNameId = productSeriesNameId;
    }

    public String getProductSeriesName() {
        return productSeriesName;
    }

    public void setProductSeriesName(String productSeriesName) {
        this.productSeriesName = productSeriesName == null ? null : productSeriesName.trim();
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