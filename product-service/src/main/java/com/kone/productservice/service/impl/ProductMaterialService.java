package com.kone.productservice.service.impl;

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


}
