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
 * Enumerator indicating flash modes of EXIF data contained in an image file.
 */
public enum Flash {
    
    /**
     * Flash did not fire.
     */
    FLASH_DID_NOT_FIRE(0x0000),
    
    /**
     * Flash fired.
     */
    FLASH_FIRED(0x0001),
    
    /**
     * Flash did not fire and strobe return light was not detected.
     */
    STROBE_RETURN_LIGHT_NOT_DETECTED(0x0005),
    
    /**
     * Flash did not fire and strobe light was detected.
     */
    STROBE_RETURN_LIGHT_DETECTED(0x0007),
    
    /**
     * Flash fired, compulsory flash mode.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE(0x0009),
    
    /**
     * Flash fired, compulsory flash mode, return light not detected.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED(0x000D),
    
    /**
     * Flash fired, compulsory flash mode, return light detected.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED(0x000F),
    
    /**
     * Flash did not fire, compulsory flash mode.
     */
    FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE(0x0010),
    
    /**
     * Flash did not fire, auto mode.
     */
    FLASH_DID_NOT_FIRE_AUTO_MODE(0x0018),
    
    /**
     * Flash fired, auto mode.
     */
    FLASH_FIRED_AUTO_MODE(0x0019),
    
    /**
     * Flash fired, auto mode, return light not detected.
     */
    FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED(0x001D),
    
    /**
     * Flash fired, auto mode, return light detected.
     */
    FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED(0x001F),
    
    /**
     * No flash function.
     */
    NO_FLASH_FUNCTION(0x0020),
    
    /**
     * Flash fired, red-eye reduction mode.
     */
    FLASH_FIRED_RED_EYE_REDUCTION_MODE(0x0041),
    
    /**
     * Flash fired, red-eye reduction mode, return light not detected.
     */
    FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED(0x0045),
    
    /**
     * Flash fired, red-eye reduction mode, return light detected.
     */
    FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED(0x0047),
    
    /**
     * Flash fired, compulsory flash mode, red-eye reduction mode.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE(0x0049),
    
    /**
     * Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED(0x004D),
    
    /**
     * Flash fired, compulsory flash mode, red-eye reduction mode, return light detected.
     */
    FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED(0x004F),
    
    /**
     * Flash fired, auto mode, red-eye reduction mode.
     */
    FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE(0x0059),
    
    /**
     * Flash fired, auto mode, return light not detected, red-eye reduction mode.
     */
    FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE(0x005D),
    
    /**
     * Flash fired, auto mode, return light detected, red-eye reduction mode.
     */
    FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE(0x005F),
    
    /**
     * Unknown flash mode.
     */
    UNKNOWN(0xFFFF);
    
    /**
     * Integer value representing flash mode.
     */
    private final int mValue;
    
    /**
     * Constructor.
     * @param value integer value representing flash mode.
     */
    Flash(final int value) {
        mValue = value;
    }
    
    /**
     * Integer value representing flash mode.
     * @return integer value representinf flash mode.
     */
    public int getValue() {
        return mValue;
    }
    
    /**
     * Indicates if flash was fired.
     * @return true if flash was fired, false otherwise.
     */
    public boolean isFlashFired() {
        switch (mValue) {
            case 0x0001: 
                //FLASH_FIRED
            case 0x0009: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE
            case 0x000D: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOTDETECTED
            case 0x000F: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED
            case 0x001D: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED
            case 0x001F: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED
            case 0x0041: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE
            case 0x0045: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
            case 0x0047: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x0049: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE
            case 0x004D: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
            case 0x004F: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x0059: 
                //FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE
            case 0x005D: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE
            case 0x005F: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE
                return true;
            default:
                return false;
        }        
    }
    
    /**
     * Indicates if stroboscopic return light to focus was detected or not.
     * @return true if return light was detected, false otherwise.
     */
    public boolean isReturnLightDetected() {
        switch (mValue) {
            case 0x0007: 
                //STROBE_RETURN_LIGHT_DETECTED
            case 0x000F: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED
            case 0x0019: 
                //FLASH_FIRED_AUTO_MODE
            case 0x001F: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED
            case 0x0047: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x004F: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x005F: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Indicates if red eye reduction mode is enabled or not.
     * @return true if red eye reduction mode is enabled, false otherwise.
     */
    public boolean isRedEyeReductionEnabled() {
        switch (mValue) {
            case 0x0041: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE
            case 0x0045: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
            case 0x0047: 
                //FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x0049: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE
            case 0x004D: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
            case 0x004F: 
                //FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
            case 0x0059: 
                //FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE
            case 0x005D: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE
            case 0x005F: 
                //FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE
                return true;
            default:
                return false;
        }            
    }
    
    /**
     * Creates an instance of this enumerator from its corresponding integer
     * value.
     * @param value integer representation of flash mode.
     * @return flash mode.
     */
    public static Flash fromValue(final int value) {
        switch (value) {
            case 0x0000:
                return FLASH_DID_NOT_FIRE;
            case 0x0001:
                return FLASH_FIRED;
            case 0x0005:
                return STROBE_RETURN_LIGHT_NOT_DETECTED;
            case 0x0007:
                return STROBE_RETURN_LIGHT_DETECTED;
            case 0x0009:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE;
            case 0x000D:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED;
            case 0x000F:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED;
            case 0x0010:
                return FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE;
            case 0x0018:
                return FLASH_DID_NOT_FIRE_AUTO_MODE;
            case 0x0019:
                return FLASH_FIRED_AUTO_MODE;
            case 0x001D:
                return FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED;
            case 0x001F:
                return FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED;
            case 0x0020:
                return NO_FLASH_FUNCTION;
            case 0x0041:
                return FLASH_FIRED_RED_EYE_REDUCTION_MODE;
            case 0x0045:
                return FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED;
            case 0x0047:
                return FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED;
            case 0x0049:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE;
            case 0x004D:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED;
            case 0x004F:
                return FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED;
            case 0x0059:
                return FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE;
            case 0x005D:
                return FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE;
            case 0x005F:
                return FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE;
            case 0xFFFF:
            default:
                return UNKNOWN;
        }    
    }        
}
