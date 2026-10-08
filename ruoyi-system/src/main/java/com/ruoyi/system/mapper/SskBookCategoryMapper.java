package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SskBookCategory;

/**
 * 图书分类Mapper接口
 *
 * @author trae
 */
public interface SskBookCategoryMapper
{
    /**
     * 查询所有未删除的类目
     *
     * @return 类目列表
     */
    List<SskBookCategory> selectList();

    /**
     * 根据ID查询类目
     *
     * @param id 类目ID
     * @return 类目信息
     */
    SskBookCategory selectById(Long id);

    /**
     * 统计同一上级下指定标题的类目数量（用于标题唯一性校验）
     *
     * @param parentId 上级ID
     * @param title 标题
     * @param excludeId 需要排除的类目ID（修改时排除自身），可为空
     * @return 数量
     */
    int countByParentIdAndTitle(@Param("parentId") Long parentId, @Param("title") String title, @Param("excludeId") Long excludeId);

    /**
     * 统计指定类目的未删除子类目数量
     *
     * @param parentId 上级ID
     * @return 子类目数量
     */
    int countChildrenByParentId(Long parentId);

    /**
     * 新增类目
     *
     * @param entity 类目信息
     * @return 影响行数
     */
    int insert(SskBookCategory entity);

    /**
     * 根据ID修改类目
     *
     * @param entity 类目信息
     * @return 影响行数
     */
    int updateById(SskBookCategory entity);

    /**
     * 根据ID集合逻辑删除类目
     *
     * @param ids 类目ID数组
     * @param updatedBy 更新人
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] ids, @Param("updatedBy") Long updatedBy);
}
