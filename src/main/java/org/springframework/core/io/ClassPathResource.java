package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * classpath下资源的实现类
 */
public class ClassPathResource implements Resource {
    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    /**
     * 从当前类所在的类加载器中获取指定路径下的资源文件，并返回一个输入流
     * 1、this.getClass() 获取当前类的 Class 对象
     * 2、getClassLoader() 获取当前类的类加载器（加载resources目录）
     * 3、getResourceAsStream(this.path) 获取指定路径下的资源文件，并返回一个输入流。其中，this.path
     * 是一个字符串类型的参数，表示需要获取的资源文件的路径。这个路径可以是相对于当前类所在的包的相对路径
     * 也可以是以“/”开头的绝对路径。如果资源文件不存在，将返回 null
     *
     * @return 资源文件的输入流
     * @throws IOException IO异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
