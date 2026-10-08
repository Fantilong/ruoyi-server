package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.vo.SskBorrowRecordVo;
import com.ruoyi.system.mapper.SskBorrowRecordMapper;
import com.ruoyi.system.service.ISskBorrowRecordService;

/**
 * 借阅记录Service实现类
 * 仅实现基础的增删查改
 *
 * @author trae
 */
@Service
public class SskBorrowRecordServiceImpl implements ISskBorrowRecordService
{
    @Autowired
    private SskBorrowRecordMapper borrowRecordMapper;

    /**
     * 分页查询借阅记录列表
     */
    @Override
    public List<SskBorrowRecordVo> findList(SskBorrowRecordVo query)
    {
        return borrowRecordMapper.selectList(query);
    }

    /**
     * 根据ID查询借阅记录详情
     */
    @Override
    public SskBorrowRecordVo findById(Long id)
    {
        return borrowRecordMapper.selectById(id);
    }

    /**
     * 新增借阅记录
     */
    @Override
    public int create(SskBorrowRecord entity)
    {
        return borrowRecordMapper.insert(entity);
    }

    /**
     * 根据ID修改借阅记录
     */
    @Override
    public int updateById(SskBorrowRecord entity)
    {
        return borrowRecordMapper.updateById(entity);
    }

    /**
     * 根据ID集合删除借阅记录（逻辑删除）
     */
    @Override
    public int deleteByIds(Long[] ids, Long updatedBy)
    {
        return borrowRecordMapper.deleteByIds(ids, updatedBy);
    }

    /**
     * 还书：更新实际归还时间
     */
    @Override
    public int updateReturnTime(Long id, Long updatedBy)
    {
        return borrowRecordMapper.updateReturnTime(id, updatedBy);
    }
}
