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

import static org.junit.Assert.*;

public class ImageReaderResultTest {

    @Test
    public void testConstructor() {
        final ImageReaderResult result = new ImageReaderResult();

        // check default values
        assertFalse(result.isValid());
        assertNull(result.getCrc());
        assertNull(result.getMd5());
        assertEquals(result.getFileLength(), 0);
        assertEquals(result.getLastModified(), 0);
        assertNull(result.getContentType());
        assertNull(result.getMetadata());
        assertEquals(result.getImageFormat(), ImageFormat.UNKNOWN);
    }

    @Test
    public void testIsSetValid() {
        final ImageReaderResult result = new ImageReaderResult();

        // check default value
        assertFalse(result.isValid());

        // set new value
        result.setValid(true);

        // check correctness
        assertTrue(result.isValid());
    }

    @Test
    public void testGetSetCrc() {
        final ImageReaderResult result = new ImageReaderResult();

        assertNull(result.getCrc());

        // set new crc
        result.setCrc(1L);

        // check correctness
        assertEquals(result.getCrc(), Long.valueOf(1));
    }

    @Test
    public void testGetSetMd5() {
        final ImageReaderResult result = new ImageReaderResult();

        assertNull(result.getMd5());

        // set new md5
        result.setMd5("abcd");

        // check correctness
        assertEquals(result.getMd5(), "abcd");
    }

    @Test
    public void testGetSetFileLength() {
        final ImageReaderResult result = new ImageReaderResult();

        assertEquals(result.getFileLength(), 0);

        // set new value
        result.setFileLength(1);

        // check correctness
        assertEquals(result.getFileLength(), 1);
    }

    @Test
    public void testGetSetLastModified() {
        final ImageReaderResult result = new ImageReaderResult();

        assertEquals(result.getLastModified(), 0);

        // set new value
        result.setLastModified(1);

        // check correctness
        assertEquals(result.getLastModified(), 1);
    }

    @Test
    public void testGetSetContentType() {
        final ImageReaderResult result = new ImageReaderResult();

        assertNull(result.getContentType());

        // set new value
        result.setContentType("image/jpeg");

        // check correctness
        assertEquals(result.getContentType(), "image/jpeg");
    }

    @Test
    public void testGetSetMetadata() {
        final ImageReaderResult result = new ImageReaderResult();

        // set new value
        final ImageMetadata metadata = new ImageMetadata();
        result.setMetadata(metadata);

        // check correctness
        assertEquals(result.getMetadata(), metadata);
    }

    @Test
    public void testGetSetImageFormat() {
        final ImageReaderResult result = new ImageReaderResult();

        // check default value
        assertEquals(result.getImageFormat(), ImageFormat.UNKNOWN);

        // set new value
        result.setImageFormat(ImageFormat.JPEG);

        // check correctness
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
    }
}