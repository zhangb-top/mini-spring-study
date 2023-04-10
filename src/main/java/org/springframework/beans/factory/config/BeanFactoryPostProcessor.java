package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * 在beanDefinition加载完成，但是bean为创建的时候，提供修改bean属性值的方法
     *
     * @param beanFactory bean工厂
     * @throws BeansException 异常
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
