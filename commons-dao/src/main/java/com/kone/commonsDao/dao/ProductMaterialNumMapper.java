package com.kone.commonsDao.dao;

import com.kone.utils.entity.ProductMaterialNum;

import java.util.List;

public interface ProductMaterialNumMapper {
    int deleteByPrimaryKey(Long productMaterialNumId);

    int insert(ProductMaterialNum record);

    int insertSelective(ProductMaterialNum record);

    ProductMaterialNum selectByPrimaryKey(Long productMaterialNumId);

    int updateByPrimaryKeySelective(ProductMaterialNum record);

    int updateByPrimaryKey(ProductMaterialNum record);

    List<ProductMaterialNum> selectByProductId(Long productId);
}