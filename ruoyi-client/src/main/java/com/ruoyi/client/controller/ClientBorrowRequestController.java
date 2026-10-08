package com.ruoyi.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.client.biz.ClientBorrowRequestBiz;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SskBorrowRequest;

/**
 * 终端-借阅申请接口
 * 类级@Anonymous：终端供囚犯使用，接口免管理后台登录鉴权
 *
 * @author trae
 */
@Anonymous
@RestController
@RequestMapping("/client/borrowRequest")
public class ClientBorrowRequestController extends BaseController
{
    @Autowired
    private ClientBorrowRequestBiz borrowRequestBiz;

    /**
     * 发起借阅申请
     * 仅需传入bookId，申请状态默认待处理，由管理后台审核
     */
    @PostMapping
    public AjaxResult add(@RequestBody SskBorrowRequest request)
    {
        borrowRequestBiz.create(request.getBookId());
        return success();
    }
}
