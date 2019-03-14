package com.kone.orderservice.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.OrderProductBatchDTO;
import com.kone.utils.entity.Order;
import com.kone.utils.entity.OrderProduct;
import com.kone.utils.msg.ResponseMsg;

import java.util.List;

public interface IOrderService {

    /**
     * 保存订单基本信息
     * @param order
     * @return
     */
    ResponseMsg saveOrder(Order order);

    /**
     * 通过条件查看订单信息
     * @param condition
     * @return
     */
    ResponseMsg<List<Order>> viewOrder(CommonCondition condition);

    /**
     * 查看订单详情
     * @param condition
     * @return
     */
    ResponseMsg<Order> viewOrderDetails(CommonCondition condition);

    /**
     * 保存订单详情
     * @param orderProduct
     * @return
     */
    ResponseMsg saveOrderDetails(OrderProduct orderProduct);

    /**
     * 批量保存订单产品
     * @param dto
     * @return
     */
    ResponseMsg saveBatchOrderDetails(OrderProductBatchDTO dto);

    /**
     * 删除订单的对应产品，删除后，产品数量会回到仓库
     * @param orderProductId
     * @return
     */
    ResponseMsg deleteOrderProduct(Long orderProductId);
}
