package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.SskBorrowRecord;

/**
 * 借阅记录VO
 * 在实体基础上扩展书籍信息、创建人/更新人姓名等展示字段
 *
 * @author trae
 */
public class SskBorrowRecordVo extends SskBorrowRecord
{
    private static final long serialVersionUID = 1L;

    /** 书籍名称 */
    private String bookName;

    /** 作者 */
    private String bookAuthor;

    /** 封面 */
    private String bookCover;

    /** 书架号 */
    private String shelfCode;

    /** 创建人姓名 */
    private String createdByName;

    /** 更新人姓名 */
    private String updatedByName;

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCover()
    {
        return bookCover;
    }

    public void setBookCover(String bookCover)
    {
        this.bookCover = bookCover;
    }

    public String getShelfCode()
    {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode)
    {
        this.shelfCode = shelfCode;
    }

    public String getCreatedByName()
    {
        return createdByName;
    }

    public void setCreatedByName(String createdByName)
    {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName()
    {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName)
    {
        this.updatedByName = updatedByName;
    }
}
