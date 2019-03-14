package com.kone.orderservice.service.impl;

import com.kone.commonsDao.dao.*;
import com.kone.orderservice.service.IOrderService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.OrderProductBatchDTO;
import com.kone.utils.entity.*;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import com.kone.utils.pager.PagerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orderService")
public class OrderService implements IOrderService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductMaterialNumMapper productMaterialNumMapper;

    @Resource
    private OrderProductMaterialMapper orderProductMaterialMapper;

    @Override
    @RequestMapping("/saveOrder")
    public ResponseMsg saveOrder(@RequestBody Order order) {
        ResponseMsg<Order> msg = new ResponseMsg<>();
        if(null != order && !StringUtils.isEmpty(order.getBuyerName())) {
            int re = orderMapper.insert(order);
            if(1 != re) {
                msg.setMsg(MsgEnum.SAVE_ERROR.getMsg());
                msg.setCode(MsgEnum.SAVE_ERROR.getCode());
                return msg;
            }

        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/viewOrder")
    public ResponseMsg<List<Order>> viewOrder(@RequestBody CommonCondition condition) {
        logger.info("view order");
        ResponseMsg<List<Order>> msg = new ResponseMsg<>();

        Pager pager = condition.getPager();
        Long total = orderMapper.countByPager(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart(pager.getNum() * pager.getSize());
        List<Order> orders = orderMapper.selectByPager(condition);

        msg.setData(orders);

        msg.setPager(pager);
        return msg;
    }

    @Override
    @RequestMapping("/viewOrderDetails")
    public ResponseMsg<Order> viewOrderDetails(@RequestBody CommonCondition condition) {
        logger.info("view order details");
        ResponseMsg<Order> msg = new ResponseMsg<>();

        Order order = orderMapper.selectByOrderId(condition.getId());
        Float total = 0F;
//        计算价格
        for(OrderProduct orderProduct : order.getProducts()) {
            Long productNum = orderProduct.getProductNum();
            Float t = 0F;
            for(OrderProductMaterial orderProductMaterial : orderProduct.getOrderProductMaterials()) {
                Float materialUnitPrice = orderProductMaterial.getMaterialUnitPrice();
                Float materialNum = orderProductMaterial.getMaterialNum();
                t += materialUnitPrice * materialNum;
            }
            Float partMoney = t * productNum;

            orderProduct.setMoney(partMoney);

            total += partMoney;
        }

        order.setMoney(total);

        msg.setData(order);

        return msg;
    }

    @Transactional
    @Override
    @RequestMapping("/saveOrderDetails")
    public ResponseMsg saveOrderDetails(@RequestBody OrderProduct orderProduct) {
        ResponseMsg msg = new ResponseMsg();
        if(null != orderProduct && 0 != orderProduct.getOrderId() && 0 != orderProduct.getProductId() && orderProduct.getProductNum() >= 0) {

            List<OrderProduct> orderProducts = orderProductMapper.selectByProductId(orderProduct.getProductId(), orderProduct.getOrderId());
            if(orderProducts.size() > 0) {
                msg.setMsg(MsgEnum.ALREADY_EXIST.getMsg());
                msg.setCode(MsgEnum.ALREADY_EXIST.getCode());
                return msg;
            }

//            产品数量减少
            Product product = productMapper.selectByPrimaryKey(orderProduct.getProductId());
            if(null != product) {
                product.setProductNum(product.getProductNum() - orderProduct.getProductNum());
                product.setGmtUpdate(new Date());
                productMapper.updateByPrimaryKey(product);
            }
//            保存订单详情
            orderProductMapper.insert(orderProduct);

            Long orderProductId = orderProduct.getOrderProductId();
//            通过产品查找需要的材料信息
            List<ProductMaterialNum> productMaterialNums = productMaterialNumMapper.selectByProductId(orderProduct.getProductId());
            for(ProductMaterialNum productMaterialNum : productMaterialNums) {
                OrderProductMaterial orderProductMaterial = new OrderProductMaterial();
                orderProductMaterial.setOrderProductId(orderProductId);
                orderProductMaterial.setMaterialName(productMaterialNum.getMaterial().getMaterialName());
                orderProductMaterial.setMaterialUnit(productMaterialNum.getMaterial().getMaterialUnit());
                orderProductMaterial.setMaterialUnitPrice(productMaterialNum.getMaterial().getMaterialUnitPrice());
//                一个产品需要使用的材料数量
                orderProductMaterial.setMaterialNum(productMaterialNum.getMaterialNum());
                orderProductMaterialMapper.insert(orderProductMaterial);
            }

        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/saveBatchOrderDetails")
    @Transactional
    public ResponseMsg saveBatchOrderDetails(@RequestBody OrderProductBatchDTO dto) {
        for(OrderProduct orderProduct : dto.getOrderProducts()) {
            synchronized (dto.getOrderId()) {
                if(orderProduct.getProductNum() > 0) {
                    orderProduct.setOrderId(dto.getOrderId());
                    saveOrderDetails(orderProduct);
                }
            }
        }
        return new ResponseMsg();
    }

    @Override
    @RequestMapping("/deleteOrderProduct")
    @Transactional
    public ResponseMsg deleteOrderProduct(Long orderProductId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != orderProductId && 0 != orderProductId) {
//            删除订单产品
            OrderProduct orderProduct = orderProductMapper.selectByPrimaryKey(orderProductId);
            if(null == orderProduct) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            orderProduct.setYn(-1);
            orderProductMapper.updateByPrimaryKey(orderProduct);

//            将订单产品数量还原到仓库
            if(orderProduct.getProductNum() > 0) {
                Product product = productMapper.selectByPrimaryKey(orderProduct.getProductId());
                if(null != product) {
                    product.setProductNum(product.getProductNum() + orderProduct.getProductNum());
                    productMapper.updateByPrimaryKey(product);
                }
            }

        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }
}
