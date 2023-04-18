package org.springframework.beans.factory;

public interface FactoryBean<T> {
    T getObject() throws Exception;

    boolean isSingleton();
}
