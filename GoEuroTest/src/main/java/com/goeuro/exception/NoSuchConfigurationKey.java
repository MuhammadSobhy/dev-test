package com.goeuro.exception;

/**
 * No configuration key founded
 * @author muhammad sobhy
 * @version 1.0
 */
public class NoSuchConfigurationKey extends Exception{

    public NoSuchConfigurationKey(String message) {
        super(message);
    }

    public NoSuchConfigurationKey(String message, Throwable cause) {
        super(message, cause);
    }   
    
}
