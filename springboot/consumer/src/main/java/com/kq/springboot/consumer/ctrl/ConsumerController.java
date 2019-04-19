package com.kq.springboot.consumer.ctrl;

import com.kq.api.IDemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConsumerController  registry:
 * @author kq
 * @date 2019/4/19
 */
@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${demo.service.version}")
    private IDemoService demoService;


    @RequestMapping("/hello/{name}")
    public String doSayHello(@PathVariable("name") String name) {

        logger.info("demoService={}",demoService);

        return demoService.getMessage(name);
    }




}
