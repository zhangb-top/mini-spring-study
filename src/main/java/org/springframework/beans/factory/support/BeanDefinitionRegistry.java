package org.springframework.beans.factory.support;

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
}
