package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 读取bean定义信息的抽象接口
 */
public interface BeanDefinitionReader {
    // 获取注册信息
    BeanDefinitionRegistry getRegistry();

    // 获取资源查找定位策略
    ResourceLoader getResourceLoader();

    // 根据资源获取beanDefinition
    void loadBeanDefinitions(Resource resource) throws BeansException;

    // 根据路径获取beanDefinition
    void loadBeanDefinitions(String location) throws BeansException;

    // 根据多个路径获取beanDefinition
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
