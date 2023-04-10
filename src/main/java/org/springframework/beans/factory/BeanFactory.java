package org.springframework.beans.factory;

/**
 * bean工厂
 */
public interface BeanFactory {
    /**
     * 根据bean名称获取bean
     *
     * @param name bean名称
     * @return bean对象
     */
    Object getBean(String name);

    /**
     * 根据名称和类型获取bean
     *
     * @param name         bean的名称
     * @param requiredType bean的类型
     * @param <T>          bean的类型
     * @return bean
     */
    <T> T getBean(String name, Class<T> requiredType);
}
