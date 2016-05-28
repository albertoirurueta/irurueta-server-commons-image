/**
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GPSCoordinatesTest {
    
    public GPSCoordinatesTest() { }
    
    @BeforeClass
    public static void setUpClass() { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }

    @Test
    public void testConstructor(){
        //test empty constructor
        GPSCoordinates coordinates = new GPSCoordinates();
        assertNull(coordinates.getLatitude());
        assertNull(coordinates.getLongitude());
        assertFalse(coordinates.isLatitudeAvailable());
        assertFalse(coordinates.isLongitudeAvailable());
        assertFalse(coordinates.isAltitudeAvailable());
        
        //test constructor with latitude and longitude
        Double latitude = 41.0;
        Double longitude = 2.0;
        coordinates = new GPSCoordinates(latitude, longitude);
        
        assertEquals(coordinates.getLatitude(), latitude);
        assertEquals(coordinates.getLongitude(), longitude);
        assertNull(coordinates.getAltitude());
        assertTrue(coordinates.isLatitudeAvailable());
        assertTrue(coordinates.isLongitudeAvailable());
        assertFalse(coordinates.isAltitudeAvailable());
        
        //test constructor with latitude, longitude and altitude
        Double altitude = 100.0;
        coordinates = new GPSCoordinates(latitude, longitude, altitude);
        
        assertEquals(coordinates.getLatitude(), latitude);
        assertEquals(coordinates.getLongitude(), longitude);
        assertEquals(coordinates.getAltitude(), altitude);
        assertTrue(coordinates.isLatitudeAvailable());
        assertTrue(coordinates.isLongitudeAvailable());
        assertTrue(coordinates.isAltitudeAvailable());
    }
    
    @Test
    public void testGetSetLatitude(){
        Double latitude = 41.0;
        Double longitude = 2.0;
        GPSCoordinates coordinates = new GPSCoordinates(latitude, 
                longitude);

        //check default value
        assertEquals(coordinates.getLatitude(), latitude);
        assertTrue(coordinates.isLatitudeAvailable());
        
        //set new value
        Double latitude2 = 0.0;
        coordinates.setLatitude(latitude2);
        
        //check correctness
        assertEquals(coordinates.getLatitude(), latitude2);
        assertTrue(coordinates.isLatitudeAvailable());        
    }
    
    @Test
    public void testGetSetLongitude(){
        Double latitude = 41.0;
        Double longitude = 2.0;
        GPSCoordinates coordinates = new GPSCoordinates(latitude, 
                longitude);

        //check default value
        assertEquals(coordinates.getLongitude(), longitude);
        assertTrue(coordinates.isLongitudeAvailable());
        
        //set new value
        Double longitude2 = 0.0;
        coordinates.setLongitude(longitude2);
        
        //check correctness
        assertEquals(coordinates.getLongitude(), longitude2);
        assertTrue(coordinates.isLongitudeAvailable());
    }
    
    @Test
    public void testGetSetAltitude(){
        Double latitude = 41.0;
        Double longitude = 2.0;
        GPSCoordinates coordinates = new GPSCoordinates(latitude, 
                longitude);

        //check default vlaue
        assertNull(coordinates.getAltitude());
        assertFalse(coordinates.isAltitudeAvailable());
        
        //set new vlaue
        Double altitude = 100.0;
        coordinates.setAltitude(altitude);
        
        //check correctness
        assertEquals(coordinates.getAltitude(), altitude);
        assertTrue(coordinates.isAltitudeAvailable());
    }    }
