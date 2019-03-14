package com.kone.materialservice.service;

import com.kone.utils.entity.MaterialDetails;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface IMaterialDetailsService {

    ResponseMsg<List<MaterialDetails>> viewMaterialDetails(Long materialId, Pager pager);
}
