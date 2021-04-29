/*
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.irurueta.server.commons.image;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlashTest {

    @Test
    public void testFromValue() {
        assertEquals(Flash.fromValue(0x0000), Flash.FLASH_DID_NOT_FIRE);
        assertEquals(Flash.fromValue(0x0001), Flash.FLASH_FIRED);
        assertEquals(Flash.fromValue(0x0005),
                Flash.STROBE_RETURN_LIGHT_NOT_DETECTED);
        assertEquals(Flash.fromValue(0x0007),
                Flash.STROBE_RETURN_LIGHT_DETECTED);
        assertEquals(Flash.fromValue(0x0009),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE);
        assertEquals(Flash.fromValue(0x000D),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED);
        assertEquals(Flash.fromValue(0x000F),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED);
        assertEquals(Flash.fromValue(0x0010),
                Flash.FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE);
        assertEquals(Flash.fromValue(0x0018),
                Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertEquals(Flash.fromValue(0x0019),
                Flash.FLASH_FIRED_AUTO_MODE);
        assertEquals(Flash.fromValue(0x001D),
                Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED);
        assertEquals(Flash.fromValue(0x001F),
                Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED);
        assertEquals(Flash.fromValue(0x0020), Flash.NO_FLASH_FUNCTION);
        assertEquals(Flash.fromValue(0x0041),
                Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE);
        assertEquals(Flash.fromValue(0x0045),
                Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED);
        assertEquals(Flash.fromValue(0x0047),
                Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED);
        assertEquals(Flash.fromValue(0x0049),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE);
        assertEquals(Flash.fromValue(0x004D),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED);
        assertEquals(Flash.fromValue(0x004F),
                Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED);
        assertEquals(Flash.fromValue(0x0059),
                Flash.FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE);
        assertEquals(Flash.fromValue(0x005D),
                Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE);
        assertEquals(Flash.fromValue(0x005F),
                Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE);
        assertEquals(Flash.fromValue(0xFFFF), Flash.UNKNOWN);
    }

    @Test
    public void testGetValue() {
        assertEquals(Flash.FLASH_DID_NOT_FIRE.getValue(), 0x0000);
        assertEquals(Flash.FLASH_FIRED.getValue(), 0x0001);
        assertEquals(Flash.STROBE_RETURN_LIGHT_NOT_DETECTED.getValue(), 0x0005);
        assertEquals(Flash.STROBE_RETURN_LIGHT_DETECTED.getValue(), 0x0007);
        assertEquals(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE.getValue(),
                0x0009);
        assertEquals(Flash.
                FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED.
                getValue(), 0x000D);
        assertEquals(Flash.
                FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED.
                getValue(), 0x000F);
        assertEquals(Flash.FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE.getValue(),
                0x0010);
        assertEquals(Flash.FLASH_DID_NOT_FIRE_AUTO_MODE.getValue(), 0x0018);
        assertEquals(Flash.FLASH_FIRED_AUTO_MODE.getValue(), 0x0019);
        assertEquals(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED.
                getValue(), 0x001D);
        assertEquals(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED.
                getValue(), 0x001F);
        assertEquals(Flash.NO_FLASH_FUNCTION.getValue(), 0x0020);
        assertEquals(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE.getValue(),
                0x0041);
        assertEquals(Flash.
                FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED.
                getValue(), 0x0045);
        assertEquals(Flash.
                FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.
                getValue(), 0x0047);
        assertEquals(Flash.
                FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE.
                getValue(), 0x0049);
        assertEquals(Flash.
                FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED.
                getValue(), 0x004D);
        assertEquals(Flash.
                FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.
                getValue(), 0x004F);
        assertEquals(Flash.FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE.
                getValue(), 0x0059);
        assertEquals(Flash.
                FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE.
                getValue(), 0x005D);
        assertEquals(Flash.
                FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE.
                getValue(), 0x005F);
        assertEquals(Flash.UNKNOWN.getValue(), 0xFFFF);
    }

    @Test
    public void testIsFlashFired() {
        assertFalse(Flash.FLASH_DID_NOT_FIRE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED.isFlashFired());
        assertFalse(Flash.STROBE_RETURN_LIGHT_NOT_DETECTED.isFlashFired());
        assertFalse(Flash.STROBE_RETURN_LIGHT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED.isFlashFired());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE.isFlashFired());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_AUTO_MODE.isFlashFired());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED.isFlashFired());
        assertFalse(Flash.NO_FLASH_FUNCTION.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
                .isFlashFired());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE.isFlashFired());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE.isFlashFired());
        assertFalse(Flash.UNKNOWN.isFlashFired());
    }

    @Test
    public void testIsReturnLightDetected() {
        assertFalse(Flash.FLASH_DID_NOT_FIRE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED.isReturnLightDetected());
        assertFalse(Flash.STROBE_RETURN_LIGHT_NOT_DETECTED.isReturnLightDetected());
        assertTrue(Flash.STROBE_RETURN_LIGHT_DETECTED.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED.isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED.isReturnLightDetected());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_AUTO_MODE.isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED.isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED.isReturnLightDetected());
        assertFalse(Flash.NO_FLASH_FUNCTION.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED.isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
                .isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
                .isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE.isReturnLightDetected());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE
                .isReturnLightDetected());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE.isReturnLightDetected());
        assertFalse(Flash.UNKNOWN.isReturnLightDetected());
    }

    @Test
    public void testIsRedEyeReductionEnabled() {
        assertFalse(Flash.FLASH_DID_NOT_FIRE.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED.isRedEyeReductionEnabled());
        assertFalse(Flash.STROBE_RETURN_LIGHT_NOT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.STROBE_RETURN_LIGHT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_NOT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RETURN_LIGHT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_COMPULSORY_FLASH_MODE.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_DID_NOT_FIRE_AUTO_MODE.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED.isRedEyeReductionEnabled());
        assertFalse(Flash.NO_FLASH_FUNCTION.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_NOT_DETECTED
                .isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_COMPULSORY_FLASH_MODE_RED_EYE_REDUCTION_MODE_RETURN_LIGHT_DETECTED
                .isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RED_EYE_REDUCTION_MODE.isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_NOT_DETECTED_RED_EYE_REDUCTION_MODE
                .isRedEyeReductionEnabled());
        assertTrue(Flash.FLASH_FIRED_AUTO_MODE_RETURN_LIGHT_DETECTED_RED_EYE_REDUCTION_MODE.isRedEyeReductionEnabled());
        assertFalse(Flash.UNKNOWN.isRedEyeReductionEnabled());
    }
}
