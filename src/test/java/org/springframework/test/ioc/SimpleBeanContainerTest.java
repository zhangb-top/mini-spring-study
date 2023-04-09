package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

public class SimpleBeanContainerTest {
    @Test
    public void testGetBean() {
        // 1、获取BeanFactory对象
        BeanFactory beanFactory = new BeanFactory();
        // 2、注册bean、
        beanFactory.registerBean("helloService", new HelloService());
        // 3、获取bean对象
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        // 4、使用bean对象的成员方法
        helloService.sayHello();
    }

    static class HelloService {
        public void sayHello() {
            System.out.println("hello spring");
        }
    }
}
