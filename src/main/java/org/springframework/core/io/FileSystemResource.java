package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * 文件系统资源的实现类
 */
public class FileSystemResource implements Resource {
    private final String filePath;

    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        try {
            Path path = new File(filePath).toPath();
            return Files.newInputStream(path);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }
}
