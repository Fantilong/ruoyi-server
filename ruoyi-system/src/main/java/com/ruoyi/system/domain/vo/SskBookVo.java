package com.ruoyi.system.domain.vo;

import java.util.List;
import com.ruoyi.system.domain.SskBook;

/**
 * 图书VO
 * 在实体基础上扩展展示字段和查询参数
 *
 * @author trae
 */
public class SskBookVo extends SskBook
{
    private static final long serialVersionUID = 1L;

    /** 创建人姓名 */
    private String createdByName;

    /** 更新人姓名 */
    private String updatedByName;

    /** 类目ID集合（查询参数，用于按类目筛选） */
    private List<Long> categoryIdsList;

    /** 类目标题集（展示用，逗号分隔） */
    private String categoryTitles;

    public String getCreatedByName()
    {
        return createdByName;
    }

    public void setCreatedByName(String createdByName)
    {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName()
    {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName)
    {
        this.updatedByName = updatedByName;
    }

    public List<Long> getCategoryIdsList()
    {
        return categoryIdsList;
    }

    public void setCategoryIdsList(List<Long> categoryIdsList)
    {
        this.categoryIdsList = categoryIdsList;
    }

    public String getCategoryTitles()
    {
        return categoryTitles;
    }

    public void setCategoryTitles(String categoryTitles)
    {
        this.categoryTitles = categoryTitles;
    }
}
