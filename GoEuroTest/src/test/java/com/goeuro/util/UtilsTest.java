package com.goeuro.util;

import com.goeuro.service.RestCityBusinessService;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author muhammad sobhy
 */
public class UtilsTest {
    
    @Test
    public void testGetCitiesFromJsonWithRome() throws IOException {
        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        RestCityBusinessService cityBusinessService = new RestCityBusinessService(url);
        String jsonCityData = cityBusinessService.getCityByName("Rome");
        Assert.assertEquals(8,Utils.getCitiesFromJson(jsonCityData).size());
    }
}
