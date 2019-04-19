package com.kq.springboot.service.impl;

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
        return "Hello, "+name;
    }


}
