package com.kone.materialservice.service.impl;

import com.kone.commonsDao.dao.MaterialDetailsMapper;
import com.kone.materialservice.service.IMaterialDetailsService;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.MaterialDetailsDTO;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.MaterialDetails;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import com.kone.utils.pager.PagerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/materialDetailsService")
public class MaterialDetailsService implements IMaterialDetailsService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private MaterialDetailsMapper materialDetailsMapper;

    @Override
    @RequestMapping("/viewMaterialDetails")
    public ResponseMsg<List<MaterialDetails>> viewMaterialDetails(Long materialId, @RequestBody Pager pager) {
        logger.info("view material details");

        ResponseMsg<List<MaterialDetails>> msg = new ResponseMsg<List<MaterialDetails>>();

        CommonCondition commonCondition = new CommonCondition();
        commonCondition.setId(materialId);
        Long total = materialDetailsMapper.countByPager(commonCondition);
        pager.setTotal(total);
        pager = PagerUtils.getPager(pager);

        pager.setStart(pager.getNum() * pager.getSize());
        MaterialDetailsDTO dto = new MaterialDetailsDTO();
        dto.setPager(pager);
        dto.setMaterialId(materialId);
        List<MaterialDetails> materialDetails = materialDetailsMapper.selectByMaterialId(dto);

        msg.setData(materialDetails);

        msg.setPager(pager);
        return msg;
    }


}
