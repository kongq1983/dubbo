package com.kq.apidemo.consumer;


import com.kq.Constants;
import com.kq.api.IDemoService;
import com.kq.api.IOrderService;
import com.kq.entity.Order;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * api方式配置dubbo客户端示例
 * ApiConsumerConfiguration
 */
public class ApiConsumerConfiguration {

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
		ReferenceConfig<IOrderService> reference = new ReferenceConfig<IOrderService>();
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(IOrderService.class);
		reference.setVersion("1.0.0");
		reference.setTimeout(1000);

		reference.setMock("com.kq.apidemo.service.impl.OrderServiceMock");


		// 和本地bean一样使用demoService
//		IDemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
//		String hello = demoService.getMessage("API demo");
//		System.out.println(hello);

		IOrderService orderService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

		Order order  = new Order();

		String result = orderService.createOrder(order);
		System.out.println("result="+result);
	}

}
