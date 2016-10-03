package com.goeuro.exception;

/**
 * No city founded
 * @author muhammad sobhy
 * @version 1.0
 */
public class NoSuchCity extends Exception{

    public NoSuchCity(String message) {
        super(message);
    }

    public NoSuchCity(String message, Throwable cause) {
        super(message, cause);
    }   
    
}
