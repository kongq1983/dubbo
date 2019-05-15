package com.kq.apidemo.service.impl;

import com.kq.api.IOrderService;
import com.kq.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * OrderServiceMock
 *
 * @author kq
 * @date 2019-05-09
 */
public class OrderServiceMock implements IOrderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AtomicInteger orderNo = new AtomicInteger(0);


    @Override
    public String createOrder(Order order) {
        logger.debug("这是一个mock实现："+order.toString());
        String newOrderNo = "mock-"+String.valueOf(orderNo.incrementAndGet());

        // 执行业务代码
        // 。。。。。
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setOrderNo(newOrderNo);
        logger.debug("mock处理结果为："+order.toString());
        return newOrderNo;
    }


    @Override
    public Order getOrder(String orderNo) {
        Order order = new Order();
        order.setOrderName("Mock订单");
        order.setOrderType(3);
        order.setUserId("1");
        order.setOrderNo(orderNo);
        return order;
    }

}
