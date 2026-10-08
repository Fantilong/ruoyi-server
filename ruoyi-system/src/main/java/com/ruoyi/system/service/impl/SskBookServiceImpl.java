package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SskBook;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.system.mapper.SskBookMapper;
import com.ruoyi.system.service.ISskBookService;

/**
 * 图书Service实现类
 * 仅实现基础的增删查改
 *
 * @author trae
 */
@Service
public class SskBookServiceImpl implements ISskBookService
{
    @Autowired
    private SskBookMapper bookMapper;

    /**
     * 分页查询图书列表
     */
    @Override
    public List<SskBookVo> findList(SskBookVo query)
    {
        return bookMapper.selectList(query);
    }

    /**
     * 根据ID查询图书详情
     */
    @Override
    public SskBookVo findById(Long id)
    {
        return bookMapper.selectById(id);
    }

    /**
     * 新增图书
     */
    @Override
    public int create(SskBook entity)
    {
        return bookMapper.insert(entity);
    }

    /**
     * 根据ID修改图书
     */
    @Override
    public int updateById(SskBook entity)
    {
        return bookMapper.updateById(entity);
    }

    /**
     * 根据ID集合删除图书（逻辑删除）
     */
    @Override
    public int deleteByIds(Long[] ids, Long updatedBy)
    {
        return bookMapper.deleteByIds(ids, updatedBy);
    }
}
