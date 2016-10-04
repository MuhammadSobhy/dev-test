package com.goeuro.facade;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeuro.entity.City;
import com.goeuro.exception.NoSuchCity;
import com.goeuro.exception.NoSuchConfigurationKey;
import com.goeuro.output.CSVExporter;
import com.goeuro.output.Exporter;
import com.goeuro.service.CityBusinessService;
import com.goeuro.service.RestCityBusinessService;
import com.goeuro.util.Configuration;
import com.goeuro.util.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * City facade to hide complexity and 
 * acts as intermediate layer between client and logic
 * @author muhammad sobhy
 * @version 1.0
 */
public class CityFacade {
    
    private static final Logger LOGGER = LogManager.getRootLogger();    
    
    public CityFacade() {
    }
    
    /**
     * Retrieve cities from restful web service that match the criteria
     * @param cityName
     * @return:
     *          list of cities
     * @throws com.goeuro.exception.NoSuchConfigurationKey
     * @throws com.goeuro.exception.NoSuchCity 
     */
    public List<City> retrieveCityFromRestService(String cityName)throws NoSuchConfigurationKey, NoSuchCity{
        LOGGER.debug("Entering retrieveCityFromRestService(cityName=" + cityName + ")");
        String cityServiceURL = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        CityBusinessService cityBusinessService = new RestCityBusinessService(cityServiceURL);
        String jsonCityData = cityBusinessService.getCityByName(cityName);
        List<City> listCityData = getCitiesFromJson(jsonCityData);
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("Leaving retrieveCityFromRestService():" + listCityData);
        else if (listCityData != null)
            LOGGER.debug("Leaving retrieveCityFromRestService(): Retrieve cities size:" + listCityData.size());
        else
            LOGGER.debug("Leaving retrieveCityFromRestService()");
        return listCityData;
    }
    
    /**
     * Export data into CSV file
     * @param cities
     * @throws com.goeuro.exception.NoSuchConfigurationKey
     * @throws IOException 
     */
    public void exportToCSV(List<City> cities) throws NoSuchConfigurationKey, IOException{
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("Entering exportToCSV(cities=" + cities + ")");
        else if (cities != null)
            LOGGER.debug("Entering exportToCSV(cities size=" + cities.size() + ")");
        else
            LOGGER.debug("Entering exportToCSV()");        
        String fileName = Configuration.getConfiguration().getValue(Configuration.CSV_FILE_NAME);  
        Exporter exporter = new CSVExporter(fileName, getHeader(), toListOfStringArray(cities));
        exporter.export();
        LOGGER.debug("Leaving exportToCSV()");
    }
    
    /**
     * Get file header
     * @return header 
     */
    private String[] getHeader() {
        LOGGER.debug("Entering getHeader()");
        String[] header = new String[] {City.Header.ID.getName(),City.Header.NAME.getName(),City.Header.TYPE.getName(),
            City.Header.LONGITUDE.getName(),City.Header.LATITUDE.getName()};
        LOGGER.debug("Leaving getHeader():" + Arrays.toString(header));
        return header;
    }
    
    /**
     * Convert list of cities into list of string arrays containing city info
     * @param cities
     * @return List<String[]>
     */
    private List<String[]> toListOfStringArray(List<City> cities) {
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("Entering toListOfStringArray(cities=" + cities + ")");
        else if (cities != null)
            LOGGER.debug("Entering toListOfStringArray(cities size=" + cities.size() + ")");
        else
            LOGGER.debug("Entering toListOfStringArray()");
        List<String[]> stringArrayOfCities = null;
        if (cities != null && cities.size() > 0) {
            stringArrayOfCities = new ArrayList<>();
            for (City city : cities) {
                stringArrayOfCities.add(new String[] {String.valueOf(city.getId()),city.getName(),city.getType(),
                    String.valueOf(city.getGeoPosition().getLatitude()),String.valueOf(city.getGeoPosition().getLongitude())});
            }
        }
        if (stringArrayOfCities != null)
            LOGGER.debug("Leaving toListOfStringArray(): size " + stringArrayOfCities.size());
        else
            LOGGER.debug("Leaving toListOfStringArray()");
        return stringArrayOfCities;
    }
    
    /**
     * Extract cities from json string
     * @param jsonString
     * @return:
     *      List<City>: contains cities extracted from json
     * @throws com.goeuro.exception.NoSuchCity
     */
    private static List<City> getCitiesFromJson(String jsonString) throws NoSuchCity{
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
            LOGGER.debug("Leaving getCitiesFromJson(): cities size " + cities.size());
        else 
            LOGGER.debug("Leaving getCitiesFromJson()");
        return cities;
    }
}
