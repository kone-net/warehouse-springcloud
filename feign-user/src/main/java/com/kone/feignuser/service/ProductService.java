package com.kone.feignuser.service;

import com.kone.utils.bo.ProductByDayBO;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.ProductStatisticsDTO;
import com.kone.utils.entity.*;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductService {

    @RequestMapping(value = "/productService/saveProduct", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveProduct(@RequestBody Product product);

    @RequestMapping(value = "/productService/viewProduct", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<Product>> viewProduct(@RequestBody CommonCondition condition);

    @RequestMapping(value = "/productMaterialNumService/saveProductMaterialNum", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveProductMaterialNum(@RequestBody ProductMaterialNum productMaterialNum);

    @RequestMapping(value = "/productService/viewAllProduct", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<Product>> viewAllProduct();


    /**
     * Product warehousing operation.
     * @param dto
     * @return
     */
    @RequestMapping(value = "/productService/productOutbound", consumes = "application/json")
    @ResponseBody
    ResponseMsg productOutbound(@RequestBody ProductStatisticsDTO dto);


    @RequestMapping(value = "/productMaterialService/viewProductMaterialByProductId", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductMaterial>> viewProductIn(@RequestBody CommonCondition condition);

    @RequestMapping(value = "/productService/deleteProduct", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteProduct(@RequestParam("productId") Long productId);

    /**
     * 通过时间段查看产品的入库统计
     *    查询该时间段，入库该产品的总量
     * @param condition 通过group by统计的总数，和该产品对应的id，通过id查询材料的详细
     * @return
     */
    @RequestMapping(value = "/productMaterialService/viewProductInByDay", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductByDayBO>> viewProductInByDay(@RequestBody CommonCondition condition);


    /**
     * 通过时间段查看产品的出库统计
     *    查询该时间段，出库该产品的总量
     * @param condition 通过group by统计的总数，和该产品对应的id，通过id查询材料的详细
     * @return
     */
    @RequestMapping(value = "/productMaterialService/viewProductOutByDay", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductByDayBO>> viewProductOutByDay(@RequestBody CommonCondition condition);


//    product matieral

    @RequestMapping(value = "/productMaterialNumService/viewProductMaterialNumByProductId", consumes = "application/json")
    @ResponseBody
    List<ProductMaterialNum> viewProductMaterialNumByProductId(@RequestBody ProductMaterialNum productMaterialNum);

    @RequestMapping(value = "/productMaterialService/saveProductMaterial", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveProductMaterial(@RequestBody ProductMaterial productMaterial);

    @RequestMapping(value = "/productMaterialNumService/deleteProductMaterialNum", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteProductMaterialNum(@RequestParam("productMaterialNumId") Long productMaterialNumId);

//    product series name
    @RequestMapping(value = "/productSeriesService/viewProductSeries", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductSeriesName>> viewProductSeries(@RequestBody CommonCondition condition);

    @RequestMapping(value = "/productSeriesService/viewAllProductSeries", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductSeriesName>> viewAllProductSeries();


    @RequestMapping(value = "/productSeriesService/saveProductSeriesName", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveProductSeriesName(@RequestBody ProductSeriesName productSeriesName);


    @RequestMapping(value = "/productSeriesService/deleteProductSeries", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteProductSeries(@RequestParam("productSeriesId") Long productSeriesId);

    @RequestMapping(value = "/productSeriesService/deleteProductSeriesName", consumes = "application/json")
    @ResponseBody
    ResponseMsg deleteProductSeriesName(@RequestParam("productSeriesNameId") Long productSeriesNameId);




//    product series details
    @RequestMapping(value = "/productSeriesService/viewSeriesDetails", consumes = "application/json")
    @ResponseBody
    ResponseMsg<List<ProductSeries>> viewSeriesDetails(@RequestBody CommonCondition condition);

    @RequestMapping(value = "/productSeriesService/saveProductSeries", consumes = "application/json")
    @ResponseBody
    ResponseMsg saveProductSeries(@RequestBody ProductSeries productSeries);
}
