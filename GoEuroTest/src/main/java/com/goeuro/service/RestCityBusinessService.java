package com.goeuro.service;

import com.goeuro.exception.NoSuchCity;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Rest city business service
 * @author muhammad sobhy
 * @version 1.0
 */
public class RestCityBusinessService extends CityBusinessService{

    private final String url;
    
    public RestCityBusinessService(String url) {
        this.url = url;
    }
    
    /**
     * Call REST web service to inquire about specific city
     * @param cityName
     * @return jsonResponse contains city info
     * @throws NoSuchCity 
     */
    @Override
    public String getCityByName(String cityName) throws NoSuchCity{
        String jsonResponse = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(getUrl() + cityName);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            jsonResponse = EntityUtils.toString(entity);
        } catch (IOException ex) {
            throw new NoSuchCity("calling city REST service failed",ex);
        } finally {
            try {
                if(response != null) 
                    response.close();
            } catch (IOException ex) {
                System.out.println("closing service response failed: " + ex);
            }
            try {
                if(httpClient != null) 
                    httpClient.close();
            } catch (IOException ex) {
                System.out.println("closing service response failed: " + ex);
            }
        }
        return jsonResponse;
    }

    /**
     * Get service name
     * @return service name 
     */
    @Override
    public String getBusinessServiceName() {
        return "Rest City Business Service";
    }
    
    public String getUrl() {
        return url;
    }    
}