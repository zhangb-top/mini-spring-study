package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.SimpletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSimpletonBeanRegistry implements SimpletonBeanRegistry {
    // 管理单例bean的map集合
    private final Map<String, Object> simpletonBeanMap = new HashMap<>();

    /**
     * 获取单例bean
     *
     * @param beanName bean名称
     * @return 单例bean
     */
    @Override
    public Object getSingleton(String beanName) {
        return simpletonBeanMap.get(beanName);
    }

    /**
     * 添加单例bean
     *
     * @param simpletonBeanName 单例bean名称
     * @param simpletonBean     单例bean对象
     */
    public void addSimpleton(String simpletonBeanName, Object simpletonBean) {
        simpletonBeanMap.put(simpletonBeanName, simpletonBean);
    }
}
