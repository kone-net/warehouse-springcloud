package com.kone.productservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kone.commonsDao.dao.OrderProductMapper;
import com.kone.productservice.service.IProductMaterialService;
import com.kone.utils.bo.ProductByDayBO;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.*;
import com.kone.utils.msg.MsgEnum;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import com.kone.utils.pager.PagerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/productMaterialService")
public class ProductMaterialService implements IProductMaterialService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private com.kone.commonsDao.dao.ProductMapper productMapper;

    @Resource
    private com.kone.commonsDao.dao.MaterialMapper materialMapper;

    @Resource
    private com.kone.commonsDao.dao.ProductMaterialMapper productMaterialMapper;

    @Resource
    private com.kone.commonsDao.dao.ProductMaterialNumMapper productMaterialNumMapper;

    @Resource
    private com.kone.commonsDao.dao.MaterialDetailsMapper materialDetailsMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    @RequestMapping("/saveProductMaterial")
    public ResponseMsg saveProductMaterial(@RequestBody ProductMaterial productMaterial) {
        ResponseMsg<ProductMaterial> msg = new ResponseMsg<>();
        Product product = null;
        if(null != productMaterial && 0 != productMaterial.getProductId()) {
            product = productMapper.selectByPrimaryKey(productMaterial.getProductId());
            if(null == product) {
                logger.info("product_id is not right");
                msg.setMsg(MsgEnum.NULL_INFO.getMsg());
                msg.setCode(MsgEnum.NULL_INFO.getCode());
                return msg;
            }

            int re = productMaterialMapper.insert(productMaterial);
            logger.info("product material id is " + productMaterial.getProductMaterialId());
            if(1 != re) {
                logger.info("save product_material is error!");
                msg.setMsg(MsgEnum.SAVE_ERROR.getMsg());
                msg.setCode(MsgEnum.SAVE_ERROR.getCode());
                return msg;
            }
//            获取产品和材料之间的关系
            List<ProductMaterialNum> productMaterialNums = productMaterialNumMapper.selectByProductId(productMaterial.getProductId());
            for(ProductMaterialNum productMaterialNum: productMaterialNums) {
//                产品总量*单个产品需要的数量=总共需要的数量
                Float totalNum = productMaterialNum.getMaterialNum() * productMaterial.getProductNum();

//                产品入库使用材料明细
                MaterialDetails materialDetails = new MaterialDetails();
                materialDetails.setProductId(productMaterial.getProductId());
                materialDetails.setMaterialId(productMaterialNum.getMaterialId());
                materialDetails.setMaterialUseNum(totalNum);
                materialDetails.setProductMaterialId(productMaterial.getProductMaterialId());
//                materialDetails.set
                materialDetailsMapper.insert(materialDetails);

//                材料数量减少
                Material material = productMaterialNum.getMaterial();
                material.setMaterialNum(material.getMaterialNum() - totalNum);
                material.setGmtUpdate(new Date());
                materialMapper.updateByPrimaryKey(material);
            }



            product.setProductNum(product.getProductNum() + productMaterial.getProductNum());
            productMapper.updateByPrimaryKey(product);
        } else {
            logger.info("product or material id is null");
            msg.setMsg(MsgEnum.NULL_INFO.getMsg());
            msg.setCode(MsgEnum.NULL_INFO.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/viewProductMaterialByProductId")
    public ResponseMsg<List<ProductMaterial>> viewProductMaterialByProductId(@RequestBody CommonCondition condition) {
        logger.info("view product  in, product id is " + condition.getId());
        ResponseMsg<List<ProductMaterial>> msg = new ResponseMsg<>();

        Pager pager = condition.getPager();
        Long total = productMaterialMapper.countByPager(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        Float sum = productMaterialMapper.getSum(condition);

        pager.setStart((pager.getNum() * pager.getSize()));
        List<ProductMaterial> productMaterials = productMaterialMapper.selectByPager(condition);
        msg.setData(productMaterials);

        msg.setPager(pager);
        msg.setSum(sum);
        return msg;
    }

    @Override
    @RequestMapping("/viewProductInByDay")
    public ResponseMsg<List<ProductByDayBO>> viewProductInByDay(@RequestBody CommonCondition condition) {
        logger.info("view product put in storage By Day");
        ResponseMsg<List<ProductByDayBO>> msg = new ResponseMsg<>();

        Pager pager = condition.getPager();
        Long total = productMaterialMapper.getProductInByDaySum(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        List<ProductByDayBO> datas = productMaterialMapper.viewProductInByDay(condition);

        msg.setPager(pager);
        msg.setData(datas);
        return msg;
    }

    @Override
    @RequestMapping("/viewProductOutByDay")
    public ResponseMsg<List<ProductByDayBO>> viewProductOutByDay(@RequestBody CommonCondition condition) {
        logger.info("view product outbound By Day");
        ResponseMsg<List<ProductByDayBO>> msg = new ResponseMsg<>();

        Pager pager = condition.getPager();
        Long total = orderProductMapper.getProductOutByDaySum(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        List<ProductByDayBO> datas = orderProductMapper.viewProductOutByDay(condition);

        msg.setPager(pager);
        msg.setData(datas);
        return msg;
    }

    @Override
    @RequestMapping("/updateProductInRecord")
    public ResponseMsg updateProductInRecord(@RequestBody ProductMaterial productMaterial) {
        logger.info("update product inbound Record");
        ResponseMsg msg = new ResponseMsg<>();
        if(null == productMaterial || null == productMaterial.getProductMaterialId() || 0 == productMaterial.getProductMaterialId()
                || null == productMaterial.getProductNum()) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
//        1.query and get old product record
        ProductMaterial oldProduct = productMaterialMapper.selectByPrimaryKey(productMaterial.getProductMaterialId());
        if(null == oldProduct) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        logger.info("old product record:" + oldProduct.toString());

//        2.calculate the num
        Long resultNum = 0L;
        resultNum = productMaterial.getProductNum() - oldProduct.getProductNum();

//        3.add or subtract from store.
        Product product = productMapper.selectByPrimaryKey(oldProduct.getProductId());
        logger.info("before the product status is updated" + product.toString());
        Long nowNum = product.getProductNum() + resultNum;
        product.setProductNum(nowNum);
        product.setGmtUpdate(new Date());

        productMapper.updateByPrimaryKey(product);

        logger.info("after product status is updated : "+ product.toString());

//        4.update the material record
        oldProduct.setProductNum(productMaterial.getProductNum());
        productMaterialMapper.updateByPrimaryKey(oldProduct);
        logger.info("new product record: " + oldProduct.toString());


        return msg;
    }

    @Override
    @RequestMapping("/viewProductOutBoundRecord")
    public ResponseMsg<List<OrderProduct>> viewProductOutBoundRecord(@RequestBody CommonCondition condition) {

        logger.info("condition for viewProductOutBoundRecord :" + condition.toString());
        ResponseMsg msg = new ResponseMsg<>();

        Pager pager = condition.getPager();

        PageHelper.offsetPage(pager.getNum().intValue() * pager.getSize(), pager.getSize());

        List<OrderProduct> productRecord = orderProductMapper.selectByProductId2(condition);
        logger.info(productRecord.size());

        PageInfo<OrderProduct> pageInfo = new PageInfo<>(productRecord);

        pager.setTotal(pageInfo.getTotal());
        pager = PagerUtils.getPager(pager);



        msg.setSum(orderProductMapper.getOrderProductNumSum(condition));
        msg.setPager(pager);
        msg.setData(productRecord);

        logger.info("pager" + pageInfo.toString());

        return msg;
    }

    @Override
    @RequestMapping("/updateProductOutRecord")
    public ResponseMsg updateProductOutRecord(@RequestBody OrderProduct orderProduct) {
        logger.info("update product outbound Record");
        ResponseMsg msg = new ResponseMsg<>();
        if(null == orderProduct || null == orderProduct.getOrderProductId() || 0 == orderProduct.getOrderProductId()
                || null == orderProduct.getProductNum()) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
//        1.query and get old order product record
        OrderProduct oldOrderProduct = orderProductMapper.selectByPrimaryKey(orderProduct.getOrderProductId());
        if(null == oldOrderProduct) {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        logger.info("old order product record:" + oldOrderProduct.toString());

//        2.calculate the num
        Long resultNum = 0L;
        resultNum = oldOrderProduct.getProductNum() - orderProduct.getProductNum();

//        3.add or subtract from store.
        Product product = productMapper.selectByPrimaryKey(oldOrderProduct.getProductId());
        logger.info("before the order product status is updated" + product.toString());
        Long nowNum = product.getProductNum() + resultNum;
        product.setProductNum(nowNum);
        product.setGmtUpdate(new Date());

        productMapper.updateByPrimaryKey(product);

        logger.info("after order product status is updated : "+ product.toString());

//        4.update the material record
        oldOrderProduct.setProductNum(orderProduct.getProductNum());
        orderProductMapper.updateByPrimaryKey(oldOrderProduct);
        logger.info("new order product record: " + oldOrderProduct.toString());

        return msg;
    }


}
