package com.kone.productservice.service.impl;

import com.kone.commonsDao.dao.ProductMapper;
import com.kone.productservice.service.IProductService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Product;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import com.kone.utils.pager.PagerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/productService")
public class ProductService implements IProductService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ProductMapper productMapper;

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
}
