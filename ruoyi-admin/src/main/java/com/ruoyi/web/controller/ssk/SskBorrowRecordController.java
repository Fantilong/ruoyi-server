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
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.vo.SskBorrowRecordVo;
import com.ruoyi.web.biz.ssk.SskBorrowRecordBiz;

/**
 * 借阅记录
 *
 * @author trae
 */
@RestController
@RequestMapping("/ssk/borrowRecord")
public class SskBorrowRecordController extends BaseController
{
    @Autowired
    private SskBorrowRecordBiz borrowRecordBiz;

    /**
     * 分页查询借阅记录列表
     * 支持按囚号、书籍名称筛选
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SskBorrowRecordVo query)
    {
        startPage();
        List<SskBorrowRecordVo> list = borrowRecordBiz.findList(query);
        return getDataTable(list);
    }

    /**
     * 根据ID获取借阅记录详情
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(borrowRecordBiz.findById(id));
    }

    /**
     * 新增借阅记录
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:add')")
    @Log(title = "借阅记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SskBorrowRecord entity)
    {
        borrowRecordBiz.create(entity, getUserId());
        return success();
    }

    /**
     * 修改借阅记录
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:edit')")
    @Log(title = "借阅记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SskBorrowRecord entity)
    {
        borrowRecordBiz.update(entity, getUserId());
        return success();
    }

    /**
     * 批量删除借阅记录，多个ID以逗号分隔
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:remove')")
    @Log(title = "借阅记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        borrowRecordBiz.deleteByIds(ids, getUserId());
        return success();
    }

    /**
     * 还书操作，将实际归还时间设为当前时间
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRecord:return')")
    @Log(title = "借阅记录", businessType = BusinessType.UPDATE)
    @PutMapping("/return/{id}")
    public AjaxResult returnBook(@PathVariable Long id)
    {
        borrowRecordBiz.returnBook(id, getUserId());
        return success();
    }
}
