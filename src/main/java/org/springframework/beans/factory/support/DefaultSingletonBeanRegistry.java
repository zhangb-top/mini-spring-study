package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.SimpletonBeanRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SimpletonBeanRegistry {
    // 管理单例bean的map集合
    private final Map<String, Object> simpletonBeanMap = new HashMap<>();
    // 管理有销毁方法的bean
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public Map<String, DisposableBean> getDisposableBeans() {
        return disposableBeans;
    }

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

    /**
     * 注册含有销毁方法的bean
     *
     * @param beanName       bean的名称
     * @param disposableBean 有销毁方法的bean
     */
    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

    /**
     * 销毁含有销毁方法的单例bean
     */
    public void destroySingletons() {
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an" +
                        " exception", e);
            }
        }
    }
}
