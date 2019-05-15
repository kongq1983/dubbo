package com.kq.apidemo.provider;


import com.kq.api.IDemoService;
import com.kq.api.IOrderService;
import com.kq.apidemo.service.impl.DemoServiceImpl;
import com.kq.apidemo.service.impl.OrderServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * ApiProviderConfiguration
 *
 * @author kq
 * @date 2019/4/20
 */
public class ApiProviderConfiguration {

    public static void main(String[] args) throws Exception {

        String address = "zookeeper://127.0.0.1:2181";

        // 服务实现
        IDemoService demoService = new DemoServiceImpl();

        // 当前应用配置。 请学习ApplicationConfig的API
        ApplicationConfig application = new ApplicationConfig();
        application.setName("hello-world-app");

        // 连接注册中心配置。 请学习RegistryConfig的API
        RegistryConfig registry = new RegistryConfig(address, "dubbo");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置。请学习ServiceConfig的API
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<IDemoService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(IDemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();


        IOrderService orderService = new OrderServiceImpl();
        ServiceConfig<IOrderService> orderServiceConfig = new ServiceConfig<>();
        orderServiceConfig.setApplication(application);
        orderServiceConfig.setRegistry(registry); // 多个注册中心可以用setRegistries()
        orderServiceConfig.setProtocol(protocol); // 多个协议可以用setProtocols()
        orderServiceConfig.setInterface(IOrderService.class);
        orderServiceConfig.setRef(orderService);
        orderServiceConfig.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
        orderServiceConfig.export();

        System.in.read(); // 按任意键退出
    }

}
