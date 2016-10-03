package com.goeuro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeuro.entity.City;
import java.io.IOException;
import java.util.List;

/**
 * To add utility stuff
 * @author muhammad sobhy
 * @version 1.0
 */
public class Utils {
    
      
    /**
     * Extract cities from json string
     * @param jsonString
     * @return:
     *      List<City> contains cities extracted from json
     * @throws IOException 
     */
    public static List<City> getCitiesFromJson(String jsonString) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List<City> city = mapper.readValue(jsonString, new TypeReference<List<City>>(){});
        return city;
    }
    
}
