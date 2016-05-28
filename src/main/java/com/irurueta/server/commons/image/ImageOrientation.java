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

/**
 * Enumerator specified possible image orientations.
 * The supported values of orientation are:
 * 1 = Top left orientation.
 * 2 = Top right orientation.
 * 3 = Bottom right orientation (image has to be rotated 180º).
 * 4 = Bottom left orientation.
 * 5 = left top orientation.
 * 6 = right top orientation (image has to be rotated 90º clockwise).
 * 7 = right bottom orientation.
 * 8 = left bottom orientation (image has to be rotated 90º counterclockwise).
 */
public enum ImageOrientation {
    /**
     * Rotation is not known.
     */
    UNKNOWN(0),
    
    /**
     * Top left orientation.
     */
    TOP_LEFT(1),
    
    /**
     * Top right orientation.
     */
    TOP_RIGHT(2),
    
    /**
     * Bottom right orientation (image has to be rotated 180º).
     */
    BOTTOM_RIGHT(3),
    
    /**
     * Bottom left orientation.
     */
    BOTTOM_LEFT(4),
    
    /**
     * Left top orientation.
     */
    LEFT_TOP(5),
    
    /**
     * Right top orientation (image has to be rotated 90º clockwise).
     */
    RIGHT_TOP(6),
    
    /**
     * Right bottom orientation.
     */
    RIGHT_BOTTOM(7),
    
    /**
     * Left bottom orientation (image has to be rotated 90º counterclockwise).
     */
    LEFT_BOTTOM(8);
    
    /**
     * Integer representation of image orientation.
     */
    private int mValue;
    
    /**
     * Constructor.
     * @param value integer representation of image orientation.
     */
    private ImageOrientation(int value){
        mValue = value;
    }
    
    /**
     * Returns integer representation of image orientation.
     * @return integer representation of image orientation.
     */
    public int getValue(){
        return mValue;
    }
    
    /**
     * Obtains image orientation from provided integer representation.
     * @param value integer representation of image orientation.
     * @return image orientation.
     */
    public static ImageOrientation fromValue(int value){
        switch(value){
            case 1:
                return TOP_LEFT;
            case 2:
                return TOP_RIGHT;
            case 3:
                return BOTTOM_RIGHT;
            case 4:
                return BOTTOM_LEFT;
            case 5:
                return LEFT_TOP;
            case 6:
                return RIGHT_TOP;
            case 7:
                return RIGHT_BOTTOM;
            case 8:
                return LEFT_BOTTOM;
            case 0:
            default:
                return UNKNOWN;
        }
    }
}
