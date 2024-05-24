package com.nioyatech.mlhubprojecttest.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InfoHelper {

    private static final String PROPERTIES_FILE_PATH = "src/test/resources/information.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getInfoText(String key) {
        return properties.getProperty(key);
    }
}

