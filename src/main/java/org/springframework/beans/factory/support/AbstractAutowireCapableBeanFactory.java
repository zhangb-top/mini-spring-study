package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    public Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        // 根据定义bean的对象，获取bean的Class类型
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            // 通过反射创建出bean
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 添加至单例bean
        addSimpleton(beanName, bean);
        return bean;
    }
}
