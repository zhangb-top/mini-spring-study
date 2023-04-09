package org.springframework.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个简单的bean工厂
 */
public class BeanFactory {
    // 简单的 map 集合 管理 bean
    private final Map<String, Object> beanMap = new HashMap<>();

    /**
     * 注册bean
     *
     * @param beanName bean的名称
     * @param bean     bean对象
     */
    public void registerBean(String beanName, Object bean) {
        beanMap.put(beanName, bean);
    }

    /**
     * 根据bean名称获取bean
     *
     * @param beanName bean的名称
     * @return bean对象
     */
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }
}
