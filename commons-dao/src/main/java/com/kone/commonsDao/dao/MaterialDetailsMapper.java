package com.kone.commonsDao.dao;

import com.kone.utils.conditions.CommonCondition;
import com.kone.utils.dto.MaterialDetailsDTO;
import com.kone.utils.entity.MaterialDetails;

import java.util.List;

public interface MaterialDetailsMapper {
    int deleteByPrimaryKey(Long materialDetailsId);

    int insert(MaterialDetails record);

    int insertSelective(MaterialDetails record);

    MaterialDetails selectByPrimaryKey(Long materialDetailsId);

    int updateByPrimaryKeySelective(MaterialDetails record);

    int updateByPrimaryKey(MaterialDetails record);

    List<MaterialDetails> selectByMaterialId(MaterialDetailsDTO dto);

    Long countByPager(CommonCondition condition);
}