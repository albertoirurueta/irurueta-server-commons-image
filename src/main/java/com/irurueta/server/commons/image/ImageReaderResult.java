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

/**
 * Structure containing information about an image file that has been read using
 * and ImageReader.
 * This structure contains information such as image EXIF metadata, image 
 * format, file length, CRC, MD5, etc.
 */
public class ImageReaderResult {
    /**
     * Indicates if a file contains a valid recognized image and is not 
     * corrupted.
     */
    private boolean mValid;
    
    /**
     * CRC hash value of image file.
     */
    private Long mCrc;
    
    /**
     * MD5 hash value of image file.
     */
    private String mMd5;
    
    /**
     * Image file length in bytes.
     */
    private long mFileLength;
    
    /**
     * Timestamp when image file was last modified.
     */
    private long mLastModified;
    
    /**
     * MIME content type assigned to image file depending on detected image
     * format (i.e. image/jpeg or image/png).
     */
    private String mContentType;
    
    /**
     * Image metadata such as image size, and EXIF tags such as GPS location.
     */
    private ImageMetadata mMetadata;    
    
    /**
     * Detected image format. Supported formats are: JPG, PNG, GIF, BMP.
     */
    private ImageFormat mImageFormat = ImageFormat.UNKNOWN;
    
    /**
     * Constructor.
     */
    public ImageReaderResult() { }
    
    /**
     * Indicates if a file contains a valid recognized image and is not 
     * corrupted.
     * @return true if file is valid, false otherwise.
     */
    public boolean isValid() {
        return mValid;
    }
    
    /**
     * Specifies if a file contains a valid recognized image and is not 
     * corrupted.
     * @param valid true if file is valid, false otherwise.
     */
    public void setValid(boolean valid) {
        mValid = valid;
    }
    
    /**
     * Returns CRC hash value of image file.
     * CRC can be used to detect data corruption when sending data over a 
     * network.
     * @return CRC hash value of image file.
     */
    public Long getCrc() {
        return mCrc;
    }
    
    /**
     * Sets CRC hash value of image file.
     * CRC can be used to detect data corruption when sending data over a
     * network.
     * @param crc CRC hash value of image file to be set.
     */
    public void setCrc(Long crc) {
        mCrc = crc;
    }
    
    /**
     * Returns MD5 hash value of image file.
     * MD5 hash value can be used to detect data corruption when sending data
     * over a network. MD5 is more secure than CRC but its computation is also
     * more expensive.
     * @return MD5 hash value of image file.
     */
    public String getMd5() {
        return mMd5;
    }
    
    /**
     * Sets MD5 hash value of image file.
     * MD5 hash value can be used to detect data corruption when sending data
     * over a network. MD5 is more secure than CRC but its computation is also
     * more expensive.
     * @param md5 MD5 hash value of image file to be set.
     */
    public void setMd5(String md5) {
        this.mMd5 = md5;
    }
    
    /**
     * Returns image file length in bytes.
     * @return image file length in bytes.
     */
    public long getFileLength() {
        return mFileLength;
    }
    
    /**
     * Sets image file length in bytes.
     * @param fileLength image file length in bytes.
     */
    public void setFileLength(long fileLength) {
        mFileLength = fileLength;
    }
    
    /**
     * Returns timestamp when image file was last modified as the number of
     * milliseconds passed since epoch time (January 1st, 1970).
     * @return timestamp when image file was last modified.
     */
    public long getLastModified() {
        return mLastModified;
    }
    
    /**
     * Sets timestamp when image file was last modified as the number of
     * milliseconds passed since epoch time (January 1st, 1970).
     * @param lastModified timestamp when image file was last modified.
     */
    public void setLastModified(long lastModified) {
        mLastModified = lastModified;
    }
    
    /**
     * Returns MIME content type assigned to image file depending on detected 
     * image format (i.e. image/jpeg or image/png).
     * @return MIME content type assigned to image.
     */
    public String getContentType() {
        return mContentType;
    }
    
    /**
     * Sets MIME content type to be assigned to image file depending on detected
     * image format (i.e. image/jpeg or image/png).
     * @param contentType MIME content type to be assigned to image.
     */
    public void setContentType(String contentType) {
        mContentType = contentType;
    }
    
    /**
     * Returns image metadata such as image size, and EXIF tags such as GPS 
     * location.
     * @return image metadata.
     */
    public ImageMetadata getMetadata() {
        return mMetadata;
    }
    
    /**
     * Sets image metadata such as image size, and EXIF tags such as GPS 
     * location.
     * @param metadata image metadata to be set.
     */
    public void setMetadata(ImageMetadata metadata) {
        mMetadata = metadata;
    }
    
    /**
     * Returns detected image format. Supported formats are: JPG, PNG, GIF, BMP
     * @return detected image format.
     */
    public ImageFormat getImageFormat() {
        return mImageFormat;
    }
    
    /**
     * Sets detected image format. Supported formats are: JPG, PNG, GIF, BMP.
     * @param imageFormat detected image format.
     */
    public void setImageFormat(ImageFormat imageFormat) {
        mImageFormat = imageFormat;
    }    
}
