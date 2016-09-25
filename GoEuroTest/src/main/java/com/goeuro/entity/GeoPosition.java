package com.goeuro.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains city's position information
 * @author muhammad sobhy
 * @version 1.0
 */
public class GeoPosition {
    
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }    
}
