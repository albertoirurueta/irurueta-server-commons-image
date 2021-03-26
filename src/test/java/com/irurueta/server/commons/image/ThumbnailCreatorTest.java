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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ThumbnailCreatorTest {

    public static final String FOLDER =
            "./src/test/java/com/irurueta/server/commons/image/tmp/";

    @BeforeClass
    public static void setUpClass() {
        //create folder for generated thumbnails
        File folder = new File(FOLDER);
        //noinspection ResultOfMethodCallIgnored
        folder.mkdirs();
    }

    @AfterClass
    public static void tearDownClass() {
        //remove any remaining files in thumbnails folder
        final File folder = new File(FOLDER);
        final File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                //noinspection ResultOfMethodCallIgnored
                f.delete();
            }
        }
        //delete created folder
        //noinspection ResultOfMethodCallIgnored
        folder.delete();
    }

    @Test
    public void testGetInstance() {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();
        assertNotNull(creator);
    }

    @Test
    public void testGetSetMaxConcurrentThreads() {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();
        assertEquals(creator.getMaxConcurrentThreads(),
                ThumbnailCreator.DEFAULT_MAX_CONCURRENT_THREADS);
    }

    @Test
    public void testGenerateAndSaveThumbnail1() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/batllo1.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final int origHeight = readerResult.getMetadata().getHeight();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 512;
        final File thumb = new File(FOLDER, "batlloThumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, origHeight, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        // delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail2() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/abishek.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final int origHeight = readerResult.getMetadata().getHeight();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 484;
        final int height = 648;
        final File thumb = new File(FOLDER, "abishekThumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, origHeight, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        //delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail3() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate1.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 383;
        final File thumb = new File(FOLDER, "rotate1Thumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        //delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail4() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate2.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 383;
        final int height = 512;
        final File thumb = new File(FOLDER, "rotate2Thumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        //delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail5() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate3.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 383;
        final File thumb = new File(FOLDER, "rotate3Thumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        //delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail6() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate4.jpg");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        final ImageFormat format = readerResult.getImageFormat();
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 383;
        final File thumb = new File(FOLDER, "rotate6Thumbnail.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        //delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail7() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/Svalbard.bmp");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        // transcode from bmp to jpg
        final ImageFormat format = ImageFormat.JPEG;
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 512;
        final File thumb = new File(FOLDER, "thumbnail7.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.JPEG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        // delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }

    @Test
    public void testGenerateAndSaveThumbnail8() throws InvalidImageException,
            IOException, InterruptedException {
        final ThumbnailCreator creator = ThumbnailCreator.getInstance();

        final File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/polo.png");

        // read image metadata
        ImageReaderResult readerResult = ImageReader.getInstance().readImage(f);
        final int origWidth = readerResult.getMetadata().getWidth();
        // transcode from bmp to jpg
        final ImageFormat format = ImageFormat.PNG;
        final ImageOrientation orientation =
                readerResult.getMetadata().getOrientation();

        final int width = 512;
        final int height = 512;
        final File thumb = new File(FOLDER, "thumbnail8.jpg");
        creator.generateAndSaveThumbnail(f, orientation, thumb, width, height,
                ThumbnailFormat.fromImageFormat(format));

        // check that thumbnail has requested size
        readerResult = ImageReader.getInstance().readImage(thumb);
        assertEquals(readerResult.getMetadata().getWidth(), width);
        assertEquals(readerResult.getMetadata().getHeight(), height);

        // Force IllegalArgumentException
        // (by using a negative size)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb, -width,
                    height, ThumbnailFormat.PNG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // (or by using a size larger than the original file)
        try {
            creator.generateAndSaveThumbnail(f, orientation, thumb,
                    origWidth + 1, height, ThumbnailFormat.PNG);
            fail("IllegalArgumentException expected but not thrown");
        } catch (final IllegalArgumentException ignore) {
        }

        // delete generated thumbnail file
        //noinspection ResultOfMethodCallIgnored
        thumb.delete();
    }
}