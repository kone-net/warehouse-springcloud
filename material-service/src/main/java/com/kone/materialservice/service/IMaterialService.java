package com.kone.materialservice.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.MaterialIn;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface IMaterialService {

    ResponseMsg<List<Material>> viewMaterial(CommonCondition condition);

    ResponseMsg saveMaterial(Material material);

    ResponseMsg<List<Material>> viewAllMaterial();

    /**
     * 材料入库
     * @param materialIn
     * @return
     */
    ResponseMsg saveMaterialIn(MaterialIn materialIn);

    /**
     * 查看材料入库记录
     * @param condition
     * @return
     */
    ResponseMsg<List<MaterialIn>> viewMaterialIn(CommonCondition condition);

    /**
     * 删除材料，改变yn的值
     * @param materialId
     * @return
     */
    ResponseMsg deleteMaterial(Long materialId);
}
