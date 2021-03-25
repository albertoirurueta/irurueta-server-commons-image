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
 * Enumerator indicating possible measure units to be used in EXIF data.
 */
public enum Unit {
    /**
     * Units are not available.
     */
    NOT_AVAILABLE(1),
    
    /**
     * Inches.
     */
    INCHES(2),
    
    /**
     * Centimeters.
     */
    CENTIMETERS(3),
    
    /**
     * Units are defined but not recognized.
     */
    UNKNOWN(0);
    
    /**
     * Integer representation of units.
     */
    private final int mValue;
    
    /**
     * Constructor.
     * @param value integer representation of units.
     */
    Unit(final int value) {
        mValue = value;
    }
    
    /**
     * Returns integer representation of units.
     * @return integer representation of units.
     */
    public int getValue() {
        return mValue;
    }
    
    /**
     * Obtains unit from its integer representation.
     * @param value integer representation.
     * @return unit.
     */
    public static Unit fromValue(final int value) {
        switch (value) {
            case 1:
                return NOT_AVAILABLE;
            case 2:
                return INCHES;
            case 3:
                return CENTIMETERS;
            case 0:
            default:
                return UNKNOWN;
        }
    }
}
