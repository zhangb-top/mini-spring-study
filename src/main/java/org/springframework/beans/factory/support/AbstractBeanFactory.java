package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSimpletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        // 获取单例bean
        Object bean = getSingleton(beanName);
        // 不为null则返回
        if (bean != null) return bean;
        // 不存在单例bean则创建
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * 创建bean
     *
     * @param beanName       bean名称
     * @param beanDefinition 定义bean的对象
     * @return 创建好的bean
     * @throws BeansException 未找到bean
     */
    public abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 获取定义bean的对象
     *
     * @param beanName bean名称
     * @return 定义bean的对象
     * @throws BeansException 未找到bean
     */
    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
