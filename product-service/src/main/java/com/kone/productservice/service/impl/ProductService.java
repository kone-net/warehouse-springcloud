package com.kone.productservice.service.impl;

import com.kone.commonsDao.dao.OrderProductMapper;
import com.kone.commonsDao.dao.ProductMapper;
import com.kone.productservice.service.IProductService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dateUtils.DateUtil;
import com.kone.utils.dto.ProductStatisticsDTO;
import com.kone.utils.entity.OrderProduct;
import com.kone.utils.entity.Product;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/productService")
public class ProductService implements IProductService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @RequestMapping("/viewProduct")
    @Override
    public ResponseMsg<List<Product>> viewProduct(@RequestBody Pager pager) {
        logger.info("view product");
        ResponseMsg<List<Product>> msg = new ResponseMsg<List<Product>>();

        Long total = productMapper.countByPager(new CommonCondition());
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart(pager.getNum() * pager.getSize());
        List<Product> products = productMapper.selectByPager(pager);

        msg.setData(products);

        msg.setPager(pager);
        return msg;
    }

    @Override
    @RequestMapping("/saveProduct")
    public ResponseMsg saveProduct(@RequestBody Product product) {
        ResponseMsg<Product> msg = new ResponseMsg<>();
        if(null != product && !StringUtils.isEmpty(product.getProductName())) {
            Product product1 = productMapper.selectByProductName(product.getProductName());
            if(null == product1) {
                int re = productMapper.insert(product);
                if(1 != re) {
                    msg.setMsg(MsgEnum.SAVE_ERROR.getMsg());
                    msg.setCode(MsgEnum.SAVE_ERROR.getCode());
                    return msg;
                }
            } else {
                msg.setMsg(MsgEnum.ALREADY_EXIST.getMsg());
                msg.setCode(MsgEnum.ALREADY_EXIST.getCode());
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
    @RequestMapping("/viewAllProduct")
    public ResponseMsg<List<Product>> viewAllProduct() {
        ResponseMsg<List<Product>> msg = new ResponseMsg<List<Product>>();
        List<Product> products = productMapper.viewAllProduct();
        msg.setData(products);
        return msg;
    }

    @Override
    @RequestMapping("/deleteProduct")
    public ResponseMsg deleteProduct(Long productId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != productId && 0 != productId) {
            Product product = productMapper.selectByPrimaryKey(productId);
            if(null == product) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            product.setYn(-1);
            productMapper.updateByPrimaryKey(product);
        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @Transactional
    @RequestMapping("/productOutbound")
    public ResponseMsg productOutbound(@RequestBody ProductStatisticsDTO dto) {
        ResponseMsg msg = new ResponseMsg();
        if(null == dto || null == dto.getProductId() || 0 == dto.getProductId()) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }

        Product product = productMapper.selectByPrimaryKey(dto.getProductId());
        if(null == product) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
//        reduce the product num
        logger.info("reduce product num is " + dto.getNum() + " and product id is " + product.getProductId());
        product.setProductNum(product.getProductNum() - dto.getNum());
        product.setGmtUpdate(new Date());
        productMapper.updateByPrimaryKey(product);

//        convert the date
        Date date = DateUtil.getDateByString(dto.getDate());

//        save the warehousing record
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(0L);
        orderProduct.setGmtCreate(date);
        orderProduct.setProductNum(dto.getNum());
        orderProduct.setProductId(dto.getProductId());
        int re = orderProductMapper.insert(orderProduct);
        if(1 != re) {
            msg.setMsg(MsgEnum.SAVE_ERROR.getMsg());
            msg.setCode(MsgEnum.SAVE_ERROR.getCode());
            return msg;
        }
        return msg;
    }
}
