package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.entity.MaterialIn;

import java.util.List;

public interface MaterialInMapper {
    int deleteByPrimaryKey(Long materialInId);

    int insert(MaterialIn record);

    int insertSelective(MaterialIn record);

    MaterialIn selectByPrimaryKey(Long materialInId);

    int updateByPrimaryKeySelective(MaterialIn record);

    int updateByPrimaryKey(MaterialIn record);

    Long countByPager(CommonCondition condition);

    List<MaterialIn> selectByPager(CommonCondition condition);

    Float getSum(CommonCondition condition);
}