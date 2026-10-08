package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.SskBorrowRequest;

/**
 * 借阅申请VO
 * 在实体基础上扩展书籍信息等展示字段
 *
 * @author trae
 */
public class SskBorrowRequestVo extends SskBorrowRequest
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

    /** 库存 */
    private Integer stockQuantity;

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

    public Integer getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity)
    {
        this.stockQuantity = stockQuantity;
    }
}
