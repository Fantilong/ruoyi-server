package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SskBorrowRequest;
import com.ruoyi.system.domain.vo.SskBorrowRequestVo;
import com.ruoyi.system.mapper.SskBorrowRequestMapper;
import com.ruoyi.system.service.ISskBorrowRequestService;

/**
 * 借阅申请Service实现类
 * 仅实现基础的增删查改
 *
 * @author trae
 */
@Service
public class SskBorrowRequestServiceImpl implements ISskBorrowRequestService
{
    @Autowired
    private SskBorrowRequestMapper borrowRequestMapper;

    /**
     * 分页查询借阅申请列表
     */
    @Override
    public List<SskBorrowRequestVo> findList(SskBorrowRequestVo query)
    {
        return borrowRequestMapper.selectList(query);
    }

    /**
     * 根据ID查询借阅申请详情
     */
    @Override
    public SskBorrowRequestVo findById(Long id)
    {
        return borrowRequestMapper.selectById(id);
    }

    /**
     * 新增借阅申请
     */
    @Override
    public int create(SskBorrowRequest entity)
    {
        return borrowRequestMapper.insert(entity);
    }

    /**
     * 根据ID修改借阅申请
     */
    @Override
    public int updateById(SskBorrowRequest entity)
    {
        return borrowRequestMapper.updateById(entity);
    }

    /**
     * 根据ID集合删除借阅申请（逻辑删除）
     */
    @Override
    public int deleteByIds(Long[] ids, Long updatedBy)
    {
        return borrowRequestMapper.deleteByIds(ids, updatedBy);
    }
}
