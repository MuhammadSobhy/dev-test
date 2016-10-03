package com.goeuro.facade;

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
import java.util.List;

/**
 * City facade to hide complexity and 
 * acts as intermediate layer between client and logic
 * @author muhammad sobhy
 * @version 1.0
 */
public class CityFacade {
    
        
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
        String cityServiceURL = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        CityBusinessService cityBusinessService = new RestCityBusinessService(cityServiceURL);
        String jsonCityData = cityBusinessService.getCityByName(cityName);
        List<City> listCityData = Utils.getCitiesFromJson(jsonCityData);
        return listCityData;
    }
    
    /**
     * Export data into CSV file
     * @param cities
     * @throws com.goeuro.exception.NoSuchConfigurationKey
     * @throws IOException 
     */
    public void exportToCSV(List<City> cities) throws NoSuchConfigurationKey, IOException{
        String fileName = Configuration.getConfiguration().getValue(Configuration.CSV_FILE_NAME);  
        Exporter exporter = new CSVExporter(fileName, getHeader(), toListOfStringArray(cities));
        exporter.export();
    }
    
    /**
     * Get file header
     * @return header 
     */
    private String[] getHeader() {
        return new String[] {City.Header.ID.getName(),City.Header.NAME.getName(),City.Header.TYPE.getName(),
            City.Header.LONGITUDE.getName(),City.Header.LATITUDE.getName()};
    }
    
    /**
     * Convert list of cities into list of string arrays containing city info
     * @param cities
     * @return List<String[]>
     */
    private List<String[]> toListOfStringArray(List<City> cities) {
        List<String[]> stringArrayOfCities = null;
        if (cities != null && cities.size() > 0) {
            stringArrayOfCities = new ArrayList<>();
            for (City city : cities) {
                stringArrayOfCities.add(new String[] {String.valueOf(city.getId()),city.getName(),city.getType(),
                    String.valueOf(city.getGeoPosition().getLatitude()),String.valueOf(city.getGeoPosition().getLongitude())});
            }
        }
        return stringArrayOfCities;
    }
}
