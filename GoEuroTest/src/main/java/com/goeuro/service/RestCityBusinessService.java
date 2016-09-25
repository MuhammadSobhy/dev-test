package com.goeuro.service;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Rest Business service to call REST web service and return city
 * @author muhammad sobhy
 * @version 1.0
 */
public class RestCityBusinessService extends CityBusinessService{

    
    public RestCityBusinessService(String url) {
        super(url);
    }
    
    @Override
    public String getCityByName(String cityName) throws IOException{
        String jsonResponse = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(getUrl() + cityName);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            jsonResponse = EntityUtils.toString(entity);
        } catch (IOException ex) {
            System.out.println("calling city service failed: " + ex);
            throw ex;
        } finally {
            try {
                if(response != null) 
                    response.close();
            } catch (IOException ex) {
                System.out.println("closing service response failed: " + ex);
                throw ex;
            }
        }
        return jsonResponse;
    }
}