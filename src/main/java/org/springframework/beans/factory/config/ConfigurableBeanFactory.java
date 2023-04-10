package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends SimpletonBeanRegistry, HierarchicalBeanFactory {
    // 创建bean的前后分别加入postProcessBeforeInitialization和postProcessAfterInitialization
    void addBeanPostProcessor() throws BeansException;
}
