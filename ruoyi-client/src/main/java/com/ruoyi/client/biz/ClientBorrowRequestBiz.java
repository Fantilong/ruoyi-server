package com.ruoyi.client.biz;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SskBorrowRequest;
import com.ruoyi.system.domain.vo.SskBookVo;
import com.ruoyi.system.enums.SskBorrowRequestStatus;
import com.ruoyi.system.service.ISskBookService;
import com.ruoyi.system.service.ISskBorrowRequestService;

/**
 * 终端-借阅申请业务层
 * 复用ruoyi-system中的基础Service，仅承载终端侧业务逻辑
 *
 * @author trae
 */
@Service
public class ClientBorrowRequestBiz
{
    @Autowired
    private ISskBorrowRequestService borrowRequestService;

    @Autowired
    private ISskBookService bookService;

    /**
     * 发起借阅申请
     * 校验图书存在后，创建一条状态为"待处理"的申请记录，
     * 囚号在管理后台审核同意时由管理员填写，故此处不落库
     *
     * @param bookId 书籍ID
     */
    public void create(Long bookId)
    {
        // 参数校验
        if (bookId == null)
        {
            throw new ServiceException("请选择要借阅的图书");
        }
        // 校验图书存在且未删除
        SskBookVo book = bookService.findById(bookId);
        if (book == null)
        {
            throw new ServiceException("图书不存在或已下架");
        }
        // 创建待处理状态的借阅申请
        SskBorrowRequest entity = new SskBorrowRequest();
        entity.setBookId(bookId);
        entity.setStatus(SskBorrowRequestStatus.READY.getValue());
        entity.setCreatedAt(new Date());
        borrowRequestService.create(entity);
    }
}
