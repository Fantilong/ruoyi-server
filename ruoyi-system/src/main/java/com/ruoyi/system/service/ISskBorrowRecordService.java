package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.vo.SskBorrowRecordVo;

/**
 * 借阅记录Service接口
 * 仅提供基础的增删查改
 *
 * @author trae
 */
public interface ISskBorrowRecordService
{
    /**
     * 分页查询借阅记录列表
     *
     * @param query 查询参数
     * @return 借阅记录列表
     */
    List<SskBorrowRecordVo> findList(SskBorrowRecordVo query);

    /**
     * 根据ID查询借阅记录详情
     *
     * @param id 记录ID
     * @return 借阅记录信息
     */
    SskBorrowRecordVo findById(Long id);

    /**
     * 新增借阅记录
     *
     * @param entity 借阅记录
     * @return 影响行数
     */
    int create(SskBorrowRecord entity);

    /**
     * 根据ID修改借阅记录
     *
     * @param entity 借阅记录
     * @return 影响行数
     */
    int updateById(SskBorrowRecord entity);

    /**
     * 根据ID集合删除借阅记录（逻辑删除）
     *
     * @param ids 记录ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(Long[] ids, Long updatedBy);

    /**
     * 还书：更新实际归还时间
     *
     * @param id 记录ID
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int updateReturnTime(Long id, Long updatedBy);
}
