package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SskBookCategory;

/**
 * 图书分类Service接口
 * 仅提供基础的增删查改，业务逻辑由biz层处理
 *
 * @author trae
 */
public interface ISskBookCategoryService
{
    /**
     * 新增类目
     *
     * @param entity 类目信息
     * @return 影响行数
     */
    int create(SskBookCategory entity);

    /**
     * 根据ID集合删除类目（逻辑删除）
     *
     * @param ids 类目ID数组
     * @param updatedBy 更新人
     * @return 影响行数
     */
    int deleteByIds(Long[] ids, Long updatedBy);

    /**
     * 根据ID查询类目
     *
     * @param id 类目ID
     * @return 类目信息
     */
    SskBookCategory findById(Long id);

    /**
     * 查询所有未删除的类目
     *
     * @return 类目列表
     */
    List<SskBookCategory> findList();

    /**
     * 根据ID修改类目
     *
     * @param entity 类目信息
     * @return 影响行数
     */
    int updateById(SskBookCategory entity);

    /**
     * 统计同一上级下指定标题的类目数量
     *
     * @param parentId 上级ID
     * @param title 标题
     * @param excludeId 需要排除的类目ID，可为空
     * @return 数量
     */
    int countByParentIdAndTitle(Long parentId, String title, Long excludeId);

    /**
     * 统计指定类目的未删除子类目数量
     *
     * @param parentId 上级ID
     * @return 子类目数量
     */
    int countChildrenByParentId(Long parentId);
}
