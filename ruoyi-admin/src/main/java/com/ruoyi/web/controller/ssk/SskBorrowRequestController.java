package com.ruoyi.web.controller.ssk;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.vo.SskBorrowRequestVo;
import com.ruoyi.web.biz.ssk.SskBorrowRequestBiz;

/**
 * 借阅申请
 *
 * @author trae
 */
@RestController
@RequestMapping("/ssk/borrowRequest")
public class SskBorrowRequestController extends BaseController
{
    @Autowired
    private SskBorrowRequestBiz borrowRequestBiz;

    /**
     * 分页查询借阅申请列表
     * 支持按书籍名称、状态筛选，status为空表示查询全部
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRequest:list')")
    @GetMapping("/list")
    public TableDataInfo list(SskBorrowRequestVo query)
    {
        startPage();
        List<SskBorrowRequestVo> list = borrowRequestBiz.findList(query);
        return getDataTable(list);
    }

    /**
     * 根据ID获取借阅申请详情
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRequest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(borrowRequestBiz.findById(id));
    }

    /**
     * 同意申请
     * 需要传入囚号和借阅天数，同意后产生一条借阅记录
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRequest:approve')")
    @Log(title = "借阅申请", businessType = BusinessType.UPDATE)
    @PostMapping("/approve/{id}")
    public AjaxResult approve(
        @PathVariable Long id,
        @RequestParam String prisonerNumber,
        @RequestParam(required = false) Integer borrowDays)
    {
        borrowRequestBiz.approve(id, prisonerNumber, borrowDays, getUserId());
        return success();
    }

    /**
     * 拒绝申请
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRequest:reject')")
    @Log(title = "借阅申请", businessType = BusinessType.UPDATE)
    @PostMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id)
    {
        borrowRequestBiz.reject(id, getUserId());
        return success();
    }

    /**
     * 批量删除借阅申请，多个ID以逗号分隔
     */
    @PreAuthorize("@ss.hasPermi('ssk:borrowRequest:remove')")
    @Log(title = "借阅申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        borrowRequestBiz.deleteByIds(ids, getUserId());
        return success();
    }
}
