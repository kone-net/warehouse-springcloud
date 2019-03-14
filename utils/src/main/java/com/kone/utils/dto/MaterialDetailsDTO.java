package com.kone.utils.dto;

import com.kone.utils.pager.Pager;

public class MaterialDetailsDTO {
    private Pager pager;

    private Long materialId;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
