package com.kone.utils.bo;

import com.kone.utils.entity.Material;

public class MaterialInByDayBO {
    private Long materialId;

    private Float materialNumSum;

    private Material material;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Float getMaterialNumSum() {
        return materialNumSum;
    }

    public void setMaterialNumSum(Float materialNumSum) {
        this.materialNumSum = materialNumSum;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
