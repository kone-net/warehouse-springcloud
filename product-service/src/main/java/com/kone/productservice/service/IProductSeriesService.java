package com.kone.productservice.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductSeries;
import com.kone.utils.entity.ProductSeriesName;
import com.kone.utils.msg.ResponseMsg;

import java.util.List;

public interface IProductSeriesService {
    ResponseMsg<List<ProductSeriesName>> viewProductSeries(CommonCondition condition);

    ResponseMsg<List<ProductSeriesName>> viewAllProductSeries();

    ResponseMsg saveProductSeriesName(ProductSeriesName productSeriesName);

    ResponseMsg<List<ProductSeries>> viewSeriesDetails(CommonCondition condition);

    ResponseMsg saveProductSeries(ProductSeries productSeries);

    ResponseMsg deleteProductSeries(Long productSeriesId);

    ResponseMsg deleteProductSeriesName(Long productSeriesNameId);

}
