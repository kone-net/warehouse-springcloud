package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductMaterial;

import java.util.List;

public interface ProductMaterialMapper {
    int deleteByPrimaryKey(Long productMaterialId);

    int insert(ProductMaterial record);

    int insertSelective(ProductMaterial record);

    ProductMaterial selectByPrimaryKey(Long productMaterialId);

    int updateByPrimaryKeySelective(ProductMaterial record);

    int updateByPrimaryKey(ProductMaterial record);

    Long countByPager(CommonCondition condition);

    List<ProductMaterial> selectByPager(CommonCondition condition);

    Float getSum(CommonCondition condition);
}