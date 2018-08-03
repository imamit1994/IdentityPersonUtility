package com.o2.co.uk.util;

import com.o2.co.uk.infra.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class FileUtility {

    @Autowired
    private PropertiesManager propertiesManager;

    @PostConstruct
    public void cleanUpExistingBackFiles() throws Exception {
        boolean isBackUpDelete = Boolean.valueOf(propertiesManager.getProperty("delete.all.backup"));
        if (isBackUpDelete) {
            Files.walk(Paths.get(propertiesManager.getProperty("backup.file.location")))
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    private Path getFilePath(String fileNameProperty) {
        return Paths.get(propertiesManager.getProperty("backup.file.location") + propertiesManager.getProperty(fileNameProperty));
    }

    public File getInputECMFile() throws FileNotFoundException {
        File file = new File(propertiesManager.getProperty("input.file"));

        if (file.exists()) {
            return file;
        } else {
            throw new FileNotFoundException();
        }
    }

    public void writeToFileIfDataIsPresent(String data, String fileNameProperty) {
        if (!StringUtils.isEmpty(data)) {
            Path filePath = getFilePath(fileNameProperty);
            if (!Files.exists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.write(filePath, (data + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
