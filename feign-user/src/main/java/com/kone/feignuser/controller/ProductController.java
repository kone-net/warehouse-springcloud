package com.kone.feignuser.controller;

import com.kone.feignuser.service.ProductService;
import com.kone.utils.bo.ProductByDayBO;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dateUtils.DateUtil;
import com.kone.utils.entity.*;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ProductService productService;

    @ApiImplicitParam(name="num",value="第几页",dataType="Integer", paramType = "query")
    @ApiOperation(value = "查看产品", notes = "查看产品")
    @GetMapping("/viewProduct")
    public String viewMaterial(Pager pager, Model model) {
        ResponseMsg<List<Product>> products = productService.viewProduct(pager);

        model.addAttribute("data", products);
        return "product/viewProduct";
    }

    /**
     * 查看所有产品
     * @return
     */
    @GetMapping("/viewAllProduct")
    @ResponseBody
    public ResponseMsg<List<Product>> viewAllProduct() {
        ResponseMsg<List<Product>> products = productService.viewAllProduct();
        return products;
    }

    /**
     * 保存产品
     * @param product
     * @return
     */
    @PostMapping("/saveProduct")
    @ResponseBody
    public ResponseMsg saveMaterial(Product product) {
        ResponseMsg msg = productService.saveProduct(product);
        return msg;
    }

    /**
     * 通过产品id查看产品和原料的关系
     * @param productMaterialNum
     * @return
     */
    @GetMapping("/viewProductMaterialNumByProductId")
    public String viewProductMaterialNumByProductId(ProductMaterialNum productMaterialNum, Model model) {
        logger.info(productMaterialNum.getProductId());
        List<ProductMaterialNum> productMaterialNums = productService.viewProductMaterialNumByProductId(productMaterialNum);

        model.addAttribute("data", productMaterialNums);
        model.addAttribute("productId", productMaterialNum.getProductId());
        return "product/viewProductMaterialNum";
    }

    /**
     * 保存原材料和产品的对应关系
     * @param productMaterialNum
     * @return
     */
    @PostMapping("/saveProductMaterialNum")
    @ResponseBody
    public ResponseMsg saveProductMaterialNum(ProductMaterialNum productMaterialNum) {
        ResponseMsg msg = productService.saveProductMaterialNum(productMaterialNum);
        return msg;
    }


    /**
     * 产品入库
     * @param productMaterial
     * @return
     */
    @PostMapping("/saveProductMaterial")
    @ResponseBody
    public ResponseMsg saveProductMaterial(ProductMaterial productMaterial, String date) {
        if(!StringUtils.isEmpty(date)) {
            productMaterial.setGmtCreate(DateUtil.getDateByString(date));
        } else {
            productMaterial.setGmtCreate(new Date());
        }
        ResponseMsg msg = productService.saveProductMaterial(productMaterial);
        return msg;
    }



    /**
     * 通过时间段查看材料的入库情况
     *   显示该时间段，该材料总共使用的数量
     * @param condition
     * @return
     */
    @GetMapping("/viewProductInByDay")
    public String viewProductInByDay(Model model, CommonCondition condition) {
        ResponseMsg<List<ProductByDayBO>> data = productService.viewProductInByDay(condition);
        model.addAttribute("data", data);
        model.addAttribute("condition", condition);
        return "product/viewProductInByDay";
    }

    /**
     * 通过时间段查看材料的出库情况
     *   显示该时间段，该材料总共使用的数量
     * @param condition
     * @return
     */
    @GetMapping("/viewProductOutByDay")
    public String viewProductOutByDay(Model model, CommonCondition condition) {
        ResponseMsg<List<ProductByDayBO>> data = productService.viewProductOutByDay(condition);
        model.addAttribute("data", data);
        model.addAttribute("condition", condition);
        return "product/viewProductOutByDay";
    }

//    product series name

    /**
     * 查看产品系列名称
     * @param condition
     * @return
     */
    @GetMapping("/viewProductSeriesName")
    public String viewProductSeriesName(CommonCondition condition, Model model) {
        ResponseMsg<List<ProductSeriesName>> msg = productService.viewProductSeries(condition);

        model.addAttribute("data", msg);
        return "product/viewProductSeriesName";
    }

    /**
     * 查看所有系列名称
     * @return
     */
    @GetMapping("/viewAllProductSeries")
    @ResponseBody
    public ResponseMsg<List<ProductSeriesName>> viewAllProductSeries() {
        ResponseMsg<List<ProductSeriesName>> msg = productService.viewAllProductSeries();

        return msg;
    }

    /**
     * 保存产品系列名称
     * @param productSeriesName
     * @return
     */
    @PostMapping("/saveProductSeriesName")
    @ResponseBody
    public ResponseMsg saveProductSeriesName(ProductSeriesName productSeriesName) {
        ResponseMsg msg = productService.saveProductSeriesName(productSeriesName);
        return msg;
    }

    /**
     * 查看产品系列对应的产品
     * @param condition
     * @param model
     * @return
     */
    @GetMapping("/viewSeriesDetails")
    public String viewSeriesDetails(CommonCondition condition, Model model) {
        ResponseMsg<List<ProductSeries>> msg = productService.viewSeriesDetails(condition);

        model.addAttribute("data", msg);
        model.addAttribute("productSeriesNameId",condition.getId());
        return "product/viewSeriesDetails";
    }

    /**
     * 通过选择系列，查询对应的产品
     * @param condition
     * @return
     */
    @GetMapping("/viewProductBySeriesId")
    @ResponseBody
    public ResponseMsg<List<ProductSeries>> viewProductBySeriesId(CommonCondition condition) {
        ResponseMsg<List<ProductSeries>> msg = productService.viewSeriesDetails(condition);
        return msg;
    }

    /**
     * 保存产品系列对应的产品
     * @param productSeries
     * @return
     */
    @PostMapping("/saveProductSeries")
    @ResponseBody
    public ResponseMsg saveProductSeries(ProductSeries productSeries) {
        ResponseMsg msg = productService.saveProductSeries(productSeries);
        return msg;
    }


    /**
     * 查看产品入库记录
     * @param condition
     * @param model
     * @return
     */
    @GetMapping("/viewProductIn")
    public String viewProductIn(CommonCondition condition, Model model) {
        logger.info("product id is " + condition.getId());
        ResponseMsg<List<ProductMaterial>> msg = productService.viewProductIn(condition);

        model.addAttribute("data", msg);
        model.addAttribute("condition", condition);
        return "product/viewProductIn";
    }

    /**
     * 删除产品，改变yn的值
     * @param productId
     * @return
     */
    @PostMapping("/deleteProduct")
    @ResponseBody
    public ResponseMsg deleteProduct(Long productId) {
        ResponseMsg msg = productService.deleteProduct(productId);
        return msg;
    }

    /**
     * 删除产品和材料的对应关系
     * @param productMaterialNumId
     * @return
     */
    @PostMapping("/deleteProductMaterialNum")
    @ResponseBody
    public ResponseMsg deleteProductMaterialNum(Long productMaterialNumId) {
        logger.info("delete product material num and productMaterialNumId is " + productMaterialNumId);
        ResponseMsg msg = productService.deleteProductMaterialNum(productMaterialNumId);
        return msg;
    }

    /**
     * 删除系列名称
     * @param productSeriesNameId
     * @return
     */
    @PostMapping("/deleteProductSeriesName")
    @ResponseBody
    public ResponseMsg deleteProductSeriesName(Long productSeriesNameId) {
        logger.info("delete product series name and productSeriesNameId is " + productSeriesNameId);
        ResponseMsg msg = productService.deleteProductSeriesName(productSeriesNameId);
        return msg;
    }

    /**
     * 删除具体系列详情
     * @param productSeriesId
     * @return
     */
    @PostMapping("/deleteProductSeries")
    @ResponseBody
    public ResponseMsg deleteProductSeries(Long productSeriesId) {
        logger.info("delete product series and productSeriesId is " + productSeriesId);
        ResponseMsg msg = productService.deleteProductSeries(productSeriesId);
        return msg;
    }
}
