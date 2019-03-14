package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Product;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByPager(Pager pager);

    Product selectByProductName(String productName);

    Long countByPager(CommonCondition condition);

    List<Product> viewAllProduct();
}