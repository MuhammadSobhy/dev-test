package com.goeuro.service;

import java.io.IOException;


/**
 * city business service to be implemented by different city storage mechanism (JSON, XML, ...)
 * @author muhammad sobhy
 * @version 1.0
 */
public abstract class CityBusinessService implements BusinessService{

    private String url;

    public CityBusinessService(String url) {
        this.url = url;
    }   
    
    @Override
    public String getBusinessServiceName() {
        return "City Business Service";
    }
    
    public abstract String getCityByName(String cityName) throws IOException;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
