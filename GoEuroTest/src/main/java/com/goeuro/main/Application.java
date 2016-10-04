package com.goeuro.main;

import com.goeuro.entity.City;
import com.goeuro.exception.NoSuchCity;
import com.goeuro.exception.NoSuchConfigurationKey;
import com.goeuro.facade.CityFacade;
import com.goeuro.util.Configuration;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Main class
 * @author muhammad sobhy
 * @version 1.0
 */
public class Application {

    private static final Logger LOGGER = LogManager.getRootLogger();      
    CityFacade mainFacade;
    
    public static void main(String[] args){
        LOGGER.debug("Entering main(args=" + args + ")");
        if (args == null || args.length <= 0) {
            LOGGER.error("Please pass city name to app.");
        } else {
            try {
                new Application().run(args[0]);
            } catch (IOException | NoSuchCity | NoSuchConfigurationKey ex) {
                LOGGER.error(ex.getMessage() , ex);
            }
        }
        LOGGER.debug("Leaving main()");
    }
    
    private void run(String cityName) throws NoSuchConfigurationKey, NoSuchCity, IOException {
        LOGGER.debug("Entering run(cityName=" + cityName + ")");
        Configuration.getConfiguration().loadConfiguration();
        mainFacade = new CityFacade();
        List<City> cities = mainFacade.retrieveCityFromRestService(cityName);
        mainFacade.exportToCSV(cities);
        LOGGER.debug("Leaving run()");
    }    
}
