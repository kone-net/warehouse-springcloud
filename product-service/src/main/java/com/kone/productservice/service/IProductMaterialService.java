package com.kone.productservice.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductMaterial;
import com.kone.utils.msg.ResponseMsg;

import java.util.List;

public interface IProductMaterialService {

    ResponseMsg saveProductMaterial(ProductMaterial productMaterial);

    ResponseMsg<List<ProductMaterial>> viewProductMaterialByProductId(CommonCondition condition);
}
