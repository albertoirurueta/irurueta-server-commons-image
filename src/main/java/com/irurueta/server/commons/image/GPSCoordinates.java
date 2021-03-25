/*
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.irurueta.server.commons.image;

/**
 * This class contains GPS coordinates of an image (latitude, longitude and
 * altitude) expressed in degrees and meters respectively.
 */
public class GPSCoordinates {
    
    /**
     * Latitude of location expressed in degrees.
     * Note: minutes and seconds must be expressed as a decimal fraction of a 
     * degree.
     */    
    private Double mLatitude;
    
    /**
     * Longitude of location expressed in degrees.
     * Note: minutes and seconds must be expressed a decimal fraction of a 
     * degree.
     */
    private Double mLongitude;
    
    /**
     * Altitude of location expressed in meters.
     * Zero indicates sea level. Positive values indicate an altitude above
     * sea level and negative values indicate an altitude below sea level.
     */
    private Double mAltitude;
    
    /**
     * Constructor.
     */
    protected GPSCoordinates() { }
    
    /**
     * Constructor.
     * @param latitude latitude expressed in degrees. Note: minutes and seconds
     * must be expressed as a decimal fraction of a degree.
     * @param longitude longitude expressed in degrees. Note: minutes and 
     * seconds must be expressed as a decimal fraction of a degree.
     */
    public GPSCoordinates(final double latitude, final double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }
    
    /**
     * Constructor.
     * @param latitude latitude expressed in degrees. Note: minutes and seconds
     * must be expressed as a decimal fraction of a degree.
     * @param longitude longitude expressed in degrees. Note: minutes and 
     * seconds must be expressed as a decimal fraction of a degree.
     * @param altitude altitude expressed in meters.
     */
    public GPSCoordinates(final double latitude, final double longitude,
            final double altitude) {
        mLatitude = latitude;
        mLongitude = longitude;
        mAltitude = altitude;
    }
        
    /**
     * Returns latitude expressed in degrees, or null if value is unknown. 
     * Note: minutes and seconds must be expressed as a decimal fraction of a 
     * degree.
     * @return latitude expressed in degrees or null if unknown.
     */
    public Double getLatitude() {
        return mLatitude;
    }
    
    /**
     * Sets latitude expressed in degrees. Null indicates that value is unknown
     * Note: minutes and seconds must be expressed as a decimal fraction of a 
     * degree.
     * @param latitude latitude to be set expressed in degrees or null if 
     * unknown.
     */
    public void setLatitude(final double latitude) {
        mLatitude = latitude;
    }
    
    /**
     * Indicates whether latitude coordinate is available.
     * @return true if available, false otherwise.
     */
    public boolean isLatitudeAvailable() {
        return mLatitude != null;
    }
            
    /**
     * Returns longitude expressed in degrees, or null if value is unknown. 
     * Note: minutes and seconds must be expressed as a decimal
     * @return longitude expressed in degrees or null if unknown.
     */
    public Double getLongitude() {
        return mLongitude;
    }
    
    /**
     * Sets longitude expressed in degrees. Null indicates that value is 
     * unknown.
     * Note: minutes and seconds must be expressed as a decimal faction of a 
     * degree.
     * @param longitude longitude to be set expressed in degrees or null if 
     * unknown.
     */
    public void setLongitude(final double longitude) {
        mLongitude = longitude;
    }
    
    /**
     * Indicates whether longitude coordinate is available.
     * @return true if available, false otherwise.
     */
    public boolean isLongitudeAvailable() {
        return mLongitude != null;
    }
            
    /**
     * Returns altitude expressed in meters, or null if value is unknown.
     * Zero indicates sea level. Positive values indicate an altitude above
     * sea level and negative values indicate an altitude below sea level.
     * @return altitude expressed in meters or null if unknown.
     */
    public Double getAltitude() {
        return mAltitude;
    }
    
    /**
     * Sets altitude expressed in meters. Null indicates that value is unknown.
     * @param altitude altitude expressed in meters or null if unknown.
     */
    public void setAltitude(final Double altitude) {
        mAltitude = altitude;
    }        
    
    /**
     * Indicates whether longitude coordinate is available.
     * @return true if available, false otherwise.
     */
    public boolean isAltitudeAvailable() {
        return mAltitude != null;
    }
}
