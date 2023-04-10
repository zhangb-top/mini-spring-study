package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AutowireCapableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory,
        AutowireCapableBeanFactory {
    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName bean名称
     * @return beanDefinition
     * @throws BeansException 如果找不到BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    // 创建bean的前后分别加入postProcessBeforeInitialization和postProcessAfterInitialization
    @Override
    void addBeanPostProcessor() throws BeansException;
}
