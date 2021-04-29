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

/**
 * Specifies image metadata containing information such as image size, camera
 * maker or model, or GPS coordinates where a picture was taken.
 */
public class ImageMetadata {
    /**
     * Image width in pixels.
     */
    private int mWidth;

    /**
     * Image height in pixels.
     */
    private int mHeight;

    /**
     * Maker of camera that took this image. This data will only be available
     * if the original file contained this metadata.
     */
    private String mMaker;

    /**
     * Model of camera that took this image. This data will only be available
     * if the original file contained this metadata.
     */
    private String mModel;

    /**
     * Focal length of the camera lens (in inches or centimeters). This data
     * will only be available if the original file contained this metadata.
     */
    private Double mFocalLength;

    /**
     * Image resolution in x coordinates direction (in inches or centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     */
    private Double mFocalPlaneXResolution;

    /**
     * Image resolution in y coordinates direction (in inches or centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     */
    private Double mFocalPlaneYResolution;

    /**
     * Resolution unit. This value indicates the resolution unit of focal plane
     * resolution fields and focal length.
     * A value of 1 indicates that no units are available.
     * A value of 2 indicates that focal resolution is expressed in inches.
     * A value of 3 indicates that focal resolution is expressed in centimeters.
     * Other values indicates that focal resolution units are unknown.
     */
    private Unit mFocalPlaneResolutionUnit;

    /**
     * Integer indicating image orientation metadata. Orientation indicates that
     * image might need to be rotated before displaying it. This field is used
     * because some cameras (i.e. iPhone) store images always in the same
     * orientation regardless the actual position of the device, but are capable
     * of detecting whether the device was rotated and such information is
     * stored in the image so that images get rotated when being displayed and
     * can be shown as expected.
     * The supported values of orientation are:
     * 1 = Top left orientation.
     * 2 = Top right orientation.
     * 3 = Bottom right orientation (image has to be rotated 180º).
     * 4 = Bottom left orientation.
     * 5 = left top orientation.
     * 6 = right top orientation (image has to be rotated 90º clockwise).
     * 7 = right bottom orientation.
     * 8 = left bottom orientation (image has to be rotated 90º
     * counterclockwise).
     */
    private ImageOrientation mOrientation;

    /**
     * Contains location data if picture was taken with a device capable of
     * storing GPS coordinates. Location information might contain information
     * such as latitude, longitude, altitude and accuracy of location.
     */
    private GPSCoordinates mLocation;

    /**
     * Name of the camera owner, artist or creator.
     */
    private String mArtist;

    /**
     * Copyright information. Indicates both the photographer and editor
     * copyrights.
     */
    private String mCopyright;

    /**
     * The name of the document from which this image was scanned.
     */
    private String mDocumentName;

    /**
     * Information about the host computer used to generate the image.
     */
    private String mHostComputer;

    /**
     * Title of the image.
     */
    private String mImageDescription;

    /**
     * Name and version of the software or firmware of the camera or image input
     * device used to generate the image.
     */
    private String mSoftware;

    /**
     * A description of the printing environment for which this image is
     * intended.
     */
    private String mTargetPrinter;

    /**
     * Serial number of the camera or camera body that captured the image.
     */
    private String mCameraSerialNumber;

    /**
     * Indicates the digital zoom ratio when the image was shot. If the
     * numerator of the recorded value is 0, this indicates that digital zoom
     * was not used.
     */
    private Double mDigitalZoomRatio;

    /**
     * Exposure time, given in seconds.
     */
    private Double mExposureTime;

    /**
     * Indicates the status of flash when the image was shot.
     */
    private Flash mFlash;

    /**
     * Amount of flash energy (BCPS - Beam Candels Power Second).
     */
    private Double mFlashEnergy;

    /**
     * The F number.
     */
    private Double mFNumber;

    /**
     * Indicates the equivalent focal length assuming a 35mm film camera, in mm.
     * A value of 0 means the focal length is unknown.
     */
    private Double mFocalLengthIn35mmFilm;

    /**
     * Defines a unique, non-localized name for the camera model that created
     * the image in the raw file.
     */
    private String mUniqueCameraModel;

    /**
     * The distance to the subject, given in meters.
     */
    private Double mSubjectDistance;

    /**
     * Shutter speed expressed as APEX (Additive System of Photographic
     * Exposure).
     *
     * @see #mExposureTime to get the same value expressed in
     * seconds.
     */
    private Double mShutterSpeedValue;

    /**
     * Indicates the ISO speed.
     */
    private Integer mISO;

    /**
     * Return image width in pixels.
     *
     * @return image width in pixels.
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * Sets image width in pixels.
     *
     * @param width image width in pixels.
     */
    public void setWidth(final int width) {
        mWidth = width;
    }

    /**
     * Return image height in pixels.
     *
     * @return image height in pixels.
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * Sets image height in pixels.
     *
     * @param height image height in pixels.
     */
    public void setHeight(final int height) {
        mHeight = height;
    }

    /**
     * Returns maker of camera that took this image. This data will only be
     * available if the original file contained this metadata.
     *
     * @return maker of camera that took this image.
     */
    public String getMaker() {
        return mMaker;
    }

    /**
     * Sets maker of camera that took this image. This data will only be
     * available if the original file contained this metadata.
     *
     * @param maker maker of camera that took this image.
     */
    public void setMaker(final String maker) {
        mMaker = maker;
    }

    /**
     * Returns model of camera that took this image. This data will only be
     * available if the original file contained this metadata.
     *
     * @return model of camera that took this image.
     */
    public String getModel() {
        return mModel;
    }

    /**
     * Sets model of camera that took this image. This data will only be
     * available if the original file contained this metadata.
     *
     * @param model model of camera that took this image.
     */
    public void setModel(final String model) {
        mModel = model;
    }

    /**
     * Returns focal length of the camera lens (in inches or centimeters). This
     * data will only be available if the original file contained this metadata.
     *
     * @return focal length of the camera lens.
     */
    public Double getFocalLength() {
        return mFocalLength;
    }

    /**
     * Sets focal length of the camera lens (in inches or centimeters).
     *
     * @param focalLength focal length of the camera lens to be set.
     */
    public void setFocalLength(final Double focalLength) {
        mFocalLength = focalLength;
    }

    /**
     * Returns image resolution in x coordinates direction (in inches or
     * centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     *
     * @return image resolution in x coordinates direction.
     */
    public Double getFocalPlaneXResolution() {
        return mFocalPlaneXResolution;
    }

    /**
     * Sets image resolution in x coordinates direction (in inches or
     * centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     *
     * @param focalPlaneXResolution image resolution in x coordinates direction.
     */
    public void setFocalPlaneXResolution(final Double focalPlaneXResolution) {
        mFocalPlaneXResolution = focalPlaneXResolution;
    }

    /**
     * Results image resolution in y coordinates direction (in inches or
     * centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     *
     * @return image resolution in y coordinates direction.
     */
    public Double getFocalPlaneYResolution() {
        return mFocalPlaneYResolution;
    }

    /**
     * Sets image resolution in y coordinates direction (in inches or
     * centimeters).
     * This field is related to actual sensor size and relates pixel size to
     * actual physical size.
     *
     * @param focalPlaneYResolution image resolution in y coordinates direction.
     */
    public void setFocalPlaneYResolution(final Double focalPlaneYResolution) {
        mFocalPlaneYResolution = focalPlaneYResolution;
    }

    /**
     * Returns resolution unit. This value indicates the resolution unit of
     * focal plane resolution fields and focal length.
     * Possible values are:
     * - no units are available.
     * - focal resolution is expressed in inches.
     * - focal resolution is expressed in centimeters.
     * - focal resolution units are unknown.
     *
     * @return resolution unit.
     */
    public Unit getFocalPlaneResolutionUnit() {
        return mFocalPlaneResolutionUnit;
    }

    /**
     * Sets resolution unit. This value indicates the resolution unit of focal
     * plane resolution fields and focal length.
     * Possible values are:
     * - no units are available.
     * - focal resolution is expressed in inches.
     * - focal resolution is expressed in centimeters.
     * - focal resolution units are unknown.
     *
     * @param focalPlaneResolutionUnit resolution unit.
     */
    public void setFocalPlaneResolutionUnit(final Unit focalPlaneResolutionUnit) {
        mFocalPlaneResolutionUnit = focalPlaneResolutionUnit;
    }

    /**
     * Returns integer indicating image orientation metadata. Orientation
     * indicates that image might need to be rotated before displaying it. This
     * field is used because some cameras (i.e. iPhone) store images always in
     * the same orientation regardless the actual position of the device, but
     * are capable of detecting whether the device was rotated and such
     * information is stored in the image so that images get rotated when being
     * displayed and can be shown as expected.
     * The supported values of orientation are:
     * - Top left orientation.
     * - Top right orientation.
     * - Bottom right orientation (image has to be rotated 180º).
     * - Bottom left orientation.
     * - left top orientation.
     * - right top orientation (image has to be rotated 90º clockwise).
     * - right bottom orientation.
     * - left bottom orientation (image has to be rotated 90º counterclockwise).
     *
     * @return integer indicating image orientation metadata.
     */
    public ImageOrientation getOrientation() {
        return mOrientation;
    }

    /**
     * Sets integer indicating image orientation metadata. Orientation indicates
     * that image might need to be rotated before displaying it. This field is
     * used because some cameras (i.e. iPhone) store images always in the same
     * orientation regardless the actual position of the device, but are capable
     * of detecting whether the device was rotated and such information is
     * stored in the image so that images get rotated when being displayed and
     * can be shown as expected.
     * The supported values of orientation are:
     * - Top left orientation.
     * - Top right orientation.
     * - Bottom right orientation (image has to be rotated 180º).
     * - Bottom left orientation.
     * - left top orientation.
     * - right top orientation (image has to be rotated 90º clockwise).
     * - right bottom orientation.
     * - left bottom orientation (image has to be rotated 90º counterclockwise).
     *
     * @param orientation orientation to be set.
     */
    public void setOrientation(final ImageOrientation orientation) {
        mOrientation = orientation;
    }

    /**
     * Returns location data if picture was taken with a device capable of
     * storing GPS coordinates. Location information might contain information
     * such as latitude, longitude, altitude and accuracy of location.
     *
     * @return image location.
     */
    public GPSCoordinates getLocation() {
        return mLocation;
    }

    /**
     * Sets location data of picture.
     *
     * @param location image location.
     */
    public void setLocation(final GPSCoordinates location) {
        mLocation = location;
    }

    /**
     * Returns name of the camera owner, artist or creator.
     *
     * @return name of the camera owner, artist or creator.
     */
    public String getArtist() {
        return mArtist;
    }

    /**
     * Sets name of the camera owner, artist or creator.
     *
     * @param artist name of the camera owner, artist or creator.
     */
    public void setArtist(final String artist) {
        mArtist = artist;
    }

    /**
     * Returns copyright information. Indicates both the photographer and editor
     * copyrights.
     *
     * @return copyright information.
     */
    public String getCopyright() {
        return mCopyright;
    }

    /**
     * Sets copyright information. Indicates both the photographer and editor
     * copyrights.
     *
     * @param copyright copyright information.
     */
    public void setCopyright(final String copyright) {
        mCopyright = copyright;
    }

    /**
     * Return the name of the document from which this image was scanned.
     *
     * @return the name of the document from which this image was scanned.
     */
    public String getDocumentName() {
        return mDocumentName;
    }

    /**
     * Sets the name of the document from which this image was scanned.
     *
     * @param documentName the name of the document from which this image was
     *                     scanned.
     */
    public void setDocumentName(final String documentName) {
        mDocumentName = documentName;
    }

    /**
     * Returns information about the host computer used to generate the image.
     *
     * @return information about the host computer used to generate the image.
     */
    public String getHostComputer() {
        return mHostComputer;
    }

    /**
     * Sets information about the host computer used to generate the image.
     *
     * @param hostComputer information about the host computer used to generate
     *                     the image.
     */
    public void setHostComputer(final String hostComputer) {
        mHostComputer = hostComputer;
    }

    /**
     * Returns title of the image.
     *
     * @return title of the image.
     */
    public String getImageDescription() {
        return mImageDescription;
    }

    /**
     * Sets title of the image.
     *
     * @param imageDescription title of the image.
     */
    public void setImageDescription(final String imageDescription) {
        mImageDescription = imageDescription;
    }

    /**
     * Returns name and version of the software or firmware of the camera or
     * image input device used to generate the image.
     *
     * @return name and version of the software or firmware of the camera or
     * image input device used to generate the image.
     */
    public String getSoftware() {
        return mSoftware;
    }

    /**
     * Sets name and version of the software or firmware of the camera or
     * image input device used to generate the image.
     *
     * @param software name and version of the software or firmware of the
     *                 camera or image input device used to generate the image.
     */
    public void setSoftware(final String software) {
        mSoftware = software;
    }

    /**
     * Returns a description of the printing environment for which this image is
     * intended.
     *
     * @return a description of the printing environment.
     */
    public String getTargetPrinter() {
        return mTargetPrinter;
    }

    /**
     * Sets a description of the printing environment for which this image is
     * intended.
     *
     * @param targetPrinter a description of the printing environment.
     */
    public void setTargetPrinter(final String targetPrinter) {
        mTargetPrinter = targetPrinter;
    }

    /**
     * Returns serial number of the camera or camera body that captured the
     * image.
     *
     * @return serial number of the camera or camera body that captured the
     * image.
     */
    public String getCameraSerialNumber() {
        return mCameraSerialNumber;
    }

    /**
     * Sets serial number of the camera or camera body that captured the image.
     *
     * @param cameraSerialNumber serial number of the camera or camera body that
     *                           captured the image.
     */
    public void setCameraSerialNumber(final String cameraSerialNumber) {
        mCameraSerialNumber = cameraSerialNumber;
    }

    /**
     * Returns the digital zoom ratio when the image was shot. If the numerator
     * of the recorded value is 0, this indicates that digital zoom was not
     * used.
     *
     * @return the digital zoom ratio when the image was shot.
     */
    public Double getDigitalZoomRatio() {
        return mDigitalZoomRatio;
    }

    /**
     * Sets the digital zoom ratio when the image was shot. If the numerator of
     * the recorded value is 0, this indicates the digital zoom was not used.
     *
     * @param digitalZoomRatio the digital zoom ratio when the image was shot.
     */
    public void setDigitalZoomRatio(final Double digitalZoomRatio) {
        mDigitalZoomRatio = digitalZoomRatio;
    }

    /**
     * Returns exposure time, given in seconds.
     *
     * @return exposure time, given in seconds.
     */
    public Double getExposureTime() {
        return mExposureTime;
    }

    /**
     * Sets exposure time, given in seconds.
     *
     * @param exposureTime exposure time, given in seconds.
     */
    public void setExposureTime(final Double exposureTime) {
        mExposureTime = exposureTime;
    }

    /**
     * Returns status of flash when the image was shot.
     *
     * @return status of flash when the image was shot.
     */
    public Flash getFlash() {
        return mFlash;
    }

    /**
     * Sets status of flash when the image was shot.
     *
     * @param flash status of flash when the image was shot.
     */
    public void setFlash(final Flash flash) {
        mFlash = flash;
    }

    /**
     * Returns amount of flash energy expressed in BCPS (Beam Candels Power
     * Second).
     *
     * @return amount of flash energy.
     */
    public Double getFlashEnergy() {
        return mFlashEnergy;
    }

    /**
     * Sets amount of flash energy expressed in BCPS (Beam Candels Power
     * Second).
     *
     * @param flashEnergy flash energy expressed in BCPS.
     */
    public void setFlashEnergy(final Double flashEnergy) {
        mFlashEnergy = flashEnergy;
    }

    /**
     * Returns the F number.
     *
     * @return the F number.
     */
    public Double getFNumber() {
        return mFNumber;
    }

    /**
     * Sets the F number.
     *
     * @param fNumber the F number.
     */
    public void setFNumber(final Double fNumber) {
        mFNumber = fNumber;
    }

    /**
     * Returns the equivalent focal length assuming a 35mm film camera, in mm.
     * A value of 0 means the focal length is unknown.
     *
     * @return the equivalent focal length assuming a 35mm film camera.
     */
    public Double getFocalLengthIn35mmFilm() {
        return mFocalLengthIn35mmFilm;
    }

    /**
     * Sets the equivalent focal length assuming a 35mm film camera, in mm.
     * A value of 0 means the focal length is unknown.
     *
     * @param focalLengthIn35mmFilm the equivalent focal length assuming a 35mm
     *                              film camera.
     */
    public void setFocalLengthIn35mmFilm(final Double focalLengthIn35mmFilm) {
        mFocalLengthIn35mmFilm = focalLengthIn35mmFilm;
    }

    /**
     * Returns a unique, non-localized name for the camera model that created
     * the image in the raw file.
     *
     * @return a unique, non-localized name for the camera model.
     */
    public String getUniqueCameraModel() {
        return mUniqueCameraModel;
    }

    /**
     * Sets a unique, non-localized name for the camera model that created the
     * image in the raw file.
     *
     * @param uniqueCameraModel a unique, non-localized name for the camera
     *                          model.
     */
    public void setUniqueCameraModel(final String uniqueCameraModel) {
        mUniqueCameraModel = uniqueCameraModel;
    }

    /**
     * Returns the distance to the subject, given in meters.
     *
     * @return the distance to the subject, given in meters.
     */
    public Double getSubjectDistance() {
        return mSubjectDistance;
    }

    /**
     * Sets the distance to the subject, given in meters.
     *
     * @param subjectDistance distance to the subject, given in meters.
     */
    public void setSubjectDistance(final Double subjectDistance) {
        mSubjectDistance = subjectDistance;
    }

    /**
     * Returns shutter speed expressed as APEX (Additive System of Photographic
     * Exposure).
     *
     * @return shutter speed expressed as APEX.
     * @see #getExposureTime() to get the same value expressed in seconds
     */
    public Double getShutterSpeedValue() {
        return mShutterSpeedValue;
    }

    /**
     * Sets shutter speed expressed as APEX (Additive System of Photographic
     * Exposure).
     *
     * @param shutterSpeedValue shutter speed expressed as APEX.
     * @see #getExposureTime() to get the same value expressed in seconds.
     */
    public void setShutterSpeedValue(final Double shutterSpeedValue) {
        mShutterSpeedValue = shutterSpeedValue;
    }

    /**
     * Returns the ISO speed.
     *
     * @return the ISO speed.
     */
    public Integer getISO() {
        return mISO;
    }

    /**
     * Sets the ISO speed.
     *
     * @param iso the ISO speed.
     */
    public void setISO(final Integer iso) {
        mISO = iso;
    }
}
