package com.kone.productservice.service;

import com.kone.utils.entity.ProductMaterialNum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface IProductMaterialNumService {

    ResponseMsg saveProductMaterialNum(ProductMaterialNum productMaterialNum);

    List<ProductMaterialNum> viewProductMaterialNumByProductId(ProductMaterialNum productMaterialNum);

    ResponseMsg deleteProductMaterialNum(Long productMaterialNumId);
}
