package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAndResourceLoaderTest {
    @Test
    public void testResourceAndResourceLoader() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载classpath下的资源
        String classPath = "classpath:hello.txt";
        Resource resource = resourceLoader.getResource(classPath);
        InputStream inputStream = resource.getInputStream();
        String str = IoUtil.readUtf8(inputStream);
        System.out.println(str);

        // 加载URL资源文件
        String url = "https://www.baidu.com";
        resource = resourceLoader.getResource(url);
        inputStream = resource.getInputStream();
        str = IoUtil.readUtf8(inputStream);
        System.out.println(str);

        // 加载系统资源文件
        String filePath = "src/test/resources/hello.txt";
        resource = resourceLoader.getResource(filePath);
        inputStream = resource.getInputStream();
        str = IoUtil.readUtf8(inputStream);
        System.out.println(str);
    }
}
