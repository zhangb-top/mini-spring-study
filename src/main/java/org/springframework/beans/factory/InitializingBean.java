package org.springframework.beans.factory;

public interface InitializingBean {
    /**
     * bean的初始化方法
     *
     * @throws Exception 异常
     */
    void afterPropertiesSet() throws Exception;
}
