package com.kq.apidemo.service.impl;

import com.kq.api.IOrderService;
import com.kq.entity.Order;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * OrderServiceImpl
 *
 * @author kq
 * @date 2019-05-09
 */

@Service(version = "${demo.service.version}")
public class OrderServiceImpl implements IOrderService{

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AtomicInteger orderNo = new AtomicInteger(0);


    @Override
    public String createOrder(Order order) {
        logger.debug("创建订单，收到参数请求："+order.toString());
        String newOrderNo = String.valueOf(orderNo.incrementAndGet());

        // 执行业务代码
        // 。。。。。
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setOrderNo(newOrderNo);
        logger.debug("处理结果："+order.toString());
        return newOrderNo;
    }


    @Override
    public Order getOrder(String orderNo) {
        logger.debug("查询订单，收到参数："+orderNo);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order = new Order();
        order.setOrderName("交易订单");
        order.setOrderType(2);
        order.setUserId("1");
        order.setOrderNo(orderNo);
        logger.debug("查询订单，返回结果："+order);
        return order;
    }
}
