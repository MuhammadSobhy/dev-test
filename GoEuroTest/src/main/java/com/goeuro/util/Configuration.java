package com.goeuro.util;

import com.goeuro.exception.NoSuchConfigurationKey;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application's configuration class
 * @author Muhammad Sobhy
 * @version 1.0
 */
public class Configuration {
    
    private static final Logger LOGGER = LogManager.getRootLogger();
    
    private static Configuration configuration;
    private static final String PROPERTIES_FILE_NAME = "configuration.properties";
    private static Properties configurationProperties = null;
    //Configuration keys
    public static final String CITY_API_URL = "CITY_API_URL";
    public static final String CSV_FILE_NAME = "CSV_FILE_NAME";

    
    /**
     * Private constructor to make sure there is only one configuration object.
     */
    private Configuration() {   
    }
    
    /**
     * Get configuration object
     * @return:
     *        Configuration object
     */
    public static synchronized Configuration getConfiguration() {
        LOGGER.debug("Entering getConfiguration()");
        if (configuration == null) {
            configuration = new  Configuration();
        }
        LOGGER.debug("Leaving getConfiguration()");
        return configuration;
    }
    
    /**
     * Load configuration file.
     * @throws
     *      IOException - if an error occurred when reading from the input stream.
     */
    public void loadConfiguration() throws IOException {
        LOGGER.debug("Entering loadConfiguration()");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (inputStream != null) {
                configurationProperties = new Properties();
                configurationProperties.load(inputStream);
                LOGGER.debug("Configuration file loaded successfully");
            } else {
                throw new FileNotFoundException("Please make sure that " + PROPERTIES_FILE_NAME
                    + " file in classpath");
            }
        }
        LOGGER.debug("Leaving loadConfiguration()");
    }
    
    /**
     * Read value from configuration file.
     * @param 
     *      key - the value key.
     * @return 
     *      The value in configuration file with the specified key value.      
     * @throws com.goeuro.exception.NoSuchConfigurationKey      
     */
    public String getValue(final String key)throws NoSuchConfigurationKey {
        LOGGER.debug("Entering getValue(key=" + key + ")");
        String value = null;
        if (key == null)
            throw new NoSuchConfigurationKey("Configuration key is null");
        if (configurationProperties != null) {
            if (configurationProperties.containsKey(key)) {
                value = configurationProperties.getProperty(key);
            } else {
                throw new NoSuchConfigurationKey("key " + key + " not exists");
            }
        } else {
            throw new NoSuchConfigurationKey("Configuration file not exists");
        }
        LOGGER.debug("Leaving getValue():" + value);
        return value;
    }
}