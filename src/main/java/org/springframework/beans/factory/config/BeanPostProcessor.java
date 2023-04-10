package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

public interface BeanPostProcessor {
    /**
     * 在bean执行初始化方法之前，执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean
     * @throws BeansException 异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean执行初始化方法之后，执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean
     * @throws BeansException 异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
