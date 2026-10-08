package com.ruoyi.client.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.system.service.ISskBookCategoryService;
import com.ruoyi.system.service.ISskBookService;

/**
 * 终端-图书浏览业务层
 * 复用ruoyi-system中的基础Service，仅承载终端侧业务逻辑
 *
 * @author trae
 */
@Service
public class ClientBookBiz
{
    /** 顶级类目的上级ID */
    private static final Long ROOT_PARENT_ID = 0L;

    @Autowired
    private ISskBookService bookService;

    @Autowired
    private ISskBookCategoryService categoryService;

    /**
     * 分页查询图书列表
     * 传入categoryId时，筛选该类目及其所有子孙类目下的图书
     *
     * @param query 查询参数（name书籍名称、categoryIds类目ID）
     * @return 图书列表
     */
    public List<SskBookVo> findBookList(SskBookVo query)
    {
        Long categoryId = parseCategoryId(query.getCategoryIds());
        // 0或空表示全部类目，不做筛选
        if (categoryId != null && !ROOT_PARENT_ID.equals(categoryId))
        {
            query.setCategoryIdsList(collectDescendantIds(categoryId));
        }
        // 清空categoryIds，实际筛选走categoryIdsList，避免误入SQL条件
        query.setCategoryIds(null);
        return bookService.findList(query);
    }

    /**
     * 查询图书详情
     *
     * @param id 图书ID
     * @return 图书信息
     */
    public SskBookVo findBookById(Long id)
    {
        SskBookVo vo = bookService.findById(id);
        if (vo == null)
        {
            throw new ServiceException("图书不存在或已下架");
        }
        return vo;
    }

    /**
     * 查询全部可用类目（平铺列表，终端前端自行构建树）
     *
     * @return 类目列表
     */
    public List<SskBookCategory> findCategoryList()
    {
        return categoryService.findList();
    }

    /**
     * 解析类目ID字符串
     */
    private Long parseCategoryId(String categoryIdStr)
    {
        if (categoryIdStr == null || categoryIdStr.isEmpty())
        {
            return null;
        }
        try
        {
            return Long.parseLong(categoryIdStr);
        }
        catch (NumberFormatException e)
        {
            throw new ServiceException("类目ID格式不正确");
        }
    }

    /**
     * 收集指定类目的所有子孙节点ID（包含自身）
     *
     * @param id 类目ID
     * @return 包含自身及所有子孙节点ID的列表
     */
    private List<Long> collectDescendantIds(Long id)
    {
        // 按上级ID分组，便于逐层向下收集
        Map<Long, List<Long>> childrenMap = new HashMap<>();
        for (SskBookCategory category : categoryService.findList())
        {
            Long pid = category.getParentId() == null ? ROOT_PARENT_ID : category.getParentId();
            childrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(category.getId());
        }
        Set<Long> result = new HashSet<>();
        result.add(id);
        collectDescendantIds(id, childrenMap, result);
        return new ArrayList<>(result);
    }

    /**
     * 递归收集子孙节点ID
     */
    private void collectDescendantIds(Long id, Map<Long, List<Long>> childrenMap, Set<Long> result)
    {
        List<Long> children = childrenMap.get(id);
        if (children == null)
        {
            return;
        }
        for (Long childId : children)
        {
            // 已收集过的节点跳过，防止脏数据导致死循环
            if (result.add(childId))
            {
                collectDescendantIds(childId, childrenMap, result);
            }
        }
    }
}
