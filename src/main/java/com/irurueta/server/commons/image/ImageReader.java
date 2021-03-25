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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldTypeRational;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldTypeShort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

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
    private ImageReader() {
    }

    /**
     * Factory method. Creates or returns singleton instance.
     *
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
     *
     * @return true if CRC computation is enabled, false otherwise.
     */
    public synchronized boolean isComputeCrcEnabled() {
        return mComputeCrc;
    }

    /**
     * Specifies whether CRC computation is enabled.
     *
     * @param computeCrc true if CRC computation must be enabled, false
     *                   otherwise.
     */
    public synchronized void setComputeCrcEnabled(final boolean computeCrc) {
        this.mComputeCrc = computeCrc;
    }

    /**
     * Indicates if MD5 hash computation is enabled.
     *
     * @return true if MD5 computation is enabled, false otherwise.
     */
    public synchronized boolean isComputeMd5Enabled() {
        return mComputeMd5;
    }

    /**
     * Specifies whether MD5 hash computation is enabled.
     *
     * @param computeMd5 true if MD5 computation must be enabled, false
     *                   otherwise.
     */
    public synchronized void setComputeMd5Enabled(final boolean computeMd5) {
        this.mComputeMd5 = computeMd5;
    }

    /**
     * Reads image metadata from provided image file.
     *
     * @param f file containing an image in one of the supported formats (jpg,
     *          png, gif or bmp).
     * @return result containing image metadata and image file information.
     * @throws InvalidImageException throws if file is corrupted, contains
     *                               invalid data, is not an image or format is not supported.
     * @throws IOException           if an I/O error occurs.
     */
    public ImageReaderResult readImage(final File f) throws InvalidImageException,
            IOException {
        try {
            final ImageReaderResult result = new ImageReaderResult();
            final ImageInfo imageInfo = Imaging.getImageInfo(f);
            result.setValid(internalCheckValid(imageInfo));
            result.setFileLength(f.length());
            result.setLastModified(f.lastModified());

            // Read metadata
            result.setContentType(imageInfo.getMimeType());
            result.setImageFormat(getImageFormat(imageInfo));

            final ImageMetadata metadata = new ImageMetadata();
            result.setMetadata(metadata);

            // if file is JPEG read its exif data
            if (imageInfo.getFormat() ==
                    ImageFormats.JPEG) {
                internalReadExif(f, imageInfo, metadata);
            } else {
                // if not, at least set image size so we can generate thumbnails
                metadata.setWidth(imageInfo.getWidth());
                metadata.setHeight(imageInfo.getHeight());
            }

            computeCRCandMd5(f, result);
            return result;
        } catch (final ImageReadException e) {
            throw new InvalidImageException(e);
        }
    }

    /**
     * Check if valid is one of the supported image formats.
     *
     * @param f an image file.
     * @return true if file appears to be valid, false otherwise.
     * @throws InvalidImageException if image file is corrupted.
     * @throws IOException           if an I/O error occurs.
     */
    public static boolean checkValidFile(final File f) throws InvalidImageException,
            IOException {
        try {
            final ImageInfo imageInfo = Imaging.getImageInfo(f);
            return internalCheckValid(imageInfo);
        } catch (final IOException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidImageException(e);
        }
    }

    /**
     * Computes CRC and MD5 hashes for provided file and the results get stored
     * in provided result instance.
     *
     * @param f      file to compute CRC and MD5 hashes.
     * @param result instance where CRC and MD5 will be stored.
     * @throws IOException if an I/O error occurs.
     */
    private void computeCRCandMd5(final File f, final ImageReaderResult result)
            throws IOException {
        if (f == null || result == null) {
            return;
        }

        try (final InputStream stream = new FileInputStream(f)) {
            CRC32 crc = null;
            if (mComputeCrc) {
                crc = new CRC32();
            }

            MessageDigest digest = null;
            if (mComputeMd5) {
                try {
                    digest = MessageDigest.getInstance("MD5");
                    digest.reset();
                } catch (final NoSuchAlgorithmException e) {
                    throw new IOException(e);
                }
            }


            final byte[] buffer = new byte[BUFFER_SIZE];
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
     *
     * @param imageInfo structure containing metadata of image file being read.
     * @return detected image format.
     */
    private com.irurueta.server.commons.image.ImageFormat getImageFormat(
            final ImageInfo imageInfo) {
        final org.apache.commons.imaging.ImageFormat format = imageInfo.getFormat();
        if (format == ImageFormats.JPEG) {
            return com.irurueta.server.commons.image.ImageFormat.JPEG;
        } else if (format == ImageFormats.PNG) {
            return com.irurueta.server.commons.image.ImageFormat.PNG;
        } else if (format == ImageFormats.GIF) {
            return com.irurueta.server.commons.image.ImageFormat.GIF;
        } else if (format == ImageFormats.BMP) {
            return com.irurueta.server.commons.image.ImageFormat.BMP;
        } else {
            return com.irurueta.server.commons.image.ImageFormat.UNKNOWN;
        }
    }

    /**
     * Indicates if image file corresponds to one of the supported image
     * formats.
     *
     * @param imageInfo structure containing metadata of image file being read.
     * @return if image file has one of the supported formats.
     */
    private static boolean internalCheckValid(final ImageInfo imageInfo) {
        final org.apache.commons.imaging.ImageFormat format = imageInfo.getFormat();
        return format == ImageFormats.JPEG ||
                format == ImageFormats.PNG ||
                format == ImageFormats.GIF ||
                format == ImageFormats.BMP;
    }

    /**
     * Internal method in charge of reading image metadata and EXIF tags.
     *
     * @param f         image file.
     * @param imageInfo structure containing metadata of image file being read.
     * @param result    structure where resulting image metadata will be stored.
     * @throws InvalidImageException if image file is corrupted or not
     *                               supported.
     * @throws IOException           if an I/O error occurs.
     */
    private void internalReadExif(final File f, final ImageInfo imageInfo,
                                  final ImageMetadata result) throws InvalidImageException, IOException {

        try {
            // store image size
            result.setWidth(imageInfo.getWidth());
            result.setHeight(imageInfo.getHeight());

            final JpegImageMetadata metadata =
                    (JpegImageMetadata) Imaging.getMetadata(f);
            if (metadata == null) {
                return;
            }

            // Get maker
            final TiffField makerField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_MAKE);
            String maker = null;
            if (makerField != null) {
                maker = makerField.getValueDescription();
            }
            if (maker != null) {
                result.setMaker(trim(maker));
            }

            // Get model
            final TiffField modelField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_MODEL);
            String model = null;
            if (modelField != null) {
                model = modelField.getValueDescription();
            }
            if (model != null) {
                result.setModel(trim(model));
            }

            // Focal length
            final TiffField focalLengthField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_LENGTH);
            if (focalLengthField != null &&
                    focalLengthField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) focalLengthField.getValue();
                if (number != null) {
                    result.setFocalLength(number.doubleValue());
                }
            }

            // Focal plane x resolution
            final TiffField focalPlaneXResolutionField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_PLANE_XRESOLUTION_EXIF_IFD);
            if (focalPlaneXResolutionField != null &&
                    focalPlaneXResolutionField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) focalPlaneXResolutionField.getValue();
                if (number != null) {
                    result.setFocalPlaneXResolution(number.doubleValue());
                }
            }

            // Focal plane y resolution
            final TiffField focalPlaneYResolutionField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_PLANE_YRESOLUTION_EXIF_IFD);
            if (focalPlaneYResolutionField != null &&
                    focalPlaneYResolutionField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) focalPlaneYResolutionField.getValue();
                if (number != null) {
                    result.setFocalPlaneYResolution(number.doubleValue());
                }
            }

            // Focal plane resolution unit
            final TiffField focalPlaneResolutionUnitField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_PLANE_RESOLUTION_UNIT_EXIF_IFD);
            if (focalPlaneResolutionUnitField != null &&
                    focalPlaneResolutionUnitField.getFieldType() instanceof FieldTypeShort) {
                final Short number =
                        (Short) focalPlaneResolutionUnitField.getValue();
                if (number != null) {
                    result.setFocalPlaneResolutionUnit(Unit.fromValue(number));
                }
            }

            // Orientation
            final TiffField orientationField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_ORIENTATION);
            if (orientationField != null &&
                    orientationField.getFieldType() instanceof FieldTypeShort) {
                final Short number = (Short) orientationField.getValue();
                if (number != null) {
                    result.setOrientation(ImageOrientation.fromValue(number));
                }
            }

            // gps latitude
            Double latitude = null;
            final TiffField gpsLatitudeField = metadata.findEXIFValue(
                    GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            if (gpsLatitudeField != null &&
                    gpsLatitudeField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber[] gpsLatitude =
                        (RationalNumber[]) gpsLatitudeField.getValue();

                if (gpsLatitude != null && gpsLatitude.length >= 3) {
                    final RationalNumber gpsLatitudeDegrees = gpsLatitude[0];
                    final RationalNumber gpsLatitudeMinutes = gpsLatitude[1];
                    final RationalNumber gpsLatitudeSeconds = gpsLatitude[2];

                    // obtain latitude ref
                    final TiffField gpsLatitudeRefField = metadata.findEXIFValue(
                            GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
                    // set north by default
                    String gpsLatitudeRef = GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF_VALUE_NORTH;
                    if (gpsLatitudeRefField != null) {
                        gpsLatitudeRef = (String) gpsLatitudeRefField.getValue();
                    }
                    double tmp = gpsLatitudeDegrees.doubleValue() +
                            gpsLatitudeMinutes.doubleValue() / 60.0 +
                            gpsLatitudeSeconds.doubleValue() / 3600.0;
                    if (!gpsLatitudeRef.toUpperCase().contains(
                            GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF_VALUE_NORTH.
                                    toUpperCase())) {
                        tmp *= -1.0;
                    }
                    latitude = tmp;
                }
            }

            // gps longitude
            Double longitude = null;
            final TiffField gpsLongitudeField = metadata.findEXIFValue(
                    GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
            if (gpsLongitudeField != null &&
                    gpsLongitudeField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber[] gpsLongitude = (RationalNumber[])
                        gpsLongitudeField.getValue();

                if (gpsLongitude != null && gpsLongitude.length >= 3) {
                    final RationalNumber gpsLongitudeDegrees = gpsLongitude[0];
                    final RationalNumber gpsLongitudeMinutes = gpsLongitude[1];
                    final RationalNumber gpsLongitudeSeconds = gpsLongitude[2];

                    // obtain longitude ref
                    final TiffField gpsLongitudeRefField = metadata.findEXIFValue(
                            GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
                    // set east by default
                    String gpsLongitudeRef = GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF_VALUE_EAST;
                    if (gpsLongitudeRefField != null) {
                        gpsLongitudeRef = (String) gpsLongitudeRefField.
                                getValue();
                    }
                    double tmp = gpsLongitudeDegrees.doubleValue() +
                            gpsLongitudeMinutes.doubleValue() / 60.0 +
                            gpsLongitudeSeconds.doubleValue() / 3600.0;
                    if (!gpsLongitudeRef.toUpperCase().contains(
                            GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF_VALUE_EAST.
                                    toUpperCase())) {
                        tmp *= -1.0;
                    }
                    longitude = tmp;
                }
            }

            // gps altitude
            Double altitude = null;
            final TiffField gpsAltitudeField = metadata.findEXIFValue(
                    GpsTagConstants.GPS_TAG_GPS_ALTITUDE);
            if (gpsAltitudeField != null &&
                    gpsAltitudeField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) gpsAltitudeField.getValue();
                if (number != null) {
                    double tmp = number.doubleValue();

                    // check if above or under sea level
                    final TiffField gpsAltitudeRefField = metadata.findEXIFValue(
                            GpsTagConstants.GPS_TAG_GPS_ALTITUDE_REF);
                    Short above = GpsTagConstants.GPS_TAG_GPS_ALTITUDE_REF_VALUE_ABOVE_SEA_LEVEL;
                    if (gpsAltitudeRefField != null &&
                            gpsAltitudeRefField.getFieldType() instanceof FieldTypeShort) {
                        above = (Short) gpsAltitudeRefField.getValue();
                    }
                    if (above != GpsTagConstants.GPS_TAG_GPS_ALTITUDE_REF_VALUE_ABOVE_SEA_LEVEL) {
                        // below sea level
                        tmp *= -1.0;
                    }
                    altitude = tmp;
                }
            }

            GPSCoordinates coordinates = null;
            if (latitude != null && longitude != null && altitude != null) {
                coordinates = new GPSCoordinates(latitude, longitude, altitude);
            } else if (latitude != null && longitude != null) { // altitude == null
                coordinates = new GPSCoordinates(latitude, longitude);
            }

            if (coordinates != null) {
                result.setLocation(coordinates);
            }

            if (result.getOrientation() != null) {
                // if orientation is available as exif data, check if width and
                // height need to be exchanged
                switch (result.getOrientation()) {
                    case RIGHT_TOP:
                        // orientation == 6 (clockwise 270º)
                    case LEFT_BOTTOM:
                        // orientation == 8 (clockwise 90º)
                        // width and height must be exchanged
                        result.setWidth(imageInfo.getHeight());
                        result.setHeight(imageInfo.getWidth());
                        break;
                    default:
                        break;
                }
            }

            // Get artist
            final TiffField artistField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_ARTIST);
            String artist = null;
            if (artistField != null) {
                artist = artistField.getValueDescription();
            }
            if (artist != null) {
                result.setArtist(trim(artist));
            }

            // Get copyright
            final TiffField copyrightField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_COPYRIGHT);
            String copyright = null;
            if (copyrightField != null) {
                copyright = copyrightField.getValueDescription();
            }
            if (copyright != null) {
                result.setCopyright(trim(copyright));
            }

            // Get document name
            final TiffField documentNameField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_DOCUMENT_NAME);
            String documentName = null;
            if (documentNameField != null && artistField != null) {
                documentName = artistField.getValueDescription();
            }
            if (documentName != null) {
                result.setDocumentName(trim(documentName));
            }

            // Get host computer
            final TiffField hostComputerField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_HOST_COMPUTER);
            String hostComputer = null;
            if (hostComputerField != null) {
                hostComputer = hostComputerField.getValueDescription();
            }
            if (hostComputer != null) {
                result.setHostComputer(trim(hostComputer));
            }

            // Get image description
            final TiffField imageDescriptionField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_IMAGE_DESCRIPTION);
            String imageDescription = null;
            if (imageDescriptionField != null) {
                imageDescription = imageDescriptionField.getValueDescription();
            }
            if (imageDescription != null) {
                result.setImageDescription(trim(imageDescription));
            }

            // Get software
            final TiffField softwareField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_SOFTWARE);
            String software = null;
            if (softwareField != null) {
                software = softwareField.getValueDescription();
            }
            if (software != null) {
                result.setSoftware(trim(software));
            }

            // Get target printer
            final TiffField targetPrinterField = metadata.findEXIFValue(
                    TiffTagConstants.TIFF_TAG_TARGET_PRINTER);
            String targetPrinter = null;
            if (targetPrinterField != null) {
                targetPrinter = targetPrinterField.getValueDescription();
            }
            if (targetPrinter != null) {
                result.setTargetPrinter(trim(targetPrinter));
            }

            // Get camera serial number
            final TiffField cameraSerialNumberField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_BODY_SERIAL_NUMBER);
            String cameraSerialNumber = null;
            if (cameraSerialNumberField != null) {
                cameraSerialNumber =
                        cameraSerialNumberField.getValueDescription();
            }
            if (cameraSerialNumber != null) {
                result.setCameraSerialNumber(trim(cameraSerialNumber));
            }

            // TODO: get lens serial number

            // Get digital zoom ratio
            final TiffField digitalZoomRatioField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_DIGITAL_ZOOM_RATIO);
            if (digitalZoomRatioField != null &&
                    digitalZoomRatioField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) digitalZoomRatioField.getValue();
                if (number != null) {
                    result.setDigitalZoomRatio(number.doubleValue());
                }
            }

            // Get exposure time
            final TiffField exposureTimeField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_EXPOSURE_TIME);
            if (exposureTimeField != null &&
                    exposureTimeField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) exposureTimeField.getValue();
                if (number != null) {
                    result.setExposureTime(number.doubleValue());
                }
            }

            // Get flash
            final TiffField flashField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FLASH);
            if (flashField != null &&
                    flashField.getFieldType() instanceof FieldTypeShort) {
                final Short number = (Short) flashField.getValue();
                if (number != null) {
                    result.setFlash(Flash.fromValue(number));
                }
            }

            // Get flash energy
            final TiffField flashEnergyField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FLASH_ENERGY_EXIF_IFD);
            if (flashEnergyField != null &&
                    flashEnergyField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) flashEnergyField.getValue();
                if (number != null) {
                    result.setFlashEnergy(number.doubleValue());
                }
            }

            // Get F number
            final TiffField fNumberField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FNUMBER);
            if (fNumberField != null &&
                    fNumberField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number = (RationalNumber) fNumberField.getValue();
                if (number != null) {
                    result.setFNumber(number.doubleValue());
                }
            }

            // Get focal length in 35mm film
            final TiffField focalLength35mmFormatField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_FOCAL_LENGTH_IN_35MM_FORMAT);
            if (focalLength35mmFormatField != null &&
                    focalLength35mmFormatField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) focalLength35mmFormatField.getValue();
                if (number != null) {
                    result.setFocalLengthIn35mmFilm(number.doubleValue());
                }
            }

            // Get unique camera model
            final TiffField uniqueCameraModelField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_MODEL_2);
            String uniqueCameraModel = null;
            if (uniqueCameraModelField != null && modelField != null) {
                uniqueCameraModel = modelField.getValueDescription();
            }
            if (uniqueCameraModel != null) {
                result.setUniqueCameraModel(trim(uniqueCameraModel));
            }

            // Get subject distance
            final TiffField subjectDistanceField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_SUBJECT_DISTANCE);
            if (subjectDistanceField != null &&
                    subjectDistanceField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) subjectDistanceField.getValue();
                if (number != null) {
                    result.setSubjectDistance(number.doubleValue());
                }
            }

            // Get shutter speed value
            final TiffField shutterSpeedField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            if (shutterSpeedField != null &&
                    shutterSpeedField.getFieldType() instanceof FieldTypeRational) {
                final RationalNumber number =
                        (RationalNumber) shutterSpeedField.getValue();
                if (number != null) {
                    result.setShutterSpeedValue(number.doubleValue());
                }
            }

            // Get ISO
            final TiffField isoField = metadata.findEXIFValue(
                    ExifTagConstants.EXIF_TAG_ISO);
            if (isoField != null &&
                    isoField.getFieldType() instanceof FieldTypeShort) {
                final Short number = (Short) isoField.getValue();
                if (number != null) {
                    result.setISO(number.intValue());
                }
            }

        } catch (final ImageReadException e) {
            throw new InvalidImageException(e);
        }
    }

    /**
     * Trims leading and ending apostrophes from provided string.
     *
     * @param s string to be processed.
     * @return trimmed string.
     */
    private String trim(final String s) {
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
