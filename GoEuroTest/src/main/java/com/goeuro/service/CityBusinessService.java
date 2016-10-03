package com.goeuro.service;

import com.goeuro.exception.NoSuchCity;


/**
 * City business service to be implemented by different city storage mechanisms (JSON, XML,DB ...)
 * @author muhammad sobhy
 * @version 1.0
 */
public abstract class CityBusinessService implements BusinessService{

    public abstract <T extends Object> T getCityByName(String cityName) throws NoSuchCity;

}