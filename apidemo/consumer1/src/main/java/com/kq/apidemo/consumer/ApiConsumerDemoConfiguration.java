package com.kq.apidemo.consumer;

import com.kq.Constants;
import com.kq.api.IDemoService;
import com.kq.api.IOrderService;
import com.kq.entity.Order;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * ApiConsumerDemoConfiguration
 *
 * @author kq
 * @date 2019-06-12
 */
public class ApiConsumerDemoConfiguration {


    public static void main(String[] args) {

        String address = Constants.ZOOKEEPER_SERVER_PROTOCOL;
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-of-helloworld-app");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig(address, "dubbo");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<IDemoService> reference = new ReferenceConfig<IDemoService>();
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(IDemoService.class);
        reference.setVersion("1.0.0");
        reference.setTimeout(1000);


        IDemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

        String message = demoService.getMessage("king");
        System.out.println("result="+message);
    }

}
