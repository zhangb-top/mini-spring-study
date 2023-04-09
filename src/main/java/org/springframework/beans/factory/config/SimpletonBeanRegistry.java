package org.springframework.beans.factory.config;

/**
 * 单例bean的注册接口
 */
public interface SimpletonBeanRegistry {
    /**
     * 获取单例bean
     *
     * @param beanName bean名称
     * @return 单例bean
     */
    Object getSingleton(String beanName);
}
