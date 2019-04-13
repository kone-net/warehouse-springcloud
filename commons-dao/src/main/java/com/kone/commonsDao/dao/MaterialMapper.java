package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.Material;
import com.kone.utils.pager.Pager;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Long materialId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Long materialId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> viewAllMaterial();

    Material selectByMaterialName(String name);

    Long countByPager(CommonCondition condition);

    List<Material> selectByPager(CommonCondition commonCondition);
}