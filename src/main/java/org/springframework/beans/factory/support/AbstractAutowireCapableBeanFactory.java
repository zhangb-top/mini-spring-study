package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

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

        // 执行bean的初始化方法
        try {
            invokeInitMethods(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 执行BeanPostProcessor的后置操作
        bean = applyBeanPostProcessorsAfterInitializeBean(bean, beanName);

        //注册有销毁方法的bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean,
                                                   BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName()))
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
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

    /**
     * 在spring中，定义bean的初始化和销毁方法有三种方法：
     * <p>
     * - 在xml文件中制定init-method和destroy-method
     * - 继承自InitializingBean和DisposableBean
     *
     * @param bean           bean对象
     * @param beanName       bean名称
     * @param beanDefinition 定义bean的类
     * @throws Exception 异常
     */
    private void invokeInitMethods(Object bean, String beanName, BeanDefinition beanDefinition) throws Exception {
        // 1、实现了InitializingBean的方法(第二种初始化方式)
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2、在xml中配置了初始化的方法(第一种初始化方式)
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(),
                    initMethodName);
            if (initMethod == null)
                throw new BeansException("Could not find an init method named '" + initMethodName + "'" +
                        " on bean with name '" + beanName + "'");
            initMethod.invoke(bean);
        }
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
