package com.goeuro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeuro.entity.City;
import com.goeuro.exception.NoSuchCity;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * To add utility stuff
 * @author muhammad sobhy
 * @version 1.0
 */
public class Utils {
    
    private static final Logger LOGGER = LogManager.getRootLogger();
    
    /**
     * Extract cities from json string
     * @param jsonString
     * @return:
     *      List<City>: contains cities extracted from json
     * @throws com.goeuro.exception.NoSuchCity
     */
    public static List<City> getCitiesFromJson(String jsonString) throws NoSuchCity{
        LOGGER.debug("Entering getCitiesFromJson(jsonString=" + jsonString + ")");
        List<City> cities = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            cities = mapper.readValue(jsonString, new TypeReference<List<City>>(){});
        } catch (IOException ex) {
            throw new NoSuchCity("Error in extracting cities from JSON" , ex);
        }
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("Leaving getCitiesFromJson():" + cities);
        else if (cities != null)
            LOGGER.info("Leaving getCitiesFromJson(): cities size " + cities.size());
        else 
            LOGGER.debug("Leaving getCitiesFromJson()");
        return cities;
    }
    
}
