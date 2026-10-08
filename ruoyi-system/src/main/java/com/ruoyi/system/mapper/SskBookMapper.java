package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SskBook;
import com.ruoyi.system.domain.vo.SskBookVo;

/**
 * 图书Mapper接口
 *
 * @author trae
 */
public interface SskBookMapper
{
    /**
     * 分页查询图书列表（关联用户表获取创建人/更新人姓名）
     *
     * @param query 查询参数
     * @return 图书列表
     */
    List<SskBookVo> selectList(SskBookVo query);

    /**
     * 根据ID查询图书详情（关联用户表）
     *
     * @param id 图书ID
     * @return 图书信息
     */
    SskBookVo selectById(Long id);

    /**
     * 新增图书
     *
     * @param entity 图书信息
     * @return 影响行数
     */
    int insert(SskBook entity);

    /**
     * 根据ID修改图书
     *
     * @param entity 图书信息
     * @return 影响行数
     */
    int updateById(SskBook entity);

    /**
     * 根据ID集合逻辑删除图书
     *
     * @param ids 图书ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] ids, @Param("updatedBy") Long updatedBy);
}
