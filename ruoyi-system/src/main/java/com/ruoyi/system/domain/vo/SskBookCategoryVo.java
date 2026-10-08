package com.ruoyi.system.domain.vo;

import java.util.List;
import com.ruoyi.system.domain.SskBookCategory;

/**
 * 图书分类树形VO
 * 在实体基础上扩展子节点字段，用于树结构展示
 *
 * @author trae
 */
public class SskBookCategoryVo extends SskBookCategory
{
    private static final long serialVersionUID = 1L;

    /** 子类目列表 */
    private List<SskBookCategoryVo> children;

    public List<SskBookCategoryVo> getChildren()
    {
        return children;
    }

    public void setChildren(List<SskBookCategoryVo> children)
    {
        this.children = children;
    }
}
