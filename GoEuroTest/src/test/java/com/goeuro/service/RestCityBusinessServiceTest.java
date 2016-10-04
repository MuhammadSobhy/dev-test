package com.goeuro.service;

import com.goeuro.exception.NoSuchCity;
import com.goeuro.exception.NoSuchConfigurationKey;
import com.goeuro.util.Configuration;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author muhammad sobhy
 */
public class RestCityBusinessServiceTest {
    
    @Test
    public void testGetCityByNameWithBerlin() throws IOException, NoSuchConfigurationKey, NoSuchCity {
        Configuration.getConfiguration().loadConfiguration();
        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        RestCityBusinessService cityBusinessService = new RestCityBusinessService(url);
        String jsonCityData = cityBusinessService.getCityByName("Berlin");
        Assert.assertTrue(jsonCityData.contains("Berlin"));
    }
    
    @Test
    public void testGetCityByNameWithRome() throws IOException, NoSuchConfigurationKey, NoSuchCity {
        Configuration.getConfiguration().loadConfiguration();
        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        RestCityBusinessService cityBusinessService = new RestCityBusinessService(url);
        String jsonCityData = cityBusinessService.getCityByName("Rome");
        Assert.assertTrue(jsonCityData.contains("Rome"));
    }
    
    @Test
    public void testGetCityByNameWithUnknownCity() throws IOException, NoSuchConfigurationKey, NoSuchCity {
        Configuration.getConfiguration().loadConfiguration();
        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        RestCityBusinessService cityBusinessService = new RestCityBusinessService(url);
        String jsonCityData = cityBusinessService.getCityByName("UnknownCity");
        Assert.assertEquals("[]",jsonCityData);
    }
}