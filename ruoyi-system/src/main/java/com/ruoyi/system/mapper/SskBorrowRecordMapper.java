package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.vo.SskBorrowRecordVo;

/**
 * 借阅记录Mapper接口
 *
 * @author trae
 */
public interface SskBorrowRecordMapper
{
    /**
     * 分页查询借阅记录列表（关联图书表获取书籍信息及书架号）
     *
     * @param query 查询参数（囚号、书籍名称）
     * @return 借阅记录列表
     */
    List<SskBorrowRecordVo> selectList(SskBorrowRecordVo query);

    /**
     * 根据ID查询借阅记录详情（关联图书表）
     *
     * @param id 记录ID
     * @return 借阅记录详情
     */
    SskBorrowRecordVo selectById(Long id);

    /**
     * 新增借阅记录
     *
     * @param entity 借阅记录
     * @return 影响行数
     */
    int insert(SskBorrowRecord entity);

    /**
     * 根据ID修改借阅记录
     *
     * @param entity 借阅记录
     * @return 影响行数
     */
    int updateById(SskBorrowRecord entity);

    /**
     * 根据ID集合逻辑删除借阅记录
     *
     * @param ids 记录ID数组
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] ids, @Param("updatedBy") Long updatedBy);

    /**
     * 还书：更新实际归还时间
     *
     * @param id 记录ID
     * @param updatedBy 更新人ID
     * @return 影响行数
     */
    int updateReturnTime(@Param("id") Long id, @Param("updatedBy") Long updatedBy);
}
