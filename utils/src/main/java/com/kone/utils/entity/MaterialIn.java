package com.kone.utils.entity;

import java.util.Date;

public class MaterialIn {
    private Long materialInId;

    private Long materialId;

    private String materialName;

    private Float materialInNum;

    private String inUnit;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    private String date;  //temp

    public Long getMaterialInId() {
        return materialInId;
    }

    public void setMaterialInId(Long materialInId) {
        this.materialInId = materialInId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public Float getMaterialInNum() {
        return materialInNum;
    }

    public void setMaterialInNum(Float materialInNum) {
        this.materialInNum = materialInNum;
    }

    public String getInUnit() {
        return inUnit;
    }

    public void setInUnit(String inUnit) {
        this.inUnit = inUnit == null ? null : inUnit.trim();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MaterialIn{" +
                "materialInId=" + materialInId +
                ", materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", materialInNum=" + materialInNum +
                ", inUnit='" + inUnit + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                ", yn=" + yn +
                '}';
    }
}