package org.springframework.beans.factory;

/**
 * 标记类接口，实现该接口能感知BeanFactory
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
