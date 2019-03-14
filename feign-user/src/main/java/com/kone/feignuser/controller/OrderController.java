package com.kone.feignuser.controller;

import com.kone.feignuser.service.OrderService;
import com.kone.feignuser.service.ProductService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.OrderProductBatchDTO;
import com.kone.utils.entity.*;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private OrderService orderService;


    /**
     * 保存订单
     * @param order
     * @return
     */
    @PostMapping("/saveOrder")
    @ResponseBody
    public ResponseMsg saveOrder(Order order) {
        ResponseMsg msg = orderService.saveOrder(order);
        return msg;
    }


    /**
     * 查看订单
     * @param condition
     * @return
     */
    @GetMapping("/viewOrder")
    public String viewOrder(CommonCondition condition, Model model) {
        ResponseMsg<List<Order>> msg = orderService.viewOrder(condition);

        model.addAttribute("data", msg);
        return "order/viewOrder";
    }


    /**
     * 查看订单详情
     * @param condition
     * @param model
     * @return
     */
    @GetMapping("/viewOrderDetails")
    public String viewOrderDetails(CommonCondition condition, Model model, Integer type) {
        ResponseMsg<Order> msg = orderService.viewOrderDetails(condition);

        model.addAttribute("data", msg);
        model.addAttribute("orderId", condition.getId());
        model.addAttribute("type", type);
        return "order/viewOrderDetails";
    }

    /**
     * 保存订单详情
     * @param orderProduct
     * @return
     */
    @PostMapping("/saveOrderDetails")
    @ResponseBody
    public ResponseMsg saveOrderDetails(OrderProduct orderProduct) {
        ResponseMsg msg = orderService.saveOrderDetails(orderProduct);
        return msg;
    }

    /**
     * 批量保存订单产品
     * @param dto
     * @return
     */
    @PostMapping("/saveBatchOrderDetails")
    @ResponseBody
    public ResponseMsg saveBatchOrderDetails(OrderProductBatchDTO dto) {
        ResponseMsg msg = orderService.saveBatchOrderDetails(dto);
        return msg;
    }


    /**
     * 删除订单的对应产品，删除后，产品数量会回到仓库
     * @param orderProductId
     * @return
     */
    @PostMapping("/deleteOrderProduct")
    @ResponseBody
    public ResponseMsg deleteOrderProduct(Long orderProductId) {
        ResponseMsg msg = orderService.deleteOrderProduct(orderProductId);
        return msg;
    }
}
