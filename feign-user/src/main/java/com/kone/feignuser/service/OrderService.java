package com.kone.feignuser.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.OrderProductBatchDTO;
import com.kone.utils.entity.Order;
import com.kone.utils.entity.OrderProduct;
import com.kone.utils.msg.ResponseMsg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderService {

    @RequestMapping(value = "/orderService/saveOrder", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveOrder(@RequestBody Order order);

    @RequestMapping(value = "/orderService/viewOrder", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<Order>> viewOrder(@RequestBody CommonCondition condition);

//    order product
    /**
     * 查看订单详情
     * @param condition
     * @return
     */
    @RequestMapping(value = "/orderService/viewOrderDetails", consumes = "application/json")
    @ResponseBody
    ResponseMsg<Order> viewOrderDetails(@RequestBody CommonCondition condition);

    /**
     * 保存订单详情
     * @param orderProduct
     * @return
     */
    @RequestMapping(value = "/orderService/saveOrderDetails", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveOrderDetails(@RequestBody OrderProduct orderProduct);


    /**
     * 批量保存订单产品
     * @param dto
     * @return
     */
    @RequestMapping(value = "/orderService/saveBatchOrderDetails", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveBatchOrderDetails(@RequestBody OrderProductBatchDTO dto);


    /**
     * 删除订单的对应产品，删除后，产品数量会回到仓库
     * @param orderProductId
     * @return
     */
    @RequestMapping(value = "/orderService/deleteOrderProduct", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteOrderProduct(@RequestParam("orderProductId") Long orderProductId);

}
