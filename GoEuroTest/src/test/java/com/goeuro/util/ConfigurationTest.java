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
    public void testGetValue() throws IOException {
        Configuration configuration = Configuration.getConfiguration();
        Assert.assertEquals("http://api.goeuro.com/api/v2/position/suggest/en/",
                configuration.getValue(Configuration.CITY_API_URL));
    }   
}
