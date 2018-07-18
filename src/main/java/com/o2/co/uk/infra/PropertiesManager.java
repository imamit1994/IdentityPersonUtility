package com.o2.co.uk.infra;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private Properties feedFileProperties;

    public PropertiesManager(Resource resource) throws IOException {
        feedFileProperties = new Properties();
        feedFileProperties.load(resource.getInputStream());
    }

    public PropertiesManager(String path) throws IOException {
        this(new FileSystemResource(path));
    }

    public Properties getProperties() {
        return feedFileProperties;
    }

    public String getProperty(String key) {
        return feedFileProperties.getProperty(key);
    }

}
