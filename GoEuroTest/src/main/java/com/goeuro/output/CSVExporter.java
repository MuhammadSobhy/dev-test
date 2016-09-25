package com.goeuro.output;

import com.goeuro.entity.City;
import java.util.List;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author muhammad sobhy
 */
public class CSVExporter {
    
    public void export(String fileName , String[] headers , List<City> cityData) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(headers);
            writer.writeAll(toListOfStringArray(cityData));
        }
    }
    
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
