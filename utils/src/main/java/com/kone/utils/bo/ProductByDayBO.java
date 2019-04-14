package com.kone.utils.bo;

import com.kone.utils.entity.Product;

public class ProductByDayBO {
    private Long productId;

    private Float productNumSum;

    private String productName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getProductNumSum() {
        return productNumSum;
    }

    public void setProductNumSum(Float productNumSum) {
        this.productNumSum = productNumSum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
