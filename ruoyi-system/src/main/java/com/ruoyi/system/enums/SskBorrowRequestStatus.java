package com.ruoyi.system.enums;

/**
 * 借阅申请状态枚举
 * 状态值与数据库存储值保持一致，作为前后端唯一契约
 *
 * @author trae
 */
public enum SskBorrowRequestStatus
{
    /** 待处理 */
    READY("ready", "待处理"),

    /** 已同意 */
    RESOLVED("resolved", "已同意"),

    /** 已拒绝 */
    REJECTED("rejected", "已拒绝");

    /** 状态值，存入数据库 */
    private final String value;

    /** 状态描述，用于展示 */
    private final String label;

    SskBorrowRequestStatus(String value, String label)
    {
        this.value = value;
        this.label = label;
    }

    public String getValue()
    {
        return value;
    }

    public String getLabel()
    {
        return label;
    }

    /**
     * 根据状态值获取枚举
     *
     * @param value 状态值
     * @return 对应枚举，找不到返回null
     */
    public static SskBorrowRequestStatus fromValue(String value)
    {
        for (SskBorrowRequestStatus status : values())
        {
            if (status.value.equals(value))
            {
                return status;
            }
        }
        return null;
    }
}
