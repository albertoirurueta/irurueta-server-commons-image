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
 * Enumerator indicating light source used to take a picture.
 */
public enum LightSource {
    /**
     * Light source is unknown.
     */
    UNKNOWN(0),
    
    /**
     * Day light source.
     */
    DAYLIGHT(1),
    
    /**
     * Fluorescent light.
     */
    FLUORESCENT(2),
    
    /**
     * Tungsten incandescent light.
     */
    TUNGSTEN_INCANDESCENT_LIGHT(3),
    
    /**
     * Flash light.
     */
    FLASH(4),
    
    /**
     * Fine weather.
     */
    FINE_WEATHER(9),
    
    /**
     * Cloudy weather.
     */
    CLOUDY_WEATHER(10),
    
    /**
     * Shade.
     */
    SHADE(11),
    
    /**
     * Daylight fluorescent.
     */
    DAYLIGHT_FLUORESCENT_D_5700_7100K(12),
    
    /**
     * Day white fluorescent.
     */
    DAY_WHITE_FLUORESCENT_N_4600_5400K(13),
    
    /**
     * Cool white fluorescent.
     */
    COOL_WHITE_FLUORESCENT_W_3900_4500K(14),
    
    /**
     * White fluorescent.
     */
    WHITE_FLUORESCENT_WW_3200_3700K(15),
    
    /**
     * Standard light A.
     */
    STANDARD_LIGHT_A(17),
    
    /**
     * Standard light B.
     */
    STANDARD_LIGHT_B(18),
    
    /**
     * Standard light C.
     */
    STANDARD_LIGHT_C(19),
    
    /**
     * D55.
     */
    D55(20),
    
    /**
     * D65.
     */
    D65(21),
    
    /**
     * D75.
     */
    D75(22),
    
    /**
     * D50.
     */
    D50(23),
    
    /**
     * ISO studio tungsten.
     */
    ISO_STUDIO_TUNGSTEN(24),
    
    /**
     * Other unknown light source.
     */
    OTHER_LIGHT_SOURCE(255);
    
    /**
     * Integer representation of a light source.
     */
    private final int mValue;
    
    /**
     * Constructor.
     * @param value integer representation of a light source.
     */
    LightSource(int value) {
        mValue = value;
    }
    
    /**
     * Returns integer representation of a light source.
     * @return integer representation of a light source.
     */
    public int getValue() {
        return mValue;
    }
    
    /**
     * Builds a LightSource instance from its corresponding integer 
     * representation.
     * @param value integer representation of a light source.
     * @return a light source instance.
     */
    public static LightSource fromValue(final int value) {
        switch (value) {
            case 0:
                return UNKNOWN;
            case 1:
                return DAYLIGHT;
            case 2:
                return FLUORESCENT;
            case 3:
                return TUNGSTEN_INCANDESCENT_LIGHT;
            case 4:
                return FLASH;
            case 9:
                return FINE_WEATHER;
            case 10:
                return CLOUDY_WEATHER;
            case 11:
                return SHADE;
            case 12:
                return DAYLIGHT_FLUORESCENT_D_5700_7100K;
            case 13:
                return DAY_WHITE_FLUORESCENT_N_4600_5400K;
            case 14:
                return COOL_WHITE_FLUORESCENT_W_3900_4500K;
            case 15:
                return WHITE_FLUORESCENT_WW_3200_3700K;
            case 17:
                return STANDARD_LIGHT_A;
            case 18:
                return STANDARD_LIGHT_B;
            case 19:
                return STANDARD_LIGHT_C;
            case 20:
                return D55;
            case 21:
                return D65;
            case 22:
                return D75;
            case 23:
                return D50;
            case 24:
                return ISO_STUDIO_TUNGSTEN;
            case 255:
            default:
                return OTHER_LIGHT_SOURCE;
        }
    }
}
