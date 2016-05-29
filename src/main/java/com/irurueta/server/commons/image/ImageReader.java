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

import static com.irurueta.server.commons.image.ImageOrientation.LEFT_BOTTOM;
import static com.irurueta.server.commons.image.ImageOrientation.RIGHT_TOP;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import org.apache.commons.codec.binary.Base64;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.RationalNumber;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.constants.TiffTagConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeRational;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeShort;

/**
 * Class to read image metadata.
 */
public class ImageReader {
    /**
     * Constant indicating if CRC is computed by default.
     */
    public static final boolean DEFAULT_COMPUTE_CRC = true;
    
    /**
     * Constant indicating if MD5 hash is computed by default.
     */
    public static final boolean DEFAULT_COMPUTE_MD5 = true;
    
    /**
     * Buffer size to compute CRC and MD5.
     */
    public static final int BUFFER_SIZE = 1024;
    
    /**
     * Reference to singleton instance of this class.
     */
    private static SoftReference<ImageReader> mReference;
    
    /**
     * Indicates if CRC will be computed when reading image metadata.
     */
    private volatile boolean mComputeCrc = DEFAULT_COMPUTE_CRC;
    
    /**
     * Indicates if MD5 will be computed when reading image metadata.
     */
    private volatile boolean mComputeMd5 = DEFAULT_COMPUTE_MD5;
        
    /**
     * Constructor.
     */
    private ImageReader() { }
    
    /**
     * Factory method. Creates or returns singleton instance.
     * @return singleton instance.
     */
    public static synchronized ImageReader getInstance() {
        ImageReader reader;
        if (mReference == null || (reader = mReference.get()) == null) {
            reader = new ImageReader();
            mReference = new SoftReference<>(reader);
        }
        return reader;
    }
    
    /**
     * Indicates if CRC computation is enabled.
     * @return true if CRC computation is enabled, false otherwise.
     */
    public synchronized boolean isComputeCrcEnabled() {
        return mComputeCrc;
    }
    
    /**
     * Specifies whether CRC computation is enabled.
     * @param computeCrc true if CRC computation must be enabled, false 
     * otherwise.
     */
    public synchronized void setComputeCrcEnabled(boolean computeCrc) {
        this.mComputeCrc = computeCrc;
    }
    
    /**
     * Indicates if MD5 hash computation is enabled.
     * @return true if MD5 computation is enabled, false otherwise.
     */
    public synchronized boolean isComputeMd5Enabled() {
        return mComputeMd5;
    }
    
    /**
     * Specifies whether MD5 hash computation is enabled.
     * @param computeMd5 true if MD5 computation must be enabled, false 
     * otherwise.
     */
    public synchronized void setComputeMd5Enabled(boolean computeMd5) {
        this.mComputeMd5 = computeMd5;
    }
    
    /**
     * Reads image metadata from provided image file.
     * @param f file containing an image in one of the supported formats (jpg,
     * png, gif or bmp).
     * @return result containing image metadata and image file information.
     * @throws InvalidImageException throws if file is corrupted, contains 
     * invalid data, is not an image or format is not supported.
     * @throws IOException if an I/O error occurs.
     */
    public ImageReaderResult readImage(File f) throws InvalidImageException, 
            IOException {
        try {
            ImageReaderResult result = new ImageReaderResult();
            ImageInfo imageInfo = Sanselan.getImageInfo(f);
            result.setValid(internalCheckValid(imageInfo));
            result.setFileLength(f.length());
            result.setLastModified(f.lastModified());
            
            //Read metadata
            result.setContentType(imageInfo.getMimeType());
            result.setImageFormat(getImageFormat(imageInfo));
            
            ImageMetadata metadata = new ImageMetadata();
            result.setMetadata(metadata);
            
            //if file is JPEG read its exif data
            if (imageInfo.getFormat() == 
                    org.apache.sanselan.ImageFormat.IMAGE_FORMAT_JPEG) {
                internalReadExif(f, imageInfo, metadata);
            } else {
                //if not, at least set image size so we can generate thumbnails
                metadata.setWidth(imageInfo.getWidth());
                metadata.setHeight(imageInfo.getHeight());
            }            
        
            computeCRCandMd5(f, result);
            return result;
        } catch (ImageReadException e) {
            throw new InvalidImageException(e);
        }
    }
    
    /**
     * Check if valid is one of the supported image formats.
     * @param f an image file.
     * @return true if file appears to be valid, false otherwise.
     * @throws InvalidImageException if image file is corrupted.
     * @throws IOException if an I/O error occurs.
     */
    public static boolean checkValidFile(File f) throws InvalidImageException, 
            IOException {
        try {
            ImageInfo imageInfo = Sanselan.getImageInfo(f);
            return internalCheckValid(imageInfo);
        } catch (IOException e) {
            throw e;
        } catch (Throwable t) {
            throw new InvalidImageException(t);
        }
    }
    
    /**
     * Computes CRC and MD5 hashes for provided file and the results get stored
     * in provided result instance.
     * @param f file to compute CRC and MD5 hashes.
     * @param result instance where CRC and MD5 will be stored.
     * @throws IOException if an I/O error occurs.
     */
    private void computeCRCandMd5(File f, ImageReaderResult result) 
            throws IOException {
        if (f == null || result == null) {
            return;
        }
        
        try (InputStream stream = new FileInputStream(f)) {
            CRC32 crc = null;
            if (mComputeCrc) {
                crc = new CRC32();
            }

            MessageDigest digest = null;
            if (mComputeMd5) {
                try {
                    digest = MessageDigest.getInstance("MD5");
                    digest.reset();
                } catch (NoSuchAlgorithmException e) {
                    digest = null;
                }
            }

            
            byte [] buffer = new byte[BUFFER_SIZE];
            int n;
            while ((n = stream.read(buffer)) > 0) {
                if (crc != null) {
                    crc.update(buffer, 0, n);
                }
                if (digest != null) {
                    digest.update(buffer, 0, n);
                }
            }
            
            if (crc != null) {
                result.setCrc(crc.getValue());
            }
            if (digest != null) {
                result.setMd5(Base64.encodeBase64String(digest.digest()));
            }            
        }
    }
                
    /**
     * Determines image format for a given image file.
     * @param imageInfo structure containing metadata of image file being read.
     * @return detected image format.
     */
    private com.irurueta.server.commons.image.ImageFormat getImageFormat(
            ImageInfo imageInfo) {
        org.apache.sanselan.ImageFormat format = imageInfo.getFormat();
        if (format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_JPEG) {
            return com.irurueta.server.commons.image.ImageFormat.JPEG;
        } else if (format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_PNG) {
            return com.irurueta.server.commons.image.ImageFormat.PNG;
        } else if (format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_GIF) {
            return com.irurueta.server.commons.image.ImageFormat.GIF;
        } else if (format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_BMP) {
            return com.irurueta.server.commons.image.ImageFormat.BMP;
        } else {
            return com.irurueta.server.commons.image.ImageFormat.UNKNOWN;
        }
    }
    
    /**
     * Indicates if image file corresponds to one of the supported image 
     * formats.
     * @param imageInfo structure containing metadata of image file being read.
     * @return if image file has one of the supported formats.
     */
    private static boolean internalCheckValid(ImageInfo imageInfo) {
        org.apache.sanselan.ImageFormat format = imageInfo.getFormat();
        return format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_JPEG ||
                format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_PNG ||
                format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_GIF ||
                format == org.apache.sanselan.ImageFormat.IMAGE_FORMAT_BMP;
    }   
    
    /**
     * Internal method in charge of reading image metadata and EXIF tags.
     * @param f image file.
     * @param imageInfo structure containing metadata of image file being read.
     * @param result structure where resulting image metadata will be stored.
     * @throws InvalidImageException if image file is corrupted or not 
     * supported.
     * @throws IOException if an I/O error occurs.
     */
    private void internalReadExif(File f, ImageInfo imageInfo, 
            ImageMetadata result) throws InvalidImageException, IOException {
        
        try {
            //store image size
            result.setWidth(imageInfo.getWidth());
            result.setHeight(imageInfo.getHeight());
            
            JpegImageMetadata metadata = 
                    (JpegImageMetadata)Sanselan.getMetadata(f);
            if (metadata == null) {
                return;
            }
            
            //Get maker
            TiffField makerField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_MAKE);
            String maker = null;
            if (makerField != null) {
                maker = makerField.getValueDescription();
            }
            if (maker != null) {
                result.setMaker(trim(maker));
            }
                
            //Get model
            TiffField modelField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_MODEL);
            String model = null;
            if (modelField != null) {
                model = modelField.getValueDescription();
            }
            if (model != null) {
                result.setModel(trim(model));
            }
            
            //Focal length
            TiffField focalLengthField = metadata.findEXIFValue(
                    TiffConstants.EXIF_TAG_FOCAL_LENGTH);
            if (focalLengthField != null && 
                    focalLengthField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = 
                        (RationalNumber)focalLengthField.getValue();
                if (number != null) {
                    result.setFocalLength(number.doubleValue());
                }
            }
            
            //Focal plane x resolution
            TiffField focalPlaneXResolutionField = metadata.findEXIFValue(
                    TiffConstants.EXIF_TAG_FOCAL_PLANE_XRESOLUTION);
            if (focalPlaneXResolutionField != null &&
                    focalPlaneXResolutionField.fieldType instanceof FieldTypeRational) {
                RationalNumber number =
                        (RationalNumber)focalPlaneXResolutionField.getValue();
                if (number != null) {
                    result.setFocalPlaneXResolution(number.doubleValue());
                }
            }

            //Focal plane y resolution
            TiffField focalPlaneYResolutionField = metadata.findEXIFValue(
                    TiffConstants.EXIF_TAG_FOCAL_PLANE_YRESOLUTION);
            if (focalPlaneYResolutionField != null &&
                    focalPlaneYResolutionField.fieldType instanceof FieldTypeRational) {
                RationalNumber number =
                        (RationalNumber)focalPlaneYResolutionField.getValue();
                if (number != null) {
                    result.setFocalPlaneYResolution(number.doubleValue());
                }
            }
            
            //Focal plane resolution unit
            TiffField focalPlaneResolutionUnitField = metadata.findEXIFValue(
                    TiffConstants.EXIF_TAG_FOCAL_PLANE_RESOLUTION_UNIT);
            if (focalPlaneResolutionUnitField != null && 
                    focalPlaneResolutionUnitField.fieldType instanceof FieldTypeShort) {
                Integer number = 
                        (Integer)focalPlaneResolutionUnitField.getValue();
                if (number != null) {
                    result.setFocalPlaneResolutionUnit(Unit.fromValue(number));
                }
            }
            
            //Orientation
            TiffField orientationField = metadata.findEXIFValue(
                    TiffConstants.EXIF_TAG_ORIENTATION);
            if (orientationField != null &&
                    orientationField.fieldType instanceof FieldTypeShort) {
                Integer number = (Integer)orientationField.getValue();
                if (number != null) {
                    result.setOrientation(ImageOrientation.fromValue(number));
                }
            }
            
            //gps latitude
            Double latitude = null;
            TiffField gpsLatitudeField = metadata.findEXIFValue(
                    TiffConstants.GPS_TAG_GPS_LATITUDE);
            if (gpsLatitudeField != null &&
                    gpsLatitudeField.fieldType instanceof FieldTypeRational) {
                RationalNumber gpsLatitude[] = 
                        (RationalNumber[])gpsLatitudeField.getValue();
                
                if (gpsLatitude != null && gpsLatitude.length >= 3) {
                    RationalNumber gpsLatitudeDegrees = gpsLatitude[0];
                    RationalNumber gpsLatitudeMinutes = gpsLatitude[1];
                    RationalNumber gpsLatitudeSeconds = gpsLatitude[2];

                    //obtain latitude ref
                    TiffField gpsLatitudeRefField = metadata.findEXIFValue(
                        TiffConstants.GPS_TAG_GPS_LATITUDE_REF);
                    //set north by default
                    String gpsLatitudeRef = TiffConstants.
                            GPS_TAG_GPS_LATITUDE_REF_VALUE_NORTH;
                    if (gpsLatitudeRefField != null) {
                        gpsLatitudeRef = (String)gpsLatitudeRefField.getValue();
                    }
                    double tmp = gpsLatitudeDegrees.doubleValue() + 
                            gpsLatitudeMinutes.doubleValue() / 60.0 +
                            gpsLatitudeSeconds.doubleValue() / 3600.0;
                    if (!gpsLatitudeRef.toUpperCase().contains(
                            TiffConstants.GPS_TAG_GPS_LATITUDE_REF_VALUE_NORTH.
                            toUpperCase())) {
                        tmp *= -1.0;
                    }
                    latitude = tmp;
                }
            }
            
            //gps longitude
            Double longitude = null;
            TiffField gpsLongitudeField = metadata.findEXIFValue(
                    TiffConstants.GPS_TAG_GPS_LONGITUDE);            
            if (gpsLongitudeField != null &&
                    gpsLongitudeField.fieldType instanceof FieldTypeRational) {
                RationalNumber gpsLongitude[] = (RationalNumber[])
                        gpsLongitudeField.getValue();
                            
                if (gpsLongitude != null && gpsLongitude.length >= 3) {
                    RationalNumber gpsLongitudeDegrees = gpsLongitude[0];
                    RationalNumber gpsLongitudeMinutes = gpsLongitude[1];
                    RationalNumber gpsLongitudeSeconds = gpsLongitude[2];

                    //obtain longitude ref
                    TiffField gpsLongitudeRefField = metadata.findEXIFValue(
                        TiffConstants.GPS_TAG_GPS_LONGITUDE_REF);
                    //set east by default
                    String gpsLongitudeRef = TiffConstants.
                            GPS_TAG_GPS_LONGITUDE_REF_VALUE_EAST;
                    if (gpsLongitudeRefField != null) {
                        gpsLongitudeRef = (String)gpsLongitudeRefField.
                                getValue();                    
                    }
                    double tmp = gpsLongitudeDegrees.doubleValue() +
                                gpsLongitudeMinutes.doubleValue() / 60.0 +
                                gpsLongitudeSeconds.doubleValue() / 3600.0;
                    if (!gpsLongitudeRef.toUpperCase().contains(
                            TiffConstants.GPS_TAG_GPS_LONGITUDE_REF_VALUE_EAST.
                            toUpperCase())) {
                        tmp *= -1.0;
                    }                
                    longitude = tmp;
                }
            }
            
            //gps altitude
            Double altitude = null;
            TiffField gpsAltitudeField = metadata.findEXIFValue(
                    TiffConstants.GPS_TAG_GPS_ALTITUDE);
            if (gpsAltitudeField != null &&
                    gpsAltitudeField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = 
                        (RationalNumber)gpsAltitudeField.getValue();
                if (number != null) {
                    double tmp = number.doubleValue();

                    //check if above or under sea level
                    TiffField gpsAltitudeRefField = metadata.findEXIFValue(
                            TiffConstants.GPS_TAG_GPS_ALTITUDE_REF);
                    Integer above = TiffConstants.
                            GPS_TAG_GPS_ALTITUDE_REF_VALUE_ABOVE_SEA_LEVEL;
                    if (gpsAltitudeRefField != null &&
                            gpsAltitudeRefField.fieldType instanceof FieldTypeShort) {
                        above = (Integer)gpsAltitudeRefField.getValue();
                    }   
                    if (above != TiffConstants.
                            GPS_TAG_GPS_ALTITUDE_REF_VALUE_ABOVE_SEA_LEVEL) {
                        //below sea level
                        tmp *= -1.0;
                    }
                    altitude = tmp;
                }
            }
            
            GPSCoordinates coordinates = null;
            if (latitude != null && longitude != null && altitude != null) {
                coordinates = new GPSCoordinates(latitude, longitude, altitude);
            } else if (latitude != null && longitude != null && 
                        altitude == null) {
                coordinates = new GPSCoordinates(latitude, longitude);
            }
            
            if (coordinates != null) {
                result.setLocation(coordinates);
            }
            
            if (result.getOrientation() != null) {
                //if orientation is available as exif data, check if width and
                //height need to be exchangd
                switch (result.getOrientation()) {
                    case RIGHT_TOP: 
                        //orientation == 6 (clockwise 270º)
                    case LEFT_BOTTOM: 
                        //orientation == 8 (clockwise 90º)
                        //width and height must be exchanged
                        result.setWidth(imageInfo.getHeight());
                        result.setHeight(imageInfo.getWidth());
                        break;                  
                    default:
                        break;
                }
            }  
            
            //Get artist
            TiffField artistField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_ARTIST);
            String artist = null;
            if (artistField != null) {
                artist = artistField.getValueDescription();
            }
            if (artist != null) {
                result.setArtist(trim(artist));
            }
            
            //Get copyright
            TiffField copyrightField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_COPYRIGHT);
            String copyright = null;
            if (copyrightField != null) {
                copyright = copyrightField.getValueDescription();
            }
            if (copyright != null) {
                result.setCopyright(trim(copyright));
            }
            
            //Get document name
            TiffField documentNameField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_DOCUMENT_NAME);
            String documentName = null;
            if (documentNameField != null) {
                documentName = artistField.getValueDescription();
            }
            if (documentName != null) {
                result.setDocumentName(trim(documentName));
            }
            
            //Get host computer
            TiffField hostComputerField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_HOST_COMPUTER);
            String hostComputer = null;
            if (hostComputerField != null) {
                hostComputer = hostComputerField.getValueDescription();
            }
            if (hostComputer != null) {
                result.setHostComputer(trim(hostComputer));
            }
            
            //Get image description
            TiffField imageDescriptionField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_IMAGE_DESCRIPTION);
            String imageDescription = null;
            if (imageDescriptionField != null) {
                imageDescription = imageDescriptionField.getValueDescription();
            }
            if (imageDescription != null) {
                result.setImageDescription(trim(imageDescription));
            }
            
            //Get software
            TiffField softwareField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_SOFTWARE);
            String software = null;
            if (softwareField != null) {
                software = softwareField.getValueDescription();
            }
            if (software != null) {
                result.setSoftware(trim(software));
            }
            
            //Get target printer
            TiffField targetPrinterField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_TARGET_PRINTER);
            String targetPrinter = null;
            if (targetPrinterField != null) {
                targetPrinter = targetPrinterField.getValueDescription();
            }
            if (targetPrinter != null) {
                result.setTargetPrinter(trim(targetPrinter));
            }
            
            //Get camera serial number
            TiffField cameraSerialNumberField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_CAMERA_SERIAL_NUMBER);
            String cameraSerialNumber = null;
            if (cameraSerialNumberField != null) {
                cameraSerialNumber = 
                        cameraSerialNumberField.getValueDescription();
            }
            if (cameraSerialNumber != null) {
                result.setCameraSerialNumber(trim(cameraSerialNumber));
            }
            
            //Get digital zoom ratio
            TiffField digitalZoomRatioField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_DIGITAL_ZOOM_RATIO);
            if (digitalZoomRatioField != null && 
                    digitalZoomRatioField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = 
                        (RationalNumber)digitalZoomRatioField.getValue();
                if (number != null) {
                    result.setDigitalZoomRatio(number.doubleValue());
                }
            }
            
            //Get exposure time
            TiffField exposureTimeField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_EXPOSURE_TIME);
            if (exposureTimeField != null &&
                    exposureTimeField.fieldType instanceof FieldTypeRational) {
                RationalNumber number =
                        (RationalNumber)exposureTimeField.getValue();
                if (number != null) {
                    result.setExposureTime(number.doubleValue());
                }
            }
            
            //Get flash
            TiffField flashField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FLASH);
            if (flashField != null &&
                    flashField.fieldType instanceof FieldTypeShort) {
                Integer number = (Integer)flashField.getValue();
                if (number != null) {
                    result.setFlash(Flash.fromValue(number));
                }
            }
            
            //Get flash energy
            TiffField flashEnergyField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FLASH_ENERGY);
            if (flashEnergyField != null &&
                    flashEnergyField.fieldType instanceof FieldTypeRational) {
                RationalNumber number =
                        (RationalNumber)flashEnergyField.getValue();
                if (number != null) {
                    result.setFlashEnergy(number.doubleValue());
                }
            }
            
            //Get F number
            TiffField fNumberField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FNUMBER);
            if (fNumberField != null && 
                    fNumberField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = (RationalNumber)fNumberField.getValue();
                if (number != null) {
                    result.setFNumber(number.doubleValue());
                }
            }
            
            //Get focal length in 35mm film
            TiffField focalLength35mmFormatField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_LENGTH_IN_35MM_FORMAT);
            if (focalLength35mmFormatField != null &&
                    focalLength35mmFormatField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = 
                        (RationalNumber)focalLength35mmFormatField.getValue();
                if (number != null) {
                    result.setFocalLengthIn35mmFilm(number.doubleValue());
                }
            }
            
            //Get unique camera model
            TiffField uniqueCameraModelField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_UNIQUE_CAMERA_MODEL);
            String uniqueCameraModel = null;
            if (uniqueCameraModelField != null) {
                uniqueCameraModel = modelField.getValueDescription();
            }
            if (uniqueCameraModel != null) {
                result.setUniqueCameraModel(trim(uniqueCameraModel));
            }
            
            //Get subject distance
            TiffField subjectDistanceField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_SUBJECT_DISTANCE);
            if (subjectDistanceField != null &&
                    subjectDistanceField.fieldType instanceof FieldTypeRational) {
                RationalNumber number = 
                        (RationalNumber)subjectDistanceField.getValue();
                if (number != null) {
                    result.setSubjectDistance(number.doubleValue());
                }
            }
            
            //Get shutter speed value
            TiffField shutterSpeedField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            if (shutterSpeedField != null &&
                    shutterSpeedField.fieldType instanceof FieldTypeRational) {
                RationalNumber number =
                        (RationalNumber)shutterSpeedField.getValue();
                if (number != null) {
                    result.setShutterSpeedValue(number.doubleValue());
                }
            }
            
            //Get ISO
            TiffField isoField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_ISO);
            if (isoField != null &&
                    isoField.fieldType instanceof FieldTypeShort) {
                Integer number = (Integer)isoField.getValue();
                if (number != null) {
                    result.setISO(number);
                }
            }            
            
        } catch (ImageReadException e) {
            throw new InvalidImageException(e);
        }
    }
    
    /**
     * Trims leading and ending apostrophes from provided string.
     * @param s string to be processed.
     * @return trimmed string.
     */
    private String trim(String s) {
        String result = null;
        if (s != null) {
            result = s;
            if (s.startsWith("'")) {
                result = result.substring(1);
            }
            if (s.endsWith("'")) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }
}
