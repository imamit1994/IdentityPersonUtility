package com.o2.co.uk.util;

import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgumentValidator {
    public static boolean isValidFilePath(String path) {
        return (!StringUtils.isEmpty(path)) && Files.isRegularFile(Paths.get(path));
    }

}
