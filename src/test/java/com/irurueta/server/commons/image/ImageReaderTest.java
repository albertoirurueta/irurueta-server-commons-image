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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImageReaderTest {

    public static final int BUFFER_SIZE = 1024;

    public ImageReaderTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testGetInstanceAndIsComputeCrcEnabled(){
        ImageReader reader = ImageReader.getInstance();
        assertNotNull(reader);
    }
    
    @Test
    public void testGetSetComputeCrcEnabled(){
        ImageReader reader = ImageReader.getInstance();
        
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        
        assertEquals(reader.isComputeCrcEnabled(),
                ImageReader.DEFAULT_COMPUTE_CRC);
        
        //set inverse value
        reader.setComputeCrcEnabled(!ImageReader.DEFAULT_COMPUTE_CRC);
        
        //check correctness
        assertEquals(reader.isComputeCrcEnabled(), 
                !ImageReader.DEFAULT_COMPUTE_CRC);
    }
    
    @Test
    public void testGetSetComputeMd5Enabled(){
        ImageReader reader = ImageReader.getInstance();
        
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        assertEquals(reader.isComputeMd5Enabled(),
                ImageReader.DEFAULT_COMPUTE_MD5);
        
        //set inverse value
        reader.setComputeMd5Enabled(!ImageReader.DEFAULT_COMPUTE_MD5);
        
        //check correctness
        assertEquals(reader.isComputeMd5Enabled(),
                !ImageReader.DEFAULT_COMPUTE_MD5);
    }
    
    @Test
    public void testReadBatllo1() throws InvalidImageException, IOException, 
            NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/batllo1.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 4000);
        assertEquals(metadata.getHeight(), 3000);
        assertEquals(metadata.getMaker(), "Canon");
        assertEquals(metadata.getModel(), "Canon PowerShot SX200 IS");
        assertEquals(metadata.getFocalLength(), 5.0, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation().getValue(), 1);
        assertNull(metadata.getLocation());
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertEquals(metadata.getImageDescription(), "");
        assertNull(metadata.getSoftware());
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertEquals(metadata.getDigitalZoomRatio(), 1.0, 0.0);
        assertEquals(metadata.getExposureTime(), 0.0015625, 0.0);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 3.4, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 9.3125, 0.0);
        assertEquals(metadata.getISO().intValue(), 160);        
    }

    @Test
    public void testReadAbishek() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/abishek.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 1936);
        assertEquals(metadata.getHeight(), 2592);
        assertNull(metadata.getMaker());
        assertNull(metadata.getModel());
        assertNull(metadata.getFocalLength());
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation().getValue(), 6);
        assertNull(metadata.getLocation());
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertNull(metadata.getSoftware());
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertNull(metadata.getExposureTime());
        assertNull(metadata.getFlash());
        assertNull(metadata.getFlashEnergy());
        assertNull(metadata.getFNumber());
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertNull(metadata.getShutterSpeedValue());
        assertNull(metadata.getISO());
    }
    
    @Test
    public void testReadCarlos1() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/carlos1.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 1024);
        assertEquals(metadata.getHeight(), 765);
        assertEquals(metadata.getMaker(), "Apple");
        assertEquals(metadata.getModel(), "iPhone");
        assertEquals(metadata.getFocalLength(), 3.85, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation().getValue(), 1);
        assertNotNull(metadata.getLocation());
        assertEquals(metadata.getLocation().getLatitude(), 41.393, 0.0001);
        assertEquals(metadata.getLocation().getLongitude(), 2.1625, 0.00001);
        assertNull(metadata.getLocation().getAltitude());
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertEquals(metadata.getHostComputer(), "Mac OS X 10.6.4");
        assertEquals(metadata.getImageDescription(), "Back Camera");
        assertEquals(metadata.getSoftware(), "QuickTime 7.6.6");
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertEquals(metadata.getExposureTime(), 0.066, 0.001);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 2.4, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 3.9112, 
                0.0);
        assertEquals(metadata.getISO().intValue(), 125);
    }

    @Test
    public void testReadRotate1() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate1.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 2592);
        assertEquals(metadata.getHeight(), 1936);
        assertEquals(metadata.getMaker(), "Apple");
        assertEquals(metadata.getModel(), "iPhone 4");
        assertEquals(metadata.getFocalLength(), 3.85, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation(), ImageOrientation.BOTTOM_RIGHT);
        assertNotNull(metadata.getLocation());
        assertEquals(metadata.getLocation().getLatitude(), 41.3935, 0.0001);
        assertEquals(metadata.getLocation().getLongitude(), 2.16333, 0.00001);        
        assertEquals(metadata.getLocation().getAltitude(), 46.0111856, 0.0001);
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertEquals(metadata.getSoftware(), "5.0");
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertEquals(metadata.getExposureTime(), 0.066, 0.001);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 2.8, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 3.9112, 0.0);
        assertEquals(metadata.getISO().intValue(), 80);
    }

    @Test
    public void testReadRotate2() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate2.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 1936);
        assertEquals(metadata.getHeight(), 2592);
        assertEquals(metadata.getMaker(), "Apple");
        assertEquals(metadata.getModel(), "iPhone 4");
        assertEquals(metadata.getFocalLength(), 3.85, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation(), ImageOrientation.RIGHT_TOP);
        assertNotNull(metadata.getLocation());
        assertEquals(metadata.getLocation().getLatitude(), 41.39333, 0.0001);
        assertEquals(metadata.getLocation().getLongitude(), 2.163, 0.00001);        
        assertEquals(metadata.getLocation().getAltitude(), 46.24472, 0.0001);
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertEquals(metadata.getSoftware(), "5.0");
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertEquals(metadata.getExposureTime(), 0.033, 0.001);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 2.8, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 4.907806, 0.001);
        assertEquals(metadata.getISO().intValue(), 100);
    }

    @Test
    public void testReadRotate3() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate3.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 2592);
        assertEquals(metadata.getHeight(), 1936);
        assertEquals(metadata.getMaker(), "Apple");
        assertEquals(metadata.getModel(), "iPhone 4");
        assertEquals(metadata.getFocalLength(), 3.85, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation(), ImageOrientation.TOP_LEFT);
        assertNotNull(metadata.getLocation());
        assertEquals(metadata.getLocation().getLatitude(), 41.39333, 0.0001);
        assertEquals(metadata.getLocation().getLongitude(), 2.163, 0.00001);        
        assertEquals(metadata.getLocation().getAltitude(), 46.24472, 0.0001);
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertEquals(metadata.getSoftware(), "5.0");
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertEquals(metadata.getExposureTime(), 0.041666, 0.001);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 2.8, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 4.58596, 0.001);
        assertEquals(metadata.getISO().intValue(), 100);
    }

    @Test
    public void testReadRotate4() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/rotate4.jpg");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/jpeg");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.JPEG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 1936);
        assertEquals(metadata.getHeight(), 2592);
        assertEquals(metadata.getMaker(), "Apple");
        assertEquals(metadata.getModel(), "iPhone 4");
        assertEquals(metadata.getFocalLength(), 3.85, 0.001);
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertEquals(metadata.getOrientation(), ImageOrientation.LEFT_BOTTOM);
        assertNotNull(metadata.getLocation());
        assertEquals(metadata.getLocation().getLatitude(), 41.39333, 0.0001);
        assertEquals(metadata.getLocation().getLongitude(), 2.163, 0.00001);        
        assertEquals(metadata.getLocation().getAltitude(), 46.24472, 0.0001);
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertEquals(metadata.getSoftware(), "5.0");
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertEquals(metadata.getExposureTime(), 0.041666, 0.001);
        assertEquals(metadata.getFlash(), Flash.FLASH_DID_NOT_FIRE_AUTO_MODE);
        assertNull(metadata.getFlashEnergy());
        assertEquals(metadata.getFNumber(), 2.8, 0.0);
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertEquals(metadata.getShutterSpeedValue(), 4.58596, 0.001);
        assertEquals(metadata.getISO().intValue(), 100);
    }

    @Test
    public void testReadSvalvard() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/Svalbard.bmp");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/x-ms-bmp");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.BMP);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 740);
        assertEquals(metadata.getHeight(), 1181);
        assertNull(metadata.getMaker());
        assertNull(metadata.getModel());
        assertNull(metadata.getFocalLength());
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertNull(metadata.getOrientation());
        assertNull(metadata.getLocation());
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertNull(metadata.getSoftware());
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertNull(metadata.getExposureTime());
        assertNull(metadata.getFlash());
        assertNull(metadata.getFlashEnergy());
        assertNull(metadata.getFNumber());
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertNull(metadata.getShutterSpeedValue());
        assertNull(metadata.getISO());
    }

    @Test
    public void testReadPolo() throws InvalidImageException, IOException, 
    NoSuchAlgorithmException{
        File f = new File(
                "./src/test/java/com/irurueta/server/commons/image/polo.png");
        
        //reset DEFAULT_COMPUTE_CRC to its default value
        ImageReader reader = ImageReader.getInstance();
        reader.setComputeCrcEnabled(ImageReader.DEFAULT_COMPUTE_CRC);
        reader.setComputeMd5Enabled(ImageReader.DEFAULT_COMPUTE_MD5);
        
        ImageReaderResult result = reader.readImage(f);
        
        assertEquals(result.getContentType(), "image/png");
        assertEquals(result.getCrc().longValue(), computeCRC32(f));
        assertEquals(result.getMd5(), computeMd5(f));
        assertEquals(result.getFileLength(), f.length());
        assertEquals(result.getImageFormat(), ImageFormat.PNG);
        assertEquals(result.getLastModified(), f.lastModified());
        
        ImageMetadata metadata = result.getMetadata();
        assertEquals(metadata.getWidth(), 1258);
        assertEquals(metadata.getHeight(), 1639);
        assertNull(metadata.getMaker());
        assertNull(metadata.getModel());
        assertNull(metadata.getFocalLength());
        assertNull(metadata.getFocalPlaneXResolution());
        assertNull(metadata.getFocalPlaneYResolution());
        assertNull(metadata.getFocalPlaneResolutionUnit());
        assertNull(metadata.getOrientation());
        assertNull(metadata.getLocation());
        
        assertNull(metadata.getArtist());
        assertNull(metadata.getCopyright());
        assertNull(metadata.getDocumentName());
        assertNull(metadata.getHostComputer());
        assertNull(metadata.getImageDescription());
        assertNull(metadata.getSoftware());
        assertNull(metadata.getTargetPrinter());
        assertNull(metadata.getCameraSerialNumber());
        assertNull(metadata.getDigitalZoomRatio());
        assertNull(metadata.getExposureTime());
        assertNull(metadata.getFlash());
        assertNull(metadata.getFlashEnergy());
        assertNull(metadata.getFNumber());
        assertNull(metadata.getFocalLengthIn35mmFilm());
        assertNull(metadata.getUniqueCameraModel());
        assertNull(metadata.getSubjectDistance());
        assertNull(metadata.getShutterSpeedValue());
        assertNull(metadata.getISO());
    }
    
    protected static long computeCRC32(File f) throws IOException{
        long crc;
        try (InputStream stream = new FileInputStream(f)) {
            crc = computeCRC32(stream);
        }
        return crc;
    }
    
    protected static long computeCRC32(InputStream stream) throws IOException{
        CRC32 crc = new CRC32();
        byte [] buffer = new byte[BUFFER_SIZE];
        int n;
        while((n = stream.read(buffer)) > 0){
            crc.update(buffer, 0, n);
        }
        return crc.getValue();
    }
    
    protected static String computeMd5(File f) throws IOException, 
            NoSuchAlgorithmException{
        String md5;
        try (InputStream stream = new FileInputStream(f)) {
            md5 = computeMd5(stream);
        }
        return md5;
    }
    
    protected static String computeMd5(InputStream stream) throws IOException, 
            NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte [] buffer = new byte[BUFFER_SIZE];
        int n;
        while((n = stream.read(buffer)) > 0){
            digest.update(buffer, 0, n);
        }
        return Base64.encodeBase64String(digest.digest());
    }
}