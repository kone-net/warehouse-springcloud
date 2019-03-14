package com.kone.commonsDao.dao;

import com.kone.utils.entity.OrderProductMaterial;

import java.util.List;

public interface OrderProductMaterialMapper {
    int deleteByPrimaryKey(Long orderProductMaterialId);

    int insert(OrderProductMaterial record);

    int insertSelective(OrderProductMaterial record);

    OrderProductMaterial selectByPrimaryKey(Long orderProductMaterialId);

    int updateByPrimaryKeySelective(OrderProductMaterial record);

    int updateByPrimaryKey(OrderProductMaterial record);

    List<OrderProductMaterial> selectByOrderProductId(Long orderProductId);
}