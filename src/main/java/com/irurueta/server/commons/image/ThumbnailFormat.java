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
 * Enumerator indicating supported thumbnail formats.
 */
public enum ThumbnailFormat {
    /**
     * JPEG format.
     */
    JPEG("jpeg"),
    
    /**
     * PNG format.
     */
    PNG("png"),
    
    /**
     * GIF format.
     */
    GIF("gif"),
    
    /**
     * BMP format.
     */
    BMP("bmp"),
    
    /**
     * Unknown format.
     */
    UNKNOWN("unknown");
    
    /**
     * String representation or file extension of format.
     */
    private final String mValue;
    
    /**
     * Constructor.
     * @param value string representation or file extension of format.
     */
    ThumbnailFormat(final String value) {
        mValue = value;
    }
    
    /**
     * Returns string representation or file extension of format.
     * @return string representation or file extension of format.
     */
    public String getValue() {
        return mValue;
    }
    
    /**
     * Obtains thumbnail format from provided string representation or file
     * extension.
     * @param value string representation or file extension.
     * @return thumbnail format.
     */
    public static ThumbnailFormat fromValue(final String value) {
        if (value == null) {
            return UNKNOWN;
        }
        
        if (value.equalsIgnoreCase("jpeg") || value.equalsIgnoreCase("jpg")) {
            return JPEG;
        } else if (value.equalsIgnoreCase("png")) {
            return PNG;
        } else if (value.equalsIgnoreCase("gif")) {
            return GIF;
        } else if (value.equalsIgnoreCase("bmp")) {
            return BMP;
        } else {
            return UNKNOWN;
        }
    }
    
    /**
     * Obtains thumbnail format from provided image format.
     * @param format image format.
     * @return thumbnail format.
     */
    public static ThumbnailFormat fromImageFormat(final ImageFormat format) {
        if (format == null) {
            return ThumbnailFormat.UNKNOWN;
        }
        
        switch (format) {
            case JPEG:
                return ThumbnailFormat.JPEG;
            case PNG:
                return ThumbnailFormat.PNG;
            case GIF:
                return ThumbnailFormat.GIF;
            case BMP:
                return ThumbnailFormat.BMP;
            case UNKNOWN:
            default:
                return ThumbnailFormat.UNKNOWN;
        }
    }
}
