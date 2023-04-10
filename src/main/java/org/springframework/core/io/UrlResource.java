package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 对java.net.URL进行资源定位的实现类
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    /**
     * 使用 URL 类的 openConnection() 方法打开一个网络连接，并获取该连接的输入流
     *
     * @return URL连接的输入流
     * @throws IOException IO异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
