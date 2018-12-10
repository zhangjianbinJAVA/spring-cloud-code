package cn.springcloud.book.domain;

/**
 * Created by caibosi on 2018-07-27.
 */
public enum OrderState {
    /**
     * 下单
     */
    ORDERED,

    /**
     * 确认(有库存)
     */
    CONFIRMED,

    /**
     * 取消
     */
    CANCELED;
}
