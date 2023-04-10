package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 定义bean的接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean
     *
     * @param beanName       bean的名称
     * @param beanDefinition bean的属性类
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取beanDefinition
     *
     * @param beanName bean名称
     * @return beanDefinition
     * @throws BeansException 未找到bean
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断bean是否存在
     *
     * @param beanName bean名称
     * @return true or false
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取所有bean的名称
     *
     * @return bean的名称数组
     */
    String[] getBeanDefinitionNames();
}
