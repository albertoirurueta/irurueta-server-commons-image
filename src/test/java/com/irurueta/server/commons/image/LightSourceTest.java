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

public class LightSourceTest {
    
    public LightSourceTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testFromValue(){
        assertEquals(LightSource.fromValue(0), LightSource.UNKNOWN);
        assertEquals(LightSource.fromValue(1), LightSource.DAYLIGHT);
        assertEquals(LightSource.fromValue(2), LightSource.FLUORESCENT);
        assertEquals(LightSource.fromValue(3), 
                LightSource.TUNGSTEN_INCANDESCENT_LIGHT);
        assertEquals(LightSource.fromValue(4), LightSource.FLASH);
        assertEquals(LightSource.fromValue(9), LightSource.FINE_WEATHER);
        assertEquals(LightSource.fromValue(10), LightSource.CLOUDY_WEATHER);
        assertEquals(LightSource.fromValue(11), LightSource.SHADE);
        assertEquals(LightSource.fromValue(12), 
                LightSource.DAYLIGHT_FLUORESCENT_D_5700_7100K);
        assertEquals(LightSource.fromValue(13), 
                LightSource.DAY_WHITE_FLUORESCENT_N_4600_5400K);
        assertEquals(LightSource.fromValue(14), 
                LightSource.COOL_WHITE_FLUORESCENT_W_3900_4500K);
        assertEquals(LightSource.fromValue(15), 
                LightSource.WHITE_FLUORESCENT_WW_3200_3700K);
        assertEquals(LightSource.fromValue(17), LightSource.STANDARD_LIGHT_A);
        assertEquals(LightSource.fromValue(18), LightSource.STANDARD_LIGHT_B);
        assertEquals(LightSource.fromValue(19), LightSource.STANDARD_LIGHT_C);
        assertEquals(LightSource.fromValue(20), LightSource.D55);
        assertEquals(LightSource.fromValue(21), LightSource.D65);
        assertEquals(LightSource.fromValue(22), LightSource.D75);
        assertEquals(LightSource.fromValue(23), LightSource.D50);
        assertEquals(LightSource.fromValue(24), 
                LightSource.ISO_STUDIO_TUNGSTEN);
        assertEquals(LightSource.fromValue(255), 
                LightSource.OTHER_LIGHT_SOURCE);
        assertEquals(LightSource.fromValue(-1), 
                LightSource.OTHER_LIGHT_SOURCE);
    }
    
    @Test
    public void testGetValue(){
        assertEquals(LightSource.UNKNOWN.getValue(), 0);
        assertEquals(LightSource.DAYLIGHT.getValue(), 1);
        assertEquals(LightSource.FLUORESCENT.getValue(), 2);
        assertEquals(LightSource.TUNGSTEN_INCANDESCENT_LIGHT.getValue(), 3);
        assertEquals(LightSource.FLASH.getValue(), 4);
        assertEquals(LightSource.FINE_WEATHER.getValue(), 9);
        assertEquals(LightSource.CLOUDY_WEATHER.getValue(), 10);
        assertEquals(LightSource.SHADE.getValue(), 11);
        assertEquals(LightSource.DAYLIGHT_FLUORESCENT_D_5700_7100K.getValue(), 
                12);
        assertEquals(LightSource.DAY_WHITE_FLUORESCENT_N_4600_5400K.getValue(),
                13);
        assertEquals(LightSource.COOL_WHITE_FLUORESCENT_W_3900_4500K.getValue(),
                14);
        assertEquals(LightSource.WHITE_FLUORESCENT_WW_3200_3700K.getValue(), 
                15);
        assertEquals(LightSource.STANDARD_LIGHT_A.getValue(), 17);
        assertEquals(LightSource.STANDARD_LIGHT_B.getValue(), 18);
        assertEquals(LightSource.STANDARD_LIGHT_C.getValue(), 19);
        assertEquals(LightSource.D55.getValue(), 20);
        assertEquals(LightSource.D65.getValue(), 21);
        assertEquals(LightSource.D75.getValue(), 22);
        assertEquals(LightSource.D50.getValue(), 23);
        assertEquals(LightSource.ISO_STUDIO_TUNGSTEN.getValue(), 24);
        assertEquals(LightSource.OTHER_LIGHT_SOURCE.getValue(), 255);
    }
}