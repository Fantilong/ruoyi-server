package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SskBook;
import com.ruoyi.system.domain.vo.SskBookVo;

/**
 * 图书Service接口
 * 仅提供基础的增删查改
 *
 * @author trae
 */
public interface ISskBookService
{
    /**
     * 分页查询图书列表
     *
     * @param query 查询参数
     * @return 图书列表
     */
    List<SskBookVo> findList(SskBookVo query);

    /**
     * 根据ID查询图书详情
     *
     * @param id 图书ID
     * @return 图书信息
     */
    SskBookVo findById(Long id);

    /**
     * 新增图书
     *
     * @param entity 图书信息
     * @return 影响行数
     */
    int create(SskBook entity);

    /**
     * 根据ID修改图书
     *
     * @param entity 图书信息
     * @return 影响行数
     */
    int updateById(SskBook entity);

    /**
     * 根据ID集合删除图书（逻辑删除）
     *
     * @param ids 图书ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(Long[] ids, Long updatedBy);
}
