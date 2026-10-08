package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SskBorrowRequest;
import com.ruoyi.system.domain.vo.SskBorrowRequestVo;

/**
 * 借阅申请Mapper接口
 *
 * @author trae
 */
public interface SskBorrowRequestMapper
{
    /**
     * 分页查询借阅申请列表（关联图书表获取书籍信息及书架号）
     *
     * @param query 查询参数（书籍名称、状态）
     * @return 借阅申请列表
     */
    List<SskBorrowRequestVo> selectList(SskBorrowRequestVo query);

    /**
     * 根据ID查询借阅申请详情（关联图书表）
     *
     * @param id 申请ID
     * @return 借阅申请详情
     */
    SskBorrowRequestVo selectById(Long id);

    /**
     * 新增借阅申请
     *
     * @param entity 借阅申请
     * @return 影响行数
     */
    int insert(SskBorrowRequest entity);

    /**
     * 根据ID修改借阅申请（主要用于更新状态）
     *
     * @param entity 借阅申请
     * @return 影响行数
     */
    int updateById(SskBorrowRequest entity);

    /**
     * 根据ID集合逻辑删除借阅申请
     *
     * @param ids 申请ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] ids, @Param("updatedBy") Long updatedBy);
}
