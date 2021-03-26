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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageOrientationTest {

    @Test
    public void testFromValue() {
        assertEquals(ImageOrientation.fromValue(1), ImageOrientation.TOP_LEFT);
        assertEquals(ImageOrientation.fromValue(2), ImageOrientation.TOP_RIGHT);
        assertEquals(ImageOrientation.fromValue(3),
                ImageOrientation.BOTTOM_RIGHT);
        assertEquals(ImageOrientation.fromValue(4),
                ImageOrientation.BOTTOM_LEFT);
        assertEquals(ImageOrientation.fromValue(5), ImageOrientation.LEFT_TOP);
        assertEquals(ImageOrientation.fromValue(6), ImageOrientation.RIGHT_TOP);
        assertEquals(ImageOrientation.fromValue(7),
                ImageOrientation.RIGHT_BOTTOM);
        assertEquals(ImageOrientation.fromValue(8),
                ImageOrientation.LEFT_BOTTOM);
        assertEquals(ImageOrientation.fromValue(0), ImageOrientation.UNKNOWN);
        assertEquals(ImageOrientation.fromValue(-1), ImageOrientation.UNKNOWN);
    }

    @Test
    public void testGetValue() {
        assertEquals(ImageOrientation.TOP_LEFT.getValue(), 1);
        assertEquals(ImageOrientation.TOP_RIGHT.getValue(), 2);
        assertEquals(ImageOrientation.BOTTOM_RIGHT.getValue(), 3);
        assertEquals(ImageOrientation.BOTTOM_LEFT.getValue(), 4);
        assertEquals(ImageOrientation.LEFT_TOP.getValue(), 5);
        assertEquals(ImageOrientation.RIGHT_TOP.getValue(), 6);
        assertEquals(ImageOrientation.RIGHT_BOTTOM.getValue(), 7);
        assertEquals(ImageOrientation.LEFT_BOTTOM.getValue(), 8);
        assertEquals(ImageOrientation.UNKNOWN.getValue(), 0);
    }
}