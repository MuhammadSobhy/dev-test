package com.goeuro.main;

import com.goeuro.facade.MainFacade;
import com.goeuro.output.CSVExporter;
import com.goeuro.service.CityBusinessService;
import com.goeuro.service.RestCityBusinessService;
import com.goeuro.util.Configuration;
import java.io.IOException;

/**
 *
 * @author muhammad sobhy
 */
public class Application {

      
    MainFacade mainFacade;
    
    public static void main(String[] args){
        if (args == null || args.length <= 0) {
            System.out.println("Please pass city name to app.");
        } else {
            try {
                new Application().run(args[0]);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void run(String cityName) throws IOException {
        String cityServiceURL = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        if (cityServiceURL != null && !cityServiceURL.equals("")) {
            CityBusinessService cityBusinessService = new RestCityBusinessService(cityServiceURL);
            CSVExporter cSVExporter = new CSVExporter();
            mainFacade = new MainFacade(cityBusinessService, cSVExporter);
            mainFacade.loadCity(cityName);
        } else {
            System.out.println(Configuration.CITY_API_URL + " property is missed in configuration file.");
        }
    }
    
}
