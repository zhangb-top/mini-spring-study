package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSimpletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

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
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return ((T) getBean(name));
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

    /**
     * 注册BeanPostProcessor
     *
     * @param beanPostProcessor
     * @throws BeansException
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws BeansException {
        // 有则覆盖
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
