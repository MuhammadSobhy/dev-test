package com.goeuro.facade;

import com.goeuro.exception.NoSuchCity;
import com.goeuro.exception.NoSuchConfigurationKey;
import com.goeuro.util.Configuration;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author muhammad sobhy
 */
public class CityFacadeTest {
    
    @Test
    public void testLoadCityWithRome() throws IOException, NoSuchConfigurationKey, NoSuchCity {
        Configuration.getConfiguration().loadConfiguration();
        CityFacade mainFacade = new CityFacade();
        mainFacade.exportToCSV(mainFacade.retrieveCityFromRestService("Rome"));
    }
}
