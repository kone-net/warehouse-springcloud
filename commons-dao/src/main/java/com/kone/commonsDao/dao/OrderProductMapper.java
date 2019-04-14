package com.kone.commonsDao.dao;

import com.kone.utils.bo.ProductByDayBO;
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


    /**
     * 通过时间段查看产品的出库情况
     *   显示该时间段，该产品总共使用的数量
     * @param condition
     * @return
     */
    List<ProductByDayBO> viewProductOutByDay(CommonCondition condition);

    /**
     * 通过时间段查看产品的出库情况 总条数
     * @param condition
     * @return
     */
    Long getProductOutByDaySum(CommonCondition condition);
}