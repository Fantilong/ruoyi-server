package com.ruoyi.web.biz.ssk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SskBook;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.system.service.ISskBookCategoryService;
import com.ruoyi.system.service.ISskBookService;

/**
 * 图书业务层
 * 负责类目树筛选、封面与图片集同步、类目标题回填等业务逻辑
 *
 * @author trae
 */
@Service
public class SskBookBiz
{
    /** 顶级类目的上级ID */
    private static final Long ROOT_PARENT_ID = 0L;

    @Autowired
    private ISskBookService bookService;

    @Autowired
    private ISskBookCategoryService categoryService;

    /**
     * 分页查询图书列表
     * 如果传入了categoryId，则筛选该类目及其所有子孙类目下的图书
     *
     * @param query 查询参数，包含name/author/shelfCode/categoryId等
     * @return 图书列表
     */
    public List<SskBookVo> findList(SskBookVo query)
    {
        // 类目筛选：将categoryId展开为包含自身及所有子孙ID的集合
        String categoryIdStr = query.getCategoryIds();
        if (categoryIdStr != null && !categoryIdStr.isEmpty())
        {
            try
            {
                Long categoryId = Long.parseLong(categoryIdStr);
                // 0表示全部类目，不做筛选
                if (!ROOT_PARENT_ID.equals(categoryId))
                {
                    List<Long> descendantIds = collectDescendantIds(categoryId);
                    query.setCategoryIdsList(descendantIds);
                }
            }
            catch (NumberFormatException e)
            {
                throw new ServiceException("类目ID格式不正确");
            }
        }
        // 清空categoryIds避免误入SQL条件，实际筛选使用categoryIdsList
        query.setCategoryIds(null);
        List<SskBookVo> list = bookService.findList(query);
        // 回填类目标题用于展示
        fillCategoryTitles(list);
        return list;
    }

    /**
     * 根据ID查询图书详情
     *
     * @param id 图书ID
     * @return 图书信息
     */
    public SskBookVo findById(Long id)
    {
        SskBookVo vo = bookService.findById(id);
        if (vo == null)
        {
            throw new ServiceException("图书不存在或已被删除");
        }
        // 回填类目标题
        fillCategoryTitles(new ArrayList<>(List.of(vo)));
        return vo;
    }

    /**
     * 新增图书
     * 封面默认取图片集的第一张
     *
     * @param entity 图书信息
     * @param userId 当前登录用户ID
     */
    public void create(SskBook entity, Long userId)
    {
        // 同步封面：取图片集第一张
        syncCover(entity);
        entity.setId(null);
        entity.setCreatedBy(userId);
        entity.setCreatedAt(new Date());
        bookService.create(entity);
    }

    /**
     * 修改图书
     * 封面默认取图片集的第一张
     *
     * @param entity 图书信息
     * @param userId 当前登录用户ID
     */
    public void update(SskBook entity, Long userId)
    {
        // 校验图书存在
        if (bookService.findById(entity.getId()) == null)
        {
            throw new ServiceException("图书不存在或已被删除");
        }
        // 同步封面：取图片集第一张
        syncCover(entity);
        entity.setUpdatedBy(userId);
        entity.setUpdatedAt(new Date());
        bookService.updateById(entity);
    }

    /**
     * 批量删除图书（逻辑删除）
     *
     * @param ids 图书ID数组
     * @param userId 当前登录用户ID
     */
    public void deleteByIds(Long[] ids, Long userId)
    {
        if (ids == null || ids.length == 0)
        {
            throw new ServiceException("请选择需要删除的图书");
        }
        bookService.deleteByIds(ids, userId);
    }

    /**
     * 同步封面：取图片集的第一张图作为封面
     * 如果图片集为空，则封面也置空
     *
     * @param entity 图书信息
     */
    private void syncCover(SskBook entity)
    {
        String images = entity.getImages();
        if (images != null && !images.isEmpty())
        {
            // 图片集以逗号分隔，取第一张作为封面
            String firstImage = images.split(",")[0];
            entity.setCover(firstImage);
        }
        else
        {
            entity.setCover(null);
        }
    }

    /**
     * 回填类目标题，用于列表展示
     *
     * @param list 图书列表
     */
    private void fillCategoryTitles(List<SskBookVo> list)
    {
        if (list == null || list.isEmpty())
        {
            return;
        }
        // 构建类目ID到标题的映射
        Map<Long, String> titleMap = new HashMap<>();
        for (SskBookCategory category : categoryService.findList())
        {
            titleMap.put(category.getId(), category.getTitle());
        }
        // 逐条回填类目标题
        for (SskBookVo vo : list)
        {
            String categoryIds = vo.getCategoryIds();
            if (categoryIds == null || categoryIds.isEmpty())
            {
                continue;
            }
            List<String> titles = new ArrayList<>();
            for (String idStr : categoryIds.split(","))
            {
                String title = titleMap.get(Long.parseLong(idStr.trim()));
                if (title != null)
                {
                    titles.add(title);
                }
            }
            vo.setCategoryTitles(String.join("，", titles));
        }
    }

    /**
     * 收集指定类目的所有子孙节点ID（包含自身）
     * 用于按类目筛选图书时，展开所有子类目
     *
     * @param id 类目ID
     * @return 包含自身及所有子孙节点ID的列表
     */
    private List<Long> collectDescendantIds(Long id)
    {
        // 按上级ID分组，便于逐层向下收集
        Map<Long, List<Long>> childrenMap = new HashMap<>();
        for (SskBookCategory entity : categoryService.findList())
        {
            Long pid = entity.getParentId() == null ? ROOT_PARENT_ID : entity.getParentId();
            childrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(entity.getId());
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
