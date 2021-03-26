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

public class UnitTest {

    @Test
    public void testFromValue() {
        assertEquals(Unit.fromValue(1), Unit.NOT_AVAILABLE);
        assertEquals(Unit.fromValue(2), Unit.INCHES);
        assertEquals(Unit.fromValue(3), Unit.CENTIMETERS);
        assertEquals(Unit.fromValue(0), Unit.UNKNOWN);
        assertEquals(Unit.fromValue(-1), Unit.UNKNOWN);
    }

    @Test
    public void testGetValue() {
        assertEquals(Unit.NOT_AVAILABLE.getValue(), 1);
        assertEquals(Unit.INCHES.getValue(), 2);
        assertEquals(Unit.CENTIMETERS.getValue(), 3);
        assertEquals(Unit.UNKNOWN.getValue(), 0);
    }
}
