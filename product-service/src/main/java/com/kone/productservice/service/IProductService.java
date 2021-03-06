package com.kone.productservice.service;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.ProductStatisticsDTO;
import com.kone.utils.entity.Product;
import com.kone.utils.msg.ResponseMsg;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface IProductService {
    ResponseMsg<List<Product>> viewProduct(CommonCondition condition);

    ResponseMsg saveProduct(Product product);

    ResponseMsg<List<Product>> viewAllProduct();

    ResponseMsg deleteProduct(Long productId);

    /**
     * Product warehousing operation.
     * @param dto
     * @return
     */
    ResponseMsg productOutbound(ProductStatisticsDTO dto);
}
