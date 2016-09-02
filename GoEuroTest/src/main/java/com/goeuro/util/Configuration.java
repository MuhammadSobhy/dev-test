package com.goeuro.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application's configuration class
 * @author Muhammad Sobhy
 * @version 1.0
 */
public class Configuration {
    
    private static Configuration configuration;
    private static final String PROPERTIES_FILE_NAME = "configuration.properties";
    private static Properties configurationProperties = null;
    //Configuration keys
    public static final String CITY_API_URL = "CITY_API_URL";
    
    /**
     * Private constructor to make sure there is only one configuration object.
     */
    private Configuration() {   
    }
    
    /**
     * Get single configuration object
     * @return:
     *        Configuration object
     */
    public static synchronized Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new  Configuration();
        }
        return configuration;
    }
    
    /**
     * Load configuration file.
     * @return:
     *        true - if it loaded successfully. 
     * @throws 
     *      IOException - if an error occurred when reading from the input stream.
     */
    private void loadConfiguration() throws IOException {
        long beginTime = System.currentTimeMillis();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (inputStream != null) {
                configurationProperties = new Properties();
                configurationProperties.load(inputStream);
                System.out.println("Configuration file loaded successfully in "
                    + "[" + (System.currentTimeMillis() - beginTime)/1000 + "] sec");
            } else {
                throw new FileNotFoundException("Please make sure that " + PROPERTIES_FILE_NAME
                    + " file in classpath");
            }
        }
    }
    
    /**
     * Read value from configuration file.
     * @param 
     *      key - the property key.
     * @return 
     *      The value in configuration file with the specified key value.      
     * @throws
     *      java.io.IOException      
     */
    public String getValue(final String key) throws IOException {
        String value = null;
        if (configurationProperties == null) {
            loadConfiguration();
        }
        if (configurationProperties.containsKey(key)) {
            value = configurationProperties.getProperty(key);
        } else {
            System.out.println("Property " + key + " doesn't exist in " +PROPERTIES_FILE_NAME 
                    + " file");
        }
        return value;
    }
}