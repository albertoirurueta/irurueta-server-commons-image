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
 * Enumeration containing supported image formats.
 * Implementations of Java Virtual Machines only ensure JPEG, PNG, GIF and BMP
 * formats. Other formats might be supported but are implementation dependant
 * of specific Java Virtual Machines.
 */
public enum ImageFormat {
    /**
     * JPEG image format.
     */
    JPEG,

    /**
     * PNG image format.
     */
    PNG,

    /**
     * GIG image format.
     */
    GIF,

    /**
     * BMP image format.
     */
    BMP,

    /**
     * Other unknown formats.
     */
    UNKNOWN
}
