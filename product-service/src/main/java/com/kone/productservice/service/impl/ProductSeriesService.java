package com.kone.productservice.service.impl;

import com.kone.commonsDao.dao.ProductMapper;
import com.kone.commonsDao.dao.ProductSeriesMapper;
import com.kone.commonsDao.dao.ProductSeriesNameMapper;
import com.kone.productservice.service.IProductSeriesService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductSeries;
import com.kone.utils.entity.ProductSeriesName;
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
@RequestMapping("/productSeriesService")
public class ProductSeriesService implements IProductSeriesService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ProductSeriesMapper productSeriesMapper;

    @Resource
    private ProductSeriesNameMapper productSeriesNameMapper;


    @Override
    @RequestMapping("/viewProductSeries")
    public ResponseMsg<List<ProductSeriesName>> viewProductSeries(@RequestBody CommonCondition condition) {
        logger.info("view product");
        Pager pager = condition.getPager();
        ResponseMsg<List<ProductSeriesName>> msg = new ResponseMsg<List<ProductSeriesName>>();

        Long total = productSeriesNameMapper.countByPager(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart(pager.getNum() * pager.getSize());
        List<ProductSeriesName> productSeriesNames = productSeriesNameMapper.selectByPager(condition);

        msg.setData(productSeriesNames);

        msg.setPager(pager);
        return msg;
    }

    @Override
    @RequestMapping("/viewAllProductSeries")
    public ResponseMsg<List<ProductSeriesName>> viewAllProductSeries() {
        logger.info("view all product series name");
        ResponseMsg<List<ProductSeriesName>> msg = new ResponseMsg<List<ProductSeriesName>>();

        List<ProductSeriesName> productSeriesNames = productSeriesNameMapper.selectAllProductSeriesName();
        msg.setData(productSeriesNames);

        return msg;
    }

    @Override
    @RequestMapping("/saveProductSeriesName")
    public ResponseMsg saveProductSeriesName(@RequestBody ProductSeriesName productSeriesName) {
        ResponseMsg<ProductSeriesName> msg = new ResponseMsg<>();
        if(null != productSeriesName && !StringUtils.isEmpty(productSeriesName.getProductSeriesName())) {
            ProductSeriesName productSeriesName1 = productSeriesNameMapper.selectByName(productSeriesName.getProductSeriesName());
            if(null == productSeriesName1) {
                int re = productSeriesNameMapper.insert(productSeriesName);
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
    @RequestMapping("/viewSeriesDetails")
    public ResponseMsg<List<ProductSeries>> viewSeriesDetails(@RequestBody CommonCondition condition) {
        Pager pager = condition.getPager();
        ResponseMsg<List<ProductSeries>> msg = new ResponseMsg<List<ProductSeries>>();

        Long total = productSeriesMapper.countByPager(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart(pager.getNum() * pager.getSize());
        List<ProductSeries> productSeries = productSeriesMapper.selectBySeriesNameId(condition);

        msg.setData(productSeries);

        msg.setPager(pager);
        return msg;
    }

    @Override
    @RequestMapping("saveProductSeries")
    public ResponseMsg saveProductSeries(@RequestBody ProductSeries productSeries) {
        ResponseMsg<ProductSeriesName> msg = new ResponseMsg<>();
        if(null != productSeries && 0 != productSeries.getProductSeriesNameId() && 0 != productSeries.getProductId()) {
            List<ProductSeries> productSeries1 = productSeriesMapper.selectByProductAndSeriesId(
                    productSeries.getProductSeriesNameId(), productSeries.getProductId());
            if(productSeries1.size() <= 0) {
                int re = productSeriesMapper.insert(productSeries);
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
    @RequestMapping("/deleteProductSeries")
    public ResponseMsg deleteProductSeries(Long productSeriesId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != productSeriesId && 0 != productSeriesId) {
            ProductSeries productSeries = productSeriesMapper.selectByPrimaryKey(productSeriesId);
            if(null == productSeries) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            productSeries.setYn(-1);
            productSeriesMapper.updateByPrimaryKey(productSeries);
        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/deleteProductSeriesName")
    public ResponseMsg deleteProductSeriesName(Long productSeriesNameId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != productSeriesNameId && 0 != productSeriesNameId) {
            ProductSeriesName productSeriesName = productSeriesNameMapper.selectByPrimaryKey(productSeriesNameId);
            if(null == productSeriesName) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            productSeriesName.setYn(-1);
            productSeriesNameMapper.updateByPrimaryKey(productSeriesName);
        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

}
