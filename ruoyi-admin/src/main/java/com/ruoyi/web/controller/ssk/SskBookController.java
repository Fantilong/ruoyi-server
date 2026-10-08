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
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SskBook;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.web.biz.ssk.SskBookBiz;

/**
 * 图书管理
 *
 * @author trae
 */
@RestController
@RequestMapping("/ssk/book")
public class SskBookController extends BaseController
{
    @Autowired
    private SskBookBiz bookBiz;

    /**
     * 分页查询图书列表
     * 支持按类目、书架号、书籍名称、作者筛选
     * categoryId为0或空时表示查询全部
     */
    @PreAuthorize("@ss.hasPermi('ssk:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(SskBookVo query)
    {
        startPage();
        List<SskBookVo> list = bookBiz.findList(query);
        return getDataTable(list);
    }

    /**
     * 根据ID获取图书详情
     */
    @PreAuthorize("@ss.hasPermi('ssk:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(bookBiz.findById(id));
    }

    /**
     * 新增图书
     */
    @PreAuthorize("@ss.hasPermi('ssk:book:add')")
    @Log(title = "图书管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SskBook entity)
    {
        bookBiz.create(entity, getUserId());
        return success();
    }

    /**
     * 修改图书
     */
    @PreAuthorize("@ss.hasPermi('ssk:book:edit')")
    @Log(title = "图书管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SskBook entity)
    {
        bookBiz.update(entity, getUserId());
        return success();
    }

    /**
     * 批量删除图书，多个ID以逗号分隔
     */
    @PreAuthorize("@ss.hasPermi('ssk:book:remove')")
    @Log(title = "图书管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        bookBiz.deleteByIds(ids, getUserId());
        return success();
    }
}
