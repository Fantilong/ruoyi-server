package com.ruoyi.web.biz.ssk;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SskBorrowRecord;
import com.ruoyi.system.domain.vo.SskBorrowRecordVo;
import com.ruoyi.system.service.ISskBorrowRecordService;

/**
 * 借阅记录业务层
 * 负责还书状态流转、校验等业务逻辑
 *
 * @author trae
 */
@Service
public class SskBorrowRecordBiz
{
    @Autowired
    private ISskBorrowRecordService borrowRecordService;

    /**
     * 分页查询借阅记录列表
     *
     * @param query 查询参数
     * @return 借阅记录列表
     */
    public List<SskBorrowRecordVo> findList(SskBorrowRecordVo query)
    {
        return borrowRecordService.findList(query);
    }

    /**
     * 根据ID查询借阅记录详情
     *
     * @param id 记录ID
     * @return 借阅记录信息
     */
    public SskBorrowRecordVo findById(Long id)
    {
        SskBorrowRecordVo vo = borrowRecordService.findById(id);
        if (vo == null)
        {
            throw new ServiceException("借阅记录不存在或已被删除");
        }
        return vo;
    }

    /**
     * 新增借阅记录
     *
     * @param entity 借阅记录
     * @param userId 当前登录用户ID
     */
    public void create(SskBorrowRecord entity, Long userId)
    {
        // 借出时间不能晚于应还时间
        if (entity.getBorrowTime().after(entity.getReturnTime()))
        {
            throw new ServiceException("借出时间不能晚于应还时间");
        }
        // 新增时实际归还时间置空
        entity.setId(null);
        entity.setActualReturnTime(null);
        entity.setCreatedBy(userId);
        entity.setCreatedAt(new Date());
        borrowRecordService.create(entity);
    }

    /**
     * 修改借阅记录
     *
     * @param entity 借阅记录
     * @param userId 当前登录用户ID
     */
    public void update(SskBorrowRecord entity, Long userId)
    {
        // 校验记录存在
        SskBorrowRecordVo existing = borrowRecordService.findById(entity.getId());
        if (existing == null)
        {
            throw new ServiceException("借阅记录不存在或已被删除");
        }
        // 借出时间不能晚于应还时间
        if (entity.getBorrowTime() != null && entity.getReturnTime() != null
            && entity.getBorrowTime().after(entity.getReturnTime()))
        {
            throw new ServiceException("借出时间不能晚于应还时间");
        }
        entity.setUpdatedBy(userId);
        entity.setUpdatedAt(new Date());
        borrowRecordService.updateById(entity);
    }

    /**
     * 批量删除借阅记录（逻辑删除）
     *
     * @param ids 记录ID数组
     * @param userId 当前登录用户ID
     */
    public void deleteByIds(Long[] ids, Long userId)
    {
        if (ids == null || ids.length == 0)
        {
            throw new ServiceException("请选择需要删除的借阅记录");
        }
        borrowRecordService.deleteByIds(ids, userId);
    }

    /**
     * 还书：将实际归还时间设为当前时间
     * 已归还的记录不允许重复还书
     *
     * @param id 记录ID
     * @param userId 当前登录用户ID
     */
    public void returnBook(Long id, Long userId)
    {
        SskBorrowRecordVo existing = borrowRecordService.findById(id);
        if (existing == null)
        {
            throw new ServiceException("借阅记录不存在或已被删除");
        }
        if (existing.getActualReturnTime() != null)
        {
            throw new ServiceException("该借阅记录已归还，不能重复还书");
        }
        borrowRecordService.updateReturnTime(id, userId);
    }
}
