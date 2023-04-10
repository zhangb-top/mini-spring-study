package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
    private final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 首先从classpath查找资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 从URL资源中查找
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                // 从系统资源中查找
                return new FileSystemResource(location);
            }
        }
    }
}
