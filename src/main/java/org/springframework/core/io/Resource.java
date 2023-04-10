package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源的抽象和访问接口
 */
public interface Resource {

    /**
     * 获取资源文件的输入流
     *
     * @return 输入流
     * @throws IOException IO异常
     */
    InputStream getInputStream() throws IOException;
}
