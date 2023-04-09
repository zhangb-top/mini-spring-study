package org.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 使用CGLIB动态生成子类
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 使用CGLIB动态生成bean的代理类
     *
     * @param beanDefinition 定义bean的对象
     * @return bean对象
     * @throws BeansException 创建bean异常
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        // 创建一个Enhancer对象，Enhancer是CGLIB中的一个类，用于生成代理类的实例
        Enhancer enhancer = new Enhancer();
        // 设置Enhancer对象的父类为beanDefinition中的beanClass，即要代理的类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置Enhancer对象的回调函数，回调函数是一个MethodInterceptor对象，用于在代理对象执行方法时拦截并处理方法调用
        enhancer.setCallback((MethodInterceptor) (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj, argsTemp));
        // 调用Enhancer对象的create()方法生成代理对象并返回
        return enhancer.create();
    }
}
