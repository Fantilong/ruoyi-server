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
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.domain.vo.SskBookCategoryVo;
import com.ruoyi.system.service.ISskBookCategoryService;

/**
 * 图书分类业务层
 * 负责类目树构建、唯一性校验、删除校验等业务逻辑
 *
 * @author trae
 */
@Service
public class SskBookCategoryBiz
{
    /** 顶级类目的上级ID */
    private static final Long ROOT_PARENT_ID = 0L;

    @Autowired
    private ISskBookCategoryService bookCategoryService;

    /**
     * 查询类目树（一次性返回全部）
     *
     * @return 类目树列表
     */
    public List<SskBookCategoryVo> listTree()
    {
        List<SskBookCategory> list = bookCategoryService.findList();
        return buildTree(list);
    }

    /**
     * 根据ID查询类目详情
     *
     * @param id 类目ID
     * @return 类目信息
     */
    public SskBookCategory getById(Long id)
    {
        SskBookCategory entity = bookCategoryService.findById(id);
        if (entity == null)
        {
            throw new ServiceException("类目不存在或已被删除");
        }
        return entity;
    }

    /**
     * 新增类目
     *
     * @param entity 类目信息
     * @param userId 当前登录用户ID
     */
    public void create(SskBookCategory entity, Long userId)
    {
        // 上级ID为空时默认为顶级类目
        Long parentId = entity.getParentId() == null ? ROOT_PARENT_ID : entity.getParentId();
        entity.setParentId(parentId);
        // 非顶级类目需校验上级是否存在
        if (!ROOT_PARENT_ID.equals(parentId))
        {
            getById(parentId);
        }
        // 同一上级下标题不允许重复
        if (bookCategoryService.countByParentIdAndTitle(parentId, entity.getTitle(), null) > 0)
        {
            throw new ServiceException("同一上级下已存在标题为【" + entity.getTitle() + "】的类目");
        }
        entity.setId(null);
        entity.setCreatedBy(userId);
        entity.setCreatedAt(new Date());
        bookCategoryService.create(entity);
    }

    /**
     * 修改类目
     *
     * @param entity 类目信息
     * @param userId 当前登录用户ID
     */
    public void update(SskBookCategory entity, Long userId)
    {
        // 校验类目存在
        getById(entity.getId());
        Long parentId = entity.getParentId() == null ? ROOT_PARENT_ID : entity.getParentId();
        entity.setParentId(parentId);
        // 上级不能是自身
        if (parentId.equals(entity.getId()))
        {
            throw new ServiceException("上级类目不能是自身");
        }
        // 上级不能是自身的子孙节点，避免产生循环
        if (collectDescendantIds(entity.getId()).contains(parentId))
        {
            throw new ServiceException("上级类目不能选择自身的子类目");
        }
        // 非顶级类目需校验上级是否存在
        if (!ROOT_PARENT_ID.equals(parentId))
        {
            getById(parentId);
        }
        // 同一上级下标题不允许重复（排除自身）
        if (bookCategoryService.countByParentIdAndTitle(parentId, entity.getTitle(), entity.getId()) > 0)
        {
            throw new ServiceException("同一上级下已存在标题为【" + entity.getTitle() + "】的类目");
        }
        entity.setUpdatedBy(userId);
        entity.setUpdatedAt(new Date());
        bookCategoryService.updateById(entity);
    }

    /**
     * 批量删除类目（逻辑删除）
     * 存在子类目的节点不允许删除，防止误删整棵子树
     *
     * @param ids 类目ID数组
     * @param userId 当前登录用户ID
     */
    public void deleteByIds(Long[] ids, Long userId)
    {
        if (ids == null || ids.length == 0)
        {
            throw new ServiceException("请选择需要删除的类目");
        }
        for (Long id : ids)
        {
            SskBookCategory entity = bookCategoryService.findById(id);
            // 已删除或不存在的节点直接跳过
            if (entity == null)
            {
                continue;
            }
            if (bookCategoryService.countChildrenByParentId(id) > 0)
            {
                throw new ServiceException("类目【" + entity.getTitle() + "】下存在子类目，不允许删除");
            }
        }
        bookCategoryService.deleteByIds(ids, userId);
    }

    /**
     * 将平铺列表构建为树结构
     * 找不到上级的孤儿节点归入顶级，保证数据不丢失
     *
     * @param list 类目平铺列表
     * @return 类目树
     */
    private List<SskBookCategoryVo> buildTree(List<SskBookCategory> list)
    {
        // 先按ID建立索引，便于快速挂载子节点
        Map<Long, SskBookCategoryVo> voMap = new HashMap<>();
        for (SskBookCategory entity : list)
        {
            SskBookCategoryVo vo = new SskBookCategoryVo();
            BeanUtils.copyBeanProp(vo, entity);
            vo.setChildren(new ArrayList<>());
            voMap.put(vo.getId(), vo);
        }
        List<SskBookCategoryVo> roots = new ArrayList<>();
        for (SskBookCategoryVo vo : voMap.values())
        {
            SskBookCategoryVo parent = vo.getParentId() == null ? null : voMap.get(vo.getParentId());
            if (parent == null)
            {
                roots.add(vo);
            }
            else
            {
                parent.getChildren().add(vo);
            }
        }
        return roots;
    }

    /**
     * 收集指定类目的所有子孙节点ID
     *
     * @param id 类目ID
     * @return 子孙节点ID集合
     */
    private Set<Long> collectDescendantIds(Long id)
    {
        // 按上级ID分组，便于逐层向下收集
        Map<Long, List<Long>> childrenMap = new HashMap<>();
        for (SskBookCategory entity : bookCategoryService.findList())
        {
            Long pid = entity.getParentId() == null ? ROOT_PARENT_ID : entity.getParentId();
            childrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(entity.getId());
        }
        Set<Long> result = new HashSet<>();
        collectDescendantIds(id, childrenMap, result);
        return result;
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
