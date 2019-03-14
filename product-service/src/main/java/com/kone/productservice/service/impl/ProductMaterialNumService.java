package com.kone.productservice.service.impl;

import com.kone.productservice.service.IProductMaterialNumService;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.Product;
import com.kone.utils.entity.ProductMaterialNum;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/productMaterialNumService")
public class ProductMaterialNumService implements IProductMaterialNumService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private com.kone.commonsDao.dao.ProductMapper productMapper;

    @Resource
    private com.kone.commonsDao.dao.MaterialMapper materialMapper;

    @Resource
    private com.kone.commonsDao.dao.ProductMaterialNumMapper productMaterialNumMapper;

    @Override
    @RequestMapping("/saveProductMaterialNum")
    public ResponseMsg saveProductMaterialNum(@RequestBody ProductMaterialNum productMaterialNum) {
        ResponseMsg<ProductMaterialNum> msg = new ResponseMsg<>();
        if(null != productMaterialNum && 0 != productMaterialNum.getMaterialId() && 0 != productMaterialNum.getProductId()) {
            Product product = productMapper.selectByPrimaryKey(productMaterialNum.getProductId());
            if(null == product) {
                logger.info("product id is not right");
                msg.setMsg(MsgEnum.NULL_INFO.getMsg());
                msg.setCode(MsgEnum.NULL_INFO.getCode());
                return msg;
            }
            Material material = materialMapper.selectByPrimaryKey(productMaterialNum.getMaterialId());
            if(null == material) {
                logger.info("material id is not right");
                msg.setMsg(MsgEnum.NULL_INFO.getMsg());
                msg.setCode(MsgEnum.NULL_INFO.getCode());
                return msg;
            }

            int re = productMaterialNumMapper.insert(productMaterialNum);
            if(1 != re) {
                logger.info("save product and materail num is error!");
                msg.setMsg(MsgEnum.SAVE_ERROR.getMsg());
                msg.setCode(MsgEnum.SAVE_ERROR.getCode());
                return msg;
            }
        } else {
            logger.info("product or material id is null");
            msg.setMsg(MsgEnum.NULL_INFO.getMsg());
            msg.setCode(MsgEnum.NULL_INFO.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/viewProductMaterialNumByProductId")
    public List<ProductMaterialNum> viewProductMaterialNumByProductId(@RequestBody ProductMaterialNum productMaterialNum) {
        List<ProductMaterialNum> productMaterialNums = productMaterialNumMapper.selectByProductId(productMaterialNum.getProductId());

        return productMaterialNums;
    }

    @Override
    @RequestMapping("/deleteProductMaterialNum")
    public ResponseMsg deleteProductMaterialNum(@RequestParam("productMaterialNumId") Long productMaterialNumId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != productMaterialNumId && 0 != productMaterialNumId) {
            ProductMaterialNum productMaterialNum = productMaterialNumMapper.selectByPrimaryKey(productMaterialNumId);
            if(null == productMaterialNum) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            productMaterialNum.setYn(-1);
            productMaterialNumMapper.updateByPrimaryKey(productMaterialNum);
        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }
}
