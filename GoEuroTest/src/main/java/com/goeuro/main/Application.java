package com.goeuro.main;

import com.goeuro.entity.City;
import com.goeuro.exception.NoSuchCity;
import com.goeuro.exception.NoSuchConfigurationKey;
import com.goeuro.facade.CityFacade;
import java.io.IOException;
import java.util.List;


/**
 * Main class
 * @author muhammad sobhy
 * @version 1.0
 */
public class Application {

      
    CityFacade mainFacade;
    
    public static void main(String[] args){
        if (args == null || args.length <= 0) {
            System.out.println("Please pass city name to app.");
        } else {
            try {
                new Application().run(args[0]);
            } catch (IOException | NoSuchCity | NoSuchConfigurationKey ex) {
                System.out.println(ex);
            }
        }
    }
    
    private void run(String cityName) throws NoSuchConfigurationKey, NoSuchCity, IOException {
        mainFacade = new CityFacade();
        List<City> cities = mainFacade.retrieveCityFromRestService(cityName);
        mainFacade.exportToCSV(cities);
    }    
}
