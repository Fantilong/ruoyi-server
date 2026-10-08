package com.ruoyi.client.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SskBookCategory;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.client.biz.ClientBookBiz;

/**
 * 终端-图书浏览接口
 * 类级@Anonymous：终端供囚犯使用，接口免管理后台登录鉴权
 * 后续如需囚犯身份体系，可在此模块新增终端专属的登录/鉴权逻辑
 *
 * @author trae
 */
@Anonymous
@RestController
@RequestMapping("/client/book")
public class ClientBookController extends BaseController
{
    @Autowired
    private ClientBookBiz bookBiz;

    /**
     * 分页查询图书列表
     * 支持按书籍名称模糊搜索、按类目（含子孙类目）筛选
     */
    @GetMapping("/list")
    public TableDataInfo list(SskBookVo query)
    {
        startPage();
        List<SskBookVo> list = bookBiz.findBookList(query);
        return getDataTable(list);
    }

    /**
     * 根据ID获取图书详情
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(bookBiz.findBookById(id));
    }

    /**
     * 查询全部图书类目（平铺列表，前端自行构建树）
     */
    @GetMapping("/category/list")
    public AjaxResult categoryList()
    {
        List<SskBookCategory> list = bookBiz.findCategoryList();
        return success(list);
    }
}
