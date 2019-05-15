package com.kq.entity;

import java.io.Serializable;

/**
 * Order
 *
 * @author kq
 * @date 2019-05-09
 */
public class Order implements Serializable{

    /** 订单类型 */
    private int orderType;		//
    /** 用户ID */
    private String userId;		//
    /** 订单名称 */
    private String orderName;	// 订单名称
    /** 订单编号 */
    private String orderNo;		// 订单编号

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderType=").append(orderType);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", orderName='").append(orderName).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
