package com.kone.commonsDao.dao;

import com.kone.utils.bo.ProductByDayBO;
import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.ProductMaterial;

import java.util.List;

public interface ProductMaterialMapper {
    int deleteByPrimaryKey(Long productMaterialId);

    int insert(ProductMaterial record);

    int insertSelective(ProductMaterial record);

    ProductMaterial selectByPrimaryKey(Long productMaterialId);

    int updateByPrimaryKeySelective(ProductMaterial record);

    int updateByPrimaryKey(ProductMaterial record);

    Long countByPager(CommonCondition condition);

    List<ProductMaterial> selectByPager(CommonCondition condition);

    Float getSum(CommonCondition condition);

    /**
     * 通过时间段查看产品的入库情况
     *   显示该时间段，该产品总共使用的数量
     * @param condition
     * @return
     */
    List<ProductByDayBO> viewProductInByDay(CommonCondition condition);

    /**
     * 通过时间段查看产品的入库情况 总条数
     * @param condition
     * @return
     */
    Long getProductInByDaySum(CommonCondition condition);
}