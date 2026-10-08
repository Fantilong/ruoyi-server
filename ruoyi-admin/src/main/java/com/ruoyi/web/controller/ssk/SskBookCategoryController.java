package com.ruoyi.web.controller.ssk;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.domain.vo.SskBookCategoryVo;
import com.ruoyi.web.biz.ssk.SskBookCategoryBiz;

/**
 * 图书分类
 *
 * @author trae
 */
@RestController
@RequestMapping("/ssk/bookCategory")
public class SskBookCategoryController extends BaseController
{
    @Autowired
    private SskBookCategoryBiz bookCategoryBiz;

    /**
     * 获取类目树（一次性返回全部）
     */
    @PreAuthorize("@ss.hasPermi('ssk:bookCategory:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<SskBookCategoryVo> tree = bookCategoryBiz.listTree();
        return success(tree);
    }

    /**
     * 根据ID获取类目详情
     */
    @PreAuthorize("@ss.hasPermi('ssk:bookCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(bookCategoryBiz.getById(id));
    }

    /**
     * 新增类目
     */
    @PreAuthorize("@ss.hasPermi('ssk:bookCategory:add')")
    @Log(title = "图书分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SskBookCategory entity)
    {
        bookCategoryBiz.create(entity, getUserId());
        return success();
    }

    /**
     * 修改类目
     */
    @PreAuthorize("@ss.hasPermi('ssk:bookCategory:edit')")
    @Log(title = "图书分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SskBookCategory entity)
    {
        bookCategoryBiz.update(entity, getUserId());
        return success();
    }

    /**
     * 批量删除类目，多个ID以逗号分隔
     */
    @PreAuthorize("@ss.hasPermi('ssk:bookCategory:remove')")
    @Log(title = "图书分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        bookCategoryBiz.deleteByIds(ids, getUserId());
        return success();
    }
}
