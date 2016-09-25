package com.goeuro.facade;

import com.goeuro.entity.City;
import com.goeuro.output.CSVExporter;
import com.goeuro.service.CityBusinessService;
import com.goeuro.util.Configuration;
import com.goeuro.util.Utils;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author muhammad sobhy
 */
public class MainFacade {
    
    private final CityBusinessService cityBusinessService;
    private final CSVExporter exporter;

    public MainFacade(CityBusinessService cityBusinessService, CSVExporter exporter) {
        this.cityBusinessService = cityBusinessService;
        this.exporter = exporter;
    }
       
    public void loadCity(String cityName) throws IOException {
        String jsonCityData = cityBusinessService.getCityByName(cityName);
        List<City> listCityData = Utils.getCitiesFromJson(jsonCityData);
        if (listCityData.size() > 0) {
            String[] headers = new String[] {City.Header.ID.getName(),City.Header.NAME.getName(),City.Header.TYPE.getName(),
                City.Header.LONGITUDE.getName(),City.Header.LATITUDE.getName()};
            String fileName = Configuration.getConfiguration().getValue(Configuration.CSV_FILE_NAME);
            if (fileName != null && !fileName.equals("")) {
                exporter.export(fileName, headers, listCityData);
            } else {
                System.out.println(Configuration.CSV_FILE_NAME + " property is missed in configuration file.");
            }
        } else {
            System.out.println("There are no matched cities with[" + cityName + "]. Please try another one.");
        }
    }    
}
