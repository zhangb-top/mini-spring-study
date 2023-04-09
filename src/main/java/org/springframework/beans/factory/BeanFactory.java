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
}
