package com.goeuro.service;

import java.io.IOException;

/**
 * Rest Business service to call REST web service and return city
 * @author muhammad sobhy
 * @version 1.0
 */
public class RestCityBusinessService extends CityBusinessService{

    
    public RestCityBusinessService(String url) {
        super(url);
    }
    
    @Override
    public String getCityByName(String cityName) throws IOException{
        return null;
    }
}