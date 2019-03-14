package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductSeriesName;

import java.util.List;

public interface ProductSeriesNameMapper {
    int deleteByPrimaryKey(Long productSeriesNameId);

    int insert(ProductSeriesName record);

    int insertSelective(ProductSeriesName record);

    ProductSeriesName selectByPrimaryKey(Long productSeriesNameId);

    int updateByPrimaryKeySelective(ProductSeriesName record);

    int updateByPrimaryKey(ProductSeriesName record);

    List<ProductSeriesName> selectByPager(CommonCondition condition);

    Long countByPager(CommonCondition condition);

    ProductSeriesName selectByName(String name);

    List<ProductSeriesName> selectAllProductSeriesName();
}