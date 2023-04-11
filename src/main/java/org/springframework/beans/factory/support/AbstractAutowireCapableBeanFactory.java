package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    public InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiation) {
        this.instantiationStrategy = instantiation;
    }

    /**
     * 为bean添加属性
     *
     * @param beanName       bean名称
     * @param bean           bean对象
     * @param beanDefinition 定义bean的对象
     */
    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValue[] propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            // 注入依赖bean
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 使用反射添加属性
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    public Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
            // 添加属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // beanPostProcessor
            bean = initializeBean(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 添加至单例bean
        addSimpleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public Object initializeBean(Object bean, String beanName, BeanDefinition beanDefinition) {
        // 执行BeanPostProcessor的前置操作
        bean = applyBeanPostProcessorsBeforeInitializeBean(bean, beanName);

        // TODO 后面会在此处执行bean的初始化方法
        invokeInitMethods(bean, beanName, beanDefinition);

        // 执行BeanPostProcessor的后置操作
        bean = applyBeanPostProcessorsAfterInitializeBean(bean, beanName);

        return bean;
    }

    // 执行BeanPostProcessor的前置操作
    public Object applyBeanPostProcessorsBeforeInitializeBean(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

    // TODO 后面会在此处执行bean的初始化方法
    private void invokeInitMethods(Object bean, String beanName, BeanDefinition beanDefinition) {
        System.out.println("初始化名称为" + beanName + "的bean");
    }

    private Object applyBeanPostProcessorsAfterInitializeBean(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }
}
