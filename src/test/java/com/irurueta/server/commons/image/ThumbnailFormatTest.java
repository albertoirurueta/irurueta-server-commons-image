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

public class ThumbnailFormatTest {

    @Test
    public void testFromValue() {
        assertEquals(ThumbnailFormat.fromValue("jpeg"), ThumbnailFormat.JPEG);
        assertEquals(ThumbnailFormat.fromValue("jpg"), ThumbnailFormat.JPEG);
        assertEquals(ThumbnailFormat.fromValue("png"), ThumbnailFormat.PNG);
        assertEquals(ThumbnailFormat.fromValue("gif"), ThumbnailFormat.GIF);
        assertEquals(ThumbnailFormat.fromValue("bmp"), ThumbnailFormat.BMP);
        assertEquals(ThumbnailFormat.fromValue("other"),
                ThumbnailFormat.UNKNOWN);
        assertEquals(ThumbnailFormat.fromValue(null), ThumbnailFormat.UNKNOWN);
    }

    @Test
    public void testFromImageFormat() {
        assertEquals(ThumbnailFormat.fromImageFormat(ImageFormat.JPEG),
                ThumbnailFormat.JPEG);
        assertEquals(ThumbnailFormat.fromImageFormat(ImageFormat.PNG),
                ThumbnailFormat.PNG);
        assertEquals(ThumbnailFormat.fromImageFormat(ImageFormat.GIF),
                ThumbnailFormat.GIF);
        assertEquals(ThumbnailFormat.fromImageFormat(ImageFormat.BMP),
                ThumbnailFormat.BMP);
        assertEquals(ThumbnailFormat.fromImageFormat(ImageFormat.UNKNOWN),
                ThumbnailFormat.UNKNOWN);
        assertEquals(ThumbnailFormat.fromImageFormat(null),
                ThumbnailFormat.UNKNOWN);
    }

    @Test
    public void testGetValue() {
        assertEquals(ThumbnailFormat.JPEG.getValue(), "jpeg");
        assertEquals(ThumbnailFormat.PNG.getValue(), "png");
        assertEquals(ThumbnailFormat.GIF.getValue(), "gif");
        assertEquals(ThumbnailFormat.BMP.getValue(), "bmp");
        assertEquals(ThumbnailFormat.UNKNOWN.getValue(), "unknown");
    }
}