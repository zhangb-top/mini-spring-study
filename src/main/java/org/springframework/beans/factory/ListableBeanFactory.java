package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * 找到指定类型的所有bean
     *
     * @param type bean类型
     * @param <T>  泛型
     * @return beanMap
     * @throws BeansException 未找到bean
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回定义的所有bean的名称
     *
     * @return 所有bean的名称
     */
    String[] getBeanDefinitionNames();
}
