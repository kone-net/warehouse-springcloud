package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductSeries;

import java.util.List;

public interface ProductSeriesMapper {
    int deleteByPrimaryKey(Long productSeriesId);

    int insert(ProductSeries record);

    int insertSelective(ProductSeries record);

    ProductSeries selectByPrimaryKey(Long productSeriesId);

    int updateByPrimaryKeySelective(ProductSeries record);

    int updateByPrimaryKey(ProductSeries record);

    List<ProductSeries>  selectBySeriesNameId(CommonCondition condition);

    Long countByPager(CommonCondition condition);

    List<ProductSeries> selectByProductAndSeriesId(Long seriesNameId, Long productId);

}