package com.kone.materialservice.service.impl;

import com.kone.commonsDao.dao.MaterialInMapper;
import com.kone.materialservice.service.IMaterialService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.MaterialIn;
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
@RequestMapping("/materialService")
public class MaterialService implements IMaterialService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private com.kone.commonsDao.dao.MaterialMapper materialMapper;

    @Resource
    private MaterialInMapper materialInMapper;

    @Override
    @RequestMapping("/viewMaterial")
    public ResponseMsg<List<Material>> viewMaterial(@RequestBody Pager pager) {
        logger.info("view material");
        ResponseMsg<List<Material>> msg = new ResponseMsg<List<Material>>();

        Long total = materialMapper.countByPager(new CommonCondition());
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart((pager.getNum() * pager.getSize()));
        List<Material> materials = materialMapper.selectByPager(pager);
        msg.setData(materials);


        msg.setPager(pager);
        return msg;
    }

    @Override
    @RequestMapping("/saveMaterial")
    public ResponseMsg saveMaterial(@RequestBody Material material) {
        ResponseMsg<Material> msg = new ResponseMsg<>();
        if(null != material && !StringUtils.isEmpty(material.getMaterialName()) && !StringUtils.isEmpty(material.getMaterialUnit())) {
            Material material1 = materialMapper.selectByMaterialName(material.getMaterialName());
            if(null == material1) {
                int re = materialMapper.insert(material);
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
    @RequestMapping("/viewAllMaterial")
    public ResponseMsg<List<Material>> viewAllMaterial() {
        ResponseMsg<List<Material>> msg = new ResponseMsg<>();
        msg.setData(materialMapper.viewAllMaterial());
        return msg;
    }

    @Override
    @RequestMapping("/saveMaterialIn")
    @Transactional
    public ResponseMsg saveMaterialIn(@RequestBody MaterialIn materialIn) {
        ResponseMsg msg = new ResponseMsg();
        logger.info("save material in num");
        if(null != materialIn && materialIn.getMaterialInNum() <= 0) {
            logger.info("material is null or material num <= 0");
            msg.setMsg(MsgEnum.INPUT_THE_RIGHT_NUMBER.getMsg());
            msg.setCode(MsgEnum.INPUT_THE_RIGHT_NUMBER.getCode());
            return msg;
        }
        if(null != materialIn && null != materialIn.getMaterialId() && 0 != materialIn.getMaterialId()) {
            Material material = materialMapper.selectByPrimaryKey(materialIn.getMaterialId());
            if(null == material) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            material.setMaterialNum(material.getMaterialNum() + materialIn.getMaterialInNum());
            materialMapper.updateByPrimaryKey(material);


            materialIn.setMaterialName(material.getMaterialName());
            materialIn.setInUnit(material.getMaterialUnit());
            materialInMapper.insert(materialIn);

        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }

    @Override
    @RequestMapping("/viewMaterialIn")
    public ResponseMsg<List<MaterialIn>> viewMaterialIn(@RequestBody CommonCondition condition) {
        logger.info("view material in, material id is " + condition.getId());
        ResponseMsg<List<MaterialIn>> msg = new ResponseMsg<>();

        Pager pager = condition.getPager();
        Long total = materialInMapper.countByPager(condition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        Float sum = materialInMapper.getSum(condition);

        pager.setStart((pager.getNum() * pager.getSize()));
        List<MaterialIn> materialIns = materialInMapper.selectByPager(condition);
        msg.setData(materialIns);

        msg.setPager(pager);
        msg.setSum(sum);
        return msg;
    }

    @Override
    @RequestMapping("/deleteMaterial")
    public ResponseMsg deleteMaterial(Long materialId) {
        ResponseMsg msg = new ResponseMsg();
        if(null != materialId && 0 != materialId) {
            Material material = materialMapper.selectByPrimaryKey(materialId);
            if(null == material) {
                msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
                msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
                return msg;
            }
            material.setYn(-1);
            materialMapper.updateByPrimaryKey(material);
        } else {
            msg.setMsg(MsgEnum.NEED_INPUT_FIELD_ERROR.getMsg());
            msg.setCode(MsgEnum.NEED_INPUT_FIELD_ERROR.getCode());
            return msg;
        }
        return msg;
    }
}
