package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.mapper.SskBookCategoryMapper;
import com.ruoyi.system.service.ISskBookCategoryService;

/**
 * 图书分类Service实现类
 * 仅实现基础的增删查改
 *
 * @author trae
 */
@Service
public class SskBookCategoryServiceImpl implements ISskBookCategoryService
{
    @Autowired
    private SskBookCategoryMapper bookCategoryMapper;

    /**
     * 新增类目
     */
    @Override
    public int create(SskBookCategory entity)
    {
        return bookCategoryMapper.insert(entity);
    }

    /**
     * 根据ID集合删除类目（逻辑删除）
     */
    @Override
    public int deleteByIds(Long[] ids, Long updatedBy)
    {
        return bookCategoryMapper.deleteByIds(ids, updatedBy);
    }

    /**
     * 根据ID查询类目
     */
    @Override
    public SskBookCategory findById(Long id)
    {
        return bookCategoryMapper.selectById(id);
    }

    /**
     * 查询所有未删除的类目
     */
    @Override
    public List<SskBookCategory> findList()
    {
        return bookCategoryMapper.selectList();
    }

    /**
     * 根据ID修改类目
     */
    @Override
    public int updateById(SskBookCategory entity)
    {
        return bookCategoryMapper.updateById(entity);
    }

    /**
     * 统计同一上级下指定标题的类目数量
     */
    @Override
    public int countByParentIdAndTitle(Long parentId, String title, Long excludeId)
    {
        return bookCategoryMapper.countByParentIdAndTitle(parentId, title, excludeId);
    }

    /**
     * 统计指定类目的未删除子类目数量
     */
    @Override
    public int countChildrenByParentId(Long parentId)
    {
        return bookCategoryMapper.countChildrenByParentId(parentId);
    }
}
