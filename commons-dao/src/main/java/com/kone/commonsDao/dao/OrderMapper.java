package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Long countByPager(CommonCondition condition);

    List<Order> selectByPager(CommonCondition condition);

    Order selectByOrderId(Long orderId);
}