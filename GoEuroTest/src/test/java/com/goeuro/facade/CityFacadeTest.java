package com.goeuro.facade;

import com.goeuro.output.CSVExporter;
import com.goeuro.service.CityBusinessService;
import com.goeuro.service.RestCityBusinessService;
import com.goeuro.util.Configuration;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author muhammad sobhy
 */
public class MainFacadeTest {
    
    @Test
    public void testLoadCityWithRome() throws IOException {
        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
        CityBusinessService cityBusinessService = new RestCityBusinessService(url);
        CSVExporter cSVExporter = new CSVExporter();
        MainFacade mainFacade = new MainFacade(cityBusinessService, cSVExporter);
        mainFacade.loadCity("Rome");
    }

//    @Test
//    public void testLoadCityWithUnknown() throws IOException {
//        String url = Configuration.getConfiguration().getValue(Configuration.CITY_API_URL);
//        CityBusinessService cityBusinessService = new RestCityBusinessService(url);
//        CSVExporter cSVExporter = new CSVExporter();
//        MainFacade mainFacade = new MainFacade(cityBusinessService, cSVExporter);
//        mainFacade.loadCity("unknown");
//    }
}
