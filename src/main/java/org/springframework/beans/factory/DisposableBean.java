package org.springframework.beans.factory;

public interface DisposableBean {
    /**
     * bean的销毁方法
     *
     * @throws Exception 异常
     */
    void destroy() throws Exception;
}
