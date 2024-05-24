package com.nioyatech.mlhubprojecttest.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private ConfigurationReader() {
    }

    static Properties properties;
    static{

        String path = "configuration.properties";

        try{
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
            file.close();
        }catch (Exception e){
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

