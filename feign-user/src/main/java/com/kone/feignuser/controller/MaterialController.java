package com.kone.feignuser.controller;

import com.kone.feignuser.service.MaterialService;
import com.kone.utils.bo.MaterialByDayBO;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dateUtils.DateUtil;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.MaterialDetails;
import com.kone.utils.entity.MaterialIn;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(value = "材料操作", tags = "材料操作")
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Resource
    private MaterialService materialService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping("/viewMaterial")
    public String viewMaterial(CommonCondition condition, Model model) {
        ResponseMsg<List<Material>> msg = materialService.viewMaterial(condition);

        logger.info("name :" + condition.getName());
        model.addAttribute("data", msg);
        model.addAttribute("condition", condition);
        return "material/viewMaterial";
    }

    /**
     * 保存材料
     * @param material
     * @return
     */
    @PostMapping("/saveMaterial")
    @ResponseBody
    public ResponseMsg saveMaterial(Material material, String date) {
        if(!StringUtils.isEmpty(date)) {
            material.setGmtCreate(DateUtil.getDateByString(date));
        } else {
            material.setGmtCreate(new Date());
        }
        ResponseMsg msg = materialService.saveMaterial(material);
        return msg;
    }

    /**
     * 查看所有的材料信息
     * @return
     */
    @GetMapping("/viewAllMaterial")
    @ResponseBody
    public ResponseMsg<List<Material>> viewAllMaterial() {
        ResponseMsg<List<Material>> msg = materialService.viewAllMaterial();
        return msg;
    }

    /**
     * 查看材料的使用详情
     * @param materialId
     * @return
     */
    @GetMapping("/viewMaterialDetails")
    public String viewMaterialDetails(Long materialId, Model model, Pager pager) {
        ResponseMsg<List<MaterialDetails>> materialDetails = materialService.viewMaterialDetails(materialId, pager);
        model.addAttribute("data", materialDetails);
        model.addAttribute("materialId", materialId);
        return "material/viewMaterialDetails";
    }

    /**
     * 查看材料入库记录
     * @param condition
     * @param model
     * @return
     */
    @GetMapping("/viewMaterialIn")
    public String viewMaterialIn(CommonCondition condition, Model model) {
        logger.info("material id is " + condition.getId());
        ResponseMsg<List<MaterialIn>> msg = materialService.viewMaterialIn(condition);

        model.addAttribute("data", msg);
        model.addAttribute("materialId", condition.getId());
        model.addAttribute("condition", condition);
        return "material/viewMaterialIn";
    }

    /**
     * 保存材料入库记录
     * @param materialIn
     * @return
     */
    @PostMapping("/saveMaterialIn")
    @ResponseBody
    public ResponseMsg saveMaterialIn(MaterialIn materialIn) {
        logger.info("material in date:" + materialIn.getDate());
        ResponseMsg msg = materialService.saveMaterialIn(materialIn);
        return msg;
    }

    /**
     * 删除材料，改变yn的值
     * @param materialId
     * @return
     */
    @PostMapping("/deleteMaterial")
    @ResponseBody
    public ResponseMsg deleteMaterial(Long materialId) {
        ResponseMsg msg = materialService.deleteMaterial(materialId);
        return msg;
    }

    /**
     * 通过时间段查看材料的入库情况
     *   显示该时间段，该材料总共使用的数量
     * @param condition
     * @return
     */
    @GetMapping("/viewMaterialInByDay")
    public String viewMaterialInByDay(Model model, CommonCondition condition) {
        ResponseMsg<List<MaterialByDayBO>> materialDetails = materialService.viewMaterialInByDay(condition);
        model.addAttribute("data", materialDetails);
        model.addAttribute("condition", condition);
        return "material/viewMaterialInByDay";
    }

    /**
     * 通过时间段查看材料的出库情况
     *   显示该时间段，该材料总共使用的数量
     * @param condition
     * @return
     */
    @GetMapping("/viewMaterialOutByDay")
    public String viewMaterialOutByDay(Model model, CommonCondition condition) {
        ResponseMsg<List<MaterialByDayBO>> materialDetails = materialService.viewMaterialOutByDay(condition);
        model.addAttribute("data", materialDetails);
        model.addAttribute("condition", condition);
        return "material/viewMaterialOutByDay";
    }


    /**
     * 修改入库记录的数量，会相应增加或减少库存
     * @param materialIn 主要包括材料id和材料的入库数量
     * @return
     */
    @PostMapping("/updateMaterialRecord")
    @ResponseBody
    public ResponseMsg updateMaterialRecord(MaterialIn materialIn) {
        ResponseMsg msg = materialService.updateMaterialRecord(materialIn);
        return msg;
    }
}
