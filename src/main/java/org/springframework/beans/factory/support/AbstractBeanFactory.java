package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String beanName) {
        // 获取单例bean
        Object sharedInstance = getSingleton(beanName);
        // 不为null则返回
        if (sharedInstance != null) return getObjectForBeanInstance(sharedInstance, beanName);
        ;
        // 不存在单例bean则创建
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition);
        return getObjectForBeanInstance(bean, beanName);
    }

    /**
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return ((T) getBean(name));
    }

    public Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object bean = beanInstance;
        if (bean instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) bean;
            try {
                if (factoryBean.isSingleton()) {
                    // singleton的bean从缓存里面取
                    bean = factoryBeanObjectCache.get(factoryBean);
                    if (bean == null) {
                        bean = factoryBean.getObject();
                    }
                } else {
                    // prototype作用域bean，新创建bean
                    bean = factoryBean.getObject();
                }
            } catch (Exception ex) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "] " +
                        "creation", ex);
            }
        }
        return bean;
    }

    /**
     * 创建bean
     *
     * @param beanName       bean名称
     * @param beanDefinition 定义bean的对象
     * @return 创建好的bean
     * @throws BeansException 未找到bean
     */
    public abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 获取定义bean的对象
     *
     * @param beanName bean名称
     * @return 定义bean的对象
     * @throws BeansException 未找到bean
     */
    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 注册BeanPostProcessor
     *
     * @param beanPostProcessor
     * @throws BeansException
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws BeansException {
        // 有则覆盖
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
