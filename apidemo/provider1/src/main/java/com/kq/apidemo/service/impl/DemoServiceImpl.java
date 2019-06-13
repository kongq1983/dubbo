package com.kq.apidemo.service.impl;

import com.kq.api.IDemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * DemoServiceImpl
 *
 * @author kq
 * @date 2019/4/19
 */

@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements IDemoService {

    public String getMessage(String name) {
        System.out.println("call DemoServiceImpl param="+name);
        return "Hello, "+name;
    }


}
