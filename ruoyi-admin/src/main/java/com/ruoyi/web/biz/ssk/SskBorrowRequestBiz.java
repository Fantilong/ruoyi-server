package com.ruoyi.web.biz.ssk;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.SskBorrowRequest;
import com.ruoyi.system.domain.vo.SskBorrowRequestVo;
import com.ruoyi.system.enums.SskBorrowRequestStatus;
import com.ruoyi.system.service.ISskBorrowRecordService;
import com.ruoyi.system.service.ISskBorrowRequestService;

/**
 * 借阅申请业务层
 * 负责审核（同意/拒绝）、同意后产生借阅记录等业务逻辑
 *
 * @author trae
 */
@Service
public class SskBorrowRequestBiz
{
    /** 同意申请时默认借阅天数 */
    private static final int DEFAULT_BORROW_DAYS = 7;

    @Autowired
    private ISskBorrowRequestService borrowRequestService;

    @Autowired
    private ISskBorrowRecordService borrowRecordService;

    /**
     * 分页查询借阅申请列表
     *
     * @param query 查询参数
     * @return 借阅申请列表
     */
    public List<SskBorrowRequestVo> findList(SskBorrowRequestVo query)
    {
        return borrowRequestService.findList(query);
    }

    /**
     * 根据ID查询借阅申请详情
     *
     * @param id 申请ID
     * @return 借阅申请信息
     */
    public SskBorrowRequestVo findById(Long id)
    {
        SskBorrowRequestVo vo = borrowRequestService.findById(id);
        if (vo == null)
        {
            throw new ServiceException("借阅申请不存在或已被删除");
        }
        return vo;
    }

    /**
     * 同意申请
     * 1. 将申请状态改为已同意
     * 2. 根据囚号和借阅天数创建一条借阅记录
     * 事务保证两个操作要么都成功要么都失败
     *
     * @param id 申请ID
     * @param prisonerNumber 囚号
     * @param borrowDays 借阅天数，为空时使用默认值7天
     * @param userId 当前登录用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void approve(Long id, String prisonerNumber, Integer borrowDays, Long userId)
    {
        SskBorrowRequestVo request = findById(id);
        // 只有待处理状态的申请才能同意
        if (!SskBorrowRequestStatus.READY.getValue().equals(request.getStatus()))
        {
            throw new ServiceException("该申请已处理，不能重复操作");
        }
        // 校验书籍存在
        if (request.getBookId() == null)
        {
            throw new ServiceException("申请关联的书籍不存在");
        }
        // 计算借出时间和应还时间
        Date borrowTime = new Date();
        int days = borrowDays == null || borrowDays <= 0 ? DEFAULT_BORROW_DAYS : borrowDays;
        Date returnTime = addDays(borrowTime, days);
        // 创建借阅记录
        SskBorrowRecord record = new SskBorrowRecord();
        record.setBookId(request.getBookId());
        record.setPrisonerNumber(prisonerNumber);
        record.setBorrowTime(borrowTime);
        record.setReturnTime(returnTime);
        record.setActualReturnTime(null);
        record.setCreatedBy(userId);
        record.setCreatedAt(borrowTime);
        borrowRecordService.create(record);
        // 更新申请状态为已同意
        SskBorrowRequest update = new SskBorrowRequest();
        update.setId(id);
        update.setStatus(SskBorrowRequestStatus.RESOLVED.getValue());
        update.setUpdatedBy(userId);
        update.setUpdatedAt(new Date());
        borrowRequestService.updateById(update);
    }

    /**
     * 拒绝申请
     * 将申请状态改为已拒绝
     *
     * @param id 申请ID
     * @param userId 当前登录用户ID
     */
    public void reject(Long id, Long userId)
    {
        SskBorrowRequestVo request = findById(id);
        if (!SskBorrowRequestStatus.READY.getValue().equals(request.getStatus()))
        {
            throw new ServiceException("该申请已处理，不能重复操作");
        }
        SskBorrowRequest update = new SskBorrowRequest();
        update.setId(id);
        update.setStatus(SskBorrowRequestStatus.REJECTED.getValue());
        update.setUpdatedBy(userId);
        update.setUpdatedAt(new Date());
        borrowRequestService.updateById(update);
    }

    /**
     * 批量删除借阅申请（逻辑删除）
     *
     * @param ids 申请ID数组
     * @param userId 当前登录用户ID
     */
    public void deleteByIds(Long[] ids, Long userId)
    {
        if (ids == null || ids.length == 0)
        {
            throw new ServiceException("请选择需要删除的借阅申请");
        }
        borrowRequestService.deleteByIds(ids, userId);
    }

    /**
     * 日期加指定天数
     */
    private Date addDays(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}
