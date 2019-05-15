package com.kq.api;

import com.kq.entity.Order;

/**
 * IOrderService
 *
 * @author kq
 * @date 2019-05-09
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param order
     * @return
     */
    public String createOrder(Order order);

    /**
     * 查询订单信息
     * @param orderNo
     * @return
     */
    public Order getOrder(String orderNo);


}
