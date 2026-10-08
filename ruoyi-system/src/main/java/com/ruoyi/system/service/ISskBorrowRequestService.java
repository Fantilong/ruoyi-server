package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SskBorrowRequest;
import com.ruoyi.system.domain.vo.SskBorrowRequestVo;

/**
 * 借阅申请Service接口
 * 仅提供基础的增删查改
 *
 * @author trae
 */
public interface ISskBorrowRequestService
{
    /**
     * 分页查询借阅申请列表
     *
     * @param query 查询参数
     * @return 借阅申请列表
     */
    List<SskBorrowRequestVo> findList(SskBorrowRequestVo query);

    /**
     * 根据ID查询借阅申请详情
     *
     * @param id 申请ID
     * @return 借阅申请信息
     */
    SskBorrowRequestVo findById(Long id);

    /**
     * 新增借阅申请
     *
     * @param entity 借阅申请
     * @return 影响行数
     */
    int create(SskBorrowRequest entity);

    /**
     * 根据ID修改借阅申请
     *
     * @param entity 借阅申请
     * @return 影响行数
     */
    int updateById(SskBorrowRequest entity);

    /**
     * 根据ID集合删除借阅申请（逻辑删除）
     *
     * @param ids 申请ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(Long[] ids, Long updatedBy);
}
