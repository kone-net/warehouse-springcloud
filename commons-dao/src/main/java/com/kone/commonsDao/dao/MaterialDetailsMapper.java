package com.kone.commonsDao.dao;

import com.kone.utils.bo.MaterialByDayBO;
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

    /**
     * 通过时间段查看材料的出库统计
     *    查询该时间段，出库该材料的总量
     * @param condition 通过group by统计的总数，和该材料对应的id，通过id查询材料的详细
     * @return
     */
    List<MaterialByDayBO> viewMaterialOutByDay(CommonCondition condition);

    /**
     * 通过时间段查看材料的出库情况 总条数
     * @param condition
     * @return
     */
    Long getMaterialOutByDaySum(CommonCondition condition);
}