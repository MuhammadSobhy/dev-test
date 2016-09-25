package com.goeuro.util;

import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author muhammad sobhy
 */
public class ConfigurationTest {
    
    @Test
    public void testGetValueWithAPIUrl() throws IOException {
        Configuration configuration = Configuration.getConfiguration();
        Assert.assertEquals("http://api.goeuro.com/api/v2/position/suggest/en/",
                configuration.getValue(Configuration.CITY_API_URL));
    }
    
    @Test
    public void testGetValueCSVFileName() throws IOException {
        Configuration configuration = Configuration.getConfiguration();
        Assert.assertEquals("data.csv",
                configuration.getValue(Configuration.CSV_FILE_NAME));
    }
    
    @Test
    public void testGetValueWithUnknownProperty() throws IOException {
        Configuration configuration = Configuration.getConfiguration();
        Assert.assertEquals(null,configuration.getValue("UnknownProperty"));
    }
}
