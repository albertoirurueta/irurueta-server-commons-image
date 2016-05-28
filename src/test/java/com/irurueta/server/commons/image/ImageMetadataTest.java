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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImageMetadataTest {
    
    public ImageMetadataTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testConstructor(){
        ImageMetadata metadata = new ImageMetadata();
        
        assertEquals(metadata.getWidth(), 0, 0);
        assertEquals(metadata.getHeight(), 0, 0);
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
    public void testGetSetWidth(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertEquals(metadata.getWidth(), 0);
        
        //set new value
        metadata.setWidth(1024);
        
        //check correctness
        assertEquals(metadata.getWidth(), 1024);
    }
    
    @Test
    public void testGetSetHeight(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertEquals(metadata.getHeight(), 0);
        
        //set new value
        metadata.setHeight(1024);
        
        //check correctness
        assertEquals(metadata.getHeight(), 1024);
    }
    
    @Test
    public void testGetSetMaker(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getMaker());
        
        //set new value
        metadata.setMaker("Apple");
        
        //check correctness
        assertEquals(metadata.getMaker(), "Apple");
    }
    
    @Test
    public void testGetSetModel(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default alue
        assertNull(metadata.getModel());
        
        //set new value
        metadata.setModel("iPhone 5");
        
        //check correctness
        assertEquals(metadata.getModel(), "iPhone 5");
    }
    
    @Test
    public void testGetSetFocalLength(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getFocalLength());
        
        //set new value
        Double focalLength = 100.0;
        metadata.setFocalLength(focalLength);
        
        //check correctness
        assertEquals(metadata.getFocalLength(), focalLength);
    }
    
    @Test
    public void testGetSetFocalPlaneXResolution(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getFocalPlaneXResolution());
        
        //set new value
        Double value = 1000.0;
        metadata.setFocalPlaneXResolution(value);
        
        //check correctness
        assertEquals(metadata.getFocalPlaneXResolution(), value);
    }
    
    @Test
    public void testGetSetFocalPlaneYResolution(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getFocalPlaneYResolution());
        
        //set new value
        Double value = 1000.0;
        metadata.setFocalPlaneYResolution(value);
        
        //check correctness
        assertEquals(metadata.getFocalPlaneYResolution(), value);
    }    
    
    @Test
    public void testGetSetFocalPlaneResolutionUnit(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getFocalPlaneResolutionUnit());
        
        //set new value
        metadata.setFocalPlaneResolutionUnit(Unit.CENTIMETERS);
        
        //check correctness
        assertEquals(metadata.getFocalPlaneResolutionUnit(), Unit.CENTIMETERS);
    }
    
    @Test
    public void testGetSetOrientation(){
        ImageMetadata metadata = new ImageMetadata();
        
        //check default value
        assertNull(metadata.getOrientation());
        
        //set new value
        metadata.setOrientation(ImageOrientation.BOTTOM_LEFT);
        
        //check correctness
        assertEquals(metadata.getOrientation(), ImageOrientation.BOTTOM_LEFT);
    }
    
    @Test
    public void testGetSetLocation(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getLocation());
        
        //set value
        GPSCoordinates location = new GPSCoordinates();
        metadata.setLocation(location);
        
        //check correctness
        assertEquals(metadata.getLocation(), location);
    }    
    
    @Test
    public void testGetSetArtist(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getArtist());
        
        //set value
        metadata.setArtist("Alberto Irurueta");
        
        //check correctness
        assertEquals(metadata.getArtist(), "Alberto Irurueta");
    }
    
    @Test
    public void testGetSetCopyright(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getCopyright());
        
        //set value
        metadata.setCopyright("Copyright 2013");
        
        //check correctness
        assertEquals(metadata.getCopyright(), "Copyright 2013");
    }
    
    @Test
    public void testGetSetDocumentName(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getDocumentName());
        
        //set value
        metadata.setDocumentName("document");
        
        //check correctness
        assertEquals(metadata.getDocumentName(), "document");
    }
    
    @Test
    public void testGetSetHostComputer(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getHostComputer());
        
        //set value
        metadata.setHostComputer("computer");
        
        //check correctness
        assertEquals(metadata.getHostComputer(), "computer");
    }
    
    @Test
    public void testGetSetImageDescription(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getImageDescription());
        
        //set value
        metadata.setImageDescription("title");
        
        //check correctness
        assertEquals(metadata.getImageDescription(), "title");
    }
    
    @Test
    public void testGetSetSoftware(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getSoftware());
        
        //set value
        metadata.setSoftware("Mac OS X 10.8");
        
        //check correctness
        assertEquals(metadata.getSoftware(), "Mac OS X 10.8");
    }
    
    @Test
    public void testGetSetTargetPrinter(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getTargetPrinter());
        
        //set value
        metadata.setTargetPrinter("Canon Pixma");
        
        //check correctness
        assertEquals(metadata.getTargetPrinter(), "Canon Pixma");
    }
    
    @Test
    public void testGetSetCameraSerialNumber(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getCameraSerialNumber());
        
        //set value
        metadata.setCameraSerialNumber("1234ABCD");
        
        //check correctness
        assertEquals(metadata.getCameraSerialNumber(), "1234ABCD");
    }
    
    @Test
    public void testGetSetDigitalZoomRatio(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getDigitalZoomRatio());
        
        //set value
        Double zoom = 2.0;
        metadata.setDigitalZoomRatio(zoom);
        
        //check correctness
        assertEquals(metadata.getDigitalZoomRatio(), zoom);
    }
    
    @Test
    public void testGetSetExposureTime(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getExposureTime());
        
        //set value
        Double exposure = 0.5;
        metadata.setExposureTime(exposure);
        
        //check correctness
        assertEquals(metadata.getExposureTime(), exposure);
    }
    
    @Test
    public void testGetSetFlash(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getFlash());
        
        //set value
        metadata.setFlash(Flash.FLASH_FIRED);
        
        //check correctness
        assertEquals(metadata.getFlash(), Flash.FLASH_FIRED);
    }
    
    @Test
    public void testGetsetFlashEnergy(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getFlashEnergy());
        
        //set value
        Double flashEnergy = 10.0;
        metadata.setFlashEnergy(flashEnergy);
        
        //check correctness
        assertEquals(metadata.getFlashEnergy(), flashEnergy);
    }
    
    @Test
    public void testGetSetFNumber(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getFNumber());
        
        //set value
        Double F = 22.0;
        metadata.setFNumber(F);
        
        //check correctness
        assertEquals(metadata.getFNumber(), F);
    }
    
    @Test
    public void testGetSetFocalLengthIn35mmFilm(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getFocalLengthIn35mmFilm());
        
        //set value
        Double focalLength35mm = 200.0; //mm
        metadata.setFocalLengthIn35mmFilm(focalLength35mm);
        
        //check correctness
        assertEquals(metadata.getFocalLengthIn35mmFilm(), focalLength35mm);
    }
    
    @Test
    public void testGetSetUniqueCameraModel(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getUniqueCameraModel());
        
        //set value
        metadata.setUniqueCameraModel("Apple iPhone 4S");
        
        //check correctness
        assertEquals(metadata.getUniqueCameraModel(), "Apple iPhone 4S");
    }
    
    @Test
    public void testGetSetSubjectDistance(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getSubjectDistance());
        
        //set value
        Double distance = 2.0; //m
        metadata.setSubjectDistance(distance);
        
        //check correctness
        assertEquals(metadata.getSubjectDistance(), distance);
    }
    
    @Test
    public void testGetSetShutterSpeedValue(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getShutterSpeedValue());
        
        //set value
        Double speed = 0.1; //seconds
        metadata.setShutterSpeedValue(speed);
        
        //check correctness
        assertEquals(metadata.getShutterSpeedValue(), speed);
    }
    
    @Test
    public void testGetSetISO(){
        ImageMetadata metadata = new ImageMetadata();
        
        //assert null
        assertNull(metadata.getISO());
        
        //set value
        Integer iso = 9000;
        metadata.setISO(iso);
        
        //check correctness
        assertEquals(metadata.getISO(), iso);
    }
}