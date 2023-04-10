package org.springframework.core.io;

/**
 * 资源查找定位策略的抽象
 */
public interface ResourceLoader {
    /**
     * 获取资源接口的实现类，从而获取InputStream
     *
     * @param location 资源路径
     * @return 资源实现类
     */
    Resource getResource(String location);
}
