package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.OrderProduct;

import java.util.List;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Long orderProductId);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Long orderProductId);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);

    List<OrderProduct> selectByOrderId(Long orderId);

    Long countByPager(CommonCondition condition);

    List<OrderProduct> selectByProductId(Long productId, Long orderId);


}