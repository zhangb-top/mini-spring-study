package org.springframework.test.ioc.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = person.getPropertyValues();
        // 修改名字为王麻子
        propertyValues.addPropertyValue(new PropertyValue("name", "王麻子"));
    }
}
