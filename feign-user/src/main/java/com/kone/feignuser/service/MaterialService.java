package com.kone.feignuser.service;

import com.kone.feignuser.FeignConfig.FeignConfig;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Material;
import com.kone.utils.entity.MaterialDetails;
import com.kone.utils.entity.MaterialIn;
import com.kone.utils.entity.User;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "material-service")
public interface MaterialService {

    @RequestMapping(value = "/materialService/saveMaterial", consumes = "application/json")
    @ResponseBody
    ResponseMsg<User> saveMaterial(@RequestBody Material material);

    @RequestMapping(value = "/materialService/viewMaterial", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<Material>> viewMaterial(@RequestBody CommonCondition condition);

    @RequestMapping(value = "/materialService/viewAllMaterial", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<Material>> viewAllMaterial();

    @RequestMapping(value = "/materialDetailsService/viewMaterialDetails", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<MaterialDetails>> viewMaterialDetails(@RequestParam("materialId")Long materialId, @RequestBody Pager pager);

    /**
     * 材料入库
     * @param materialIn
     * @return
     */
    @RequestMapping(value = "/materialService/saveMaterialIn", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveMaterialIn(@RequestBody MaterialIn materialIn);

    /**
     * 查看材料入库记录
     * @param condition
     * @return
     */
    @RequestMapping(value = "/materialService/viewMaterialIn", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<MaterialIn>> viewMaterialIn(@RequestBody CommonCondition condition);


    /**
     * 删除材料，改变yn的值
     * @param materialId
     * @return
     */
    @RequestMapping(value = "/materialService/deleteMaterial", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteMaterial(@RequestParam("materialId") Long materialId);
}
