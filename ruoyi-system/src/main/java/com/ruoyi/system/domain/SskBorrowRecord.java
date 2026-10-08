package com.ruoyi.system.domain;

import java.io.Serializable;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 借阅记录实体类，对应表 ssk_borrow_record
 * 字段与表结构一一对应，不额外扩展业务字段
 *
 * @author trae
 */
public class SskBorrowRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 书籍ID */
    @NotNull(message = "书籍不能为空")
    private Long bookId;

    /** 囚号 */
    @NotBlank(message = "囚号不能为空")
    private String prisonerNumber;

    /** 借出时间 */
    @NotNull(message = "借出时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowTime;

    /** 应还时间 */
    @NotNull(message = "应还时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;

    /** 实际归还时间，为空表示未归还 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualReturnTime;

    /** 创建者ID */
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 更新者ID */
    private Long updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    /** 是否已删除（0否 1是） */
    private Integer deleted;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getBookId()
    {
        return bookId;
    }

    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public String getPrisonerNumber()
    {
        return prisonerNumber;
    }

    public void setPrisonerNumber(String prisonerNumber)
    {
        this.prisonerNumber = prisonerNumber;
    }

    public Date getBorrowTime()
    {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime)
    {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime()
    {
        return returnTime;
    }

    public void setReturnTime(Date returnTime)
    {
        this.returnTime = returnTime;
    }

    public Date getActualReturnTime()
    {
        return actualReturnTime;
    }

    public void setActualReturnTime(Date actualReturnTime)
    {
        this.actualReturnTime = actualReturnTime;
    }

    public Long getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy)
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }
}
