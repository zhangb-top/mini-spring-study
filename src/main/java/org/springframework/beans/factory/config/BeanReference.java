package org.springframework.beans.factory.config;

/**
 * bean属性中的依赖bean
 * 因为这里的value可以通过AbstractBeanFactory的getBean方法获取，所有不需要再添加一个bean属性
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
