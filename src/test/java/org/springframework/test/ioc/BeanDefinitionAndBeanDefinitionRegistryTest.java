package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.service.HelloService;

public class BeanDefinitionAndBeanDefinitionRegistryTest {
    @Test
    public void testGetBean() {
        // 1、创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、获取注册bean的class属性
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        // 3、注册bean
        beanFactory.registryBeanDefinition("helloService", beanDefinition);
        // 4、使用bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}
