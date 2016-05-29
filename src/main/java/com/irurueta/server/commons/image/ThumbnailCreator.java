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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import javax.imageio.ImageIO;

/**
 * This class generates thumbnails of images.
 * Notice that this class can also be used to transcode images to other formats
 * when generating thumbnails (even if they have the same size as the original
 * image)
 */
public class ThumbnailCreator {
    /**
     * Minimum allowed image size.
     */
    public static final int MIN_SIZE = 0;
    
    /**
     * Default maximum number of concurrent threads that can generate a 
     * thumbnail at the same time.
     */
    public static final int DEFAULT_MAX_CONCURRENT_THREADS = 1;
    
    /**
     * Minimum number of concurrent threads that can generate a thumbnail at the
     * same time.
     */
    public static final int MIN_CONCURRENT_THREADS = 1;
    
    /**
     * Reference to singleton instance of thumbnail creator.
     */
    private static SoftReference<ThumbnailCreator> mReference;
    
    /**
     * Maximum number of threads that can generate a thumbnail at the same time.
     * To avoid excessive memory usage, the number of concurrent thumbnails
     * being generated is limited to this value.
     * By default only one thread can generate thumbnails concurrently while
     * other threads will wait until they are allowed.
     * If the server has enough memory and its load is not too high, this value
     * can be increased (especially if the hardware architecture has multiple
     * processors). Increasing this value will result in a larger throughput of
     * generated thumbnails, at the expense of a greater memory usage.
     */
    private int mMaxConcurrentThreads;
    
    /**
     * Current number of threads generating a thumbnail.
     */
    private volatile int mNumThreads;
    
    /**
     * Constructor.
     */
    private ThumbnailCreator() {
        //TODO: make maximum number of concurrent threads configurable
        mMaxConcurrentThreads = DEFAULT_MAX_CONCURRENT_THREADS;
        mNumThreads = 0;
    }
    
    /**
     * Factory method. Creates or returns the singleton instance of this class
     * @return singleton.
     */
    public static synchronized ThumbnailCreator getInstance() {
        ThumbnailCreator creator;
        if (mReference == null || (creator = mReference.get()) == null) {
            creator = new ThumbnailCreator();
            mReference = new SoftReference<>(creator);
        }
        return creator;
    }

    /**
     * sets maximum number of threads that can generate a thumbnail at the same 
     * time.
     * To avoid excessive memory usage, the number of concurrent thumbnails
     * being generated is limited to this value.
     * By default only one thread can generate thumbnails concurrently while
     * other threads will wait until they are allowed.
     * If the server has enough memory and its load is not too high, this value
     * can be increased (especially if the hardware architecture has multiple
     * processors). Increasing this value will result in a larger throughput of
     * generated thumbnails, at the expense of a greater memory usage.
     * @param maxConcurrentThreads maximum number of concurrent threads that can
     * generate thumbnails at the same time.
     * @throws IllegalArgumentException if provided value is less than 1.
     */
    public synchronized void setMaxConcurrentThreads(int maxConcurrentThreads) 
            throws IllegalArgumentException {
        if (maxConcurrentThreads < MIN_CONCURRENT_THREADS) {
            throw new IllegalArgumentException();
        }
        
        this.mMaxConcurrentThreads = maxConcurrentThreads;
    }
    
    /**
     * Returns maximum number of threads that can generate a thumbnail at the 
     * same time.
     * To avoid excessive memory usage, the number of concurrent thumbnails
     * being generated is limited to this value.
     * By default only one thread can generate thumbnails concurrently while
     * other threads will wait until they are allowed.
     * If the server has enough memory and its load is not too high, this value
     * can be increased (especially if the hardware architecture has multiple
     * processors). Increasing this value will result in a larger throughput of
     * generated thumbnails, at the expense of a greater memory usage.
     * @return maximum number of threads that can generate a thumbnail at the
     * same time.
     */
    public synchronized int getMaxConcurrentThreads() {
        return mMaxConcurrentThreads;
    }
    
    /**
     * Generates thumbnail of provided input file image and saves it into
     * generated thumbnail file. Information such as input image orientation can
     * be provided if it needs to be taken into account (otherwise it will be
     * ignored). Output format will determine the format of the generated image,
     * it can be used for image transcoding as well.
     * Notice that this class can only generate thumbnails having a size smaller
     * or equal than input image. Attempting to generate a larger image will
     * fail.
     * Warning: because this class is meant to be run on a server, there should
     * be limits on allowed image sizes to avoid excessive memory usage while
     * loading a very large image file.
     * @param inputImageFile input image file.
     * @param inputOrientation input image orientation (optional).
     * @param generatedThumbnailFile file where generated thumbnail will be
     * stored.
     * @param width width (in pixels) of thumbnail to be generated.
     * @param height height (in pixels) of thumbnail to be generated.
     * @param format format of image to be generated.
     * @throws IllegalArgumentException if width or height is less than minimum
     * allowed image size (1 pixel), or if width or height is greater than
     * actual image size.
     * @throws IOException  if an I/O error occurs.
     */
    public void generateAndSaveThumbnail(File inputImageFile, 
            ImageOrientation inputOrientation,
            File generatedThumbnailFile, int width, int height, 
            ThumbnailFormat format) throws IllegalArgumentException, 
            IOException {
        
        if (width <= MIN_SIZE || height <= MIN_SIZE) {
            throw new IllegalArgumentException();
        }
        
        synchronized (this) {
            try {
                while (mNumThreads >= mMaxConcurrentThreads) {
                    wait();
                }
            } catch (InterruptedException e) { }
            mNumThreads++;
        }
        
        try {
            //default (orientation == 1)
            boolean exchangeSize = false;
            int quadrants = 0;
            if (inputOrientation != null) {
                //take into account only orientations below, other orientations
                //will be ignored
                switch (inputOrientation) {
                    case LEFT_BOTTOM: 
                        //orientaton == 8 (counterclockwise 90º)
                        exchangeSize = true;
                        quadrants = -1;
                        break;
                    case BOTTOM_RIGHT: 
                        //orientaton == 3 (clockwise 180º)
                        exchangeSize = false;
                        quadrants = -2;
                        break;
                    case RIGHT_TOP: 
                        //orientaton == 6 (clockwise 90º)
                        exchangeSize = true;
                        quadrants = -3;
                        break;
                    default:
                        break;
                }
            }
            
            int bufferedImageType = BufferedImage.TYPE_INT_RGB;
            if (format == ThumbnailFormat.PNG) {
                bufferedImageType = BufferedImage.TYPE_INT_ARGB;
            }
        
            BufferedImage inputImage = ImageIO.read(inputImageFile);
            if (inputImage == null) {
                throw new IOException();
            }
            
        
            Image tempImage;
            BufferedImage resizedImage;  
            Graphics2D graphics2D;
            if (exchangeSize) {
                if (width > inputImage.getHeight() || 
                        height > inputImage.getWidth()) {
                    throw new IllegalArgumentException();
                }
            
                //scale image
                tempImage = inputImage.getScaledInstance(height, width, 
                        Image.SCALE_AREA_AVERAGING);
                resizedImage = new BufferedImage(height, width,                     
                       bufferedImageType);
                graphics2D = resizedImage.createGraphics();
                graphics2D.drawImage(tempImage, 0, 0, height, width, null);
                graphics2D.dispose();
            
            } else {
                if (width > inputImage.getWidth() || 
                        height > inputImage.getHeight()) {
                    throw new IllegalArgumentException();
                }
            
                //scale image
                tempImage = inputImage.getScaledInstance(width, height,
                        Image.SCALE_AREA_AVERAGING);
                resizedImage = new BufferedImage(width, height,                     
                       bufferedImageType);
                graphics2D = resizedImage.createGraphics();
                graphics2D.drawImage(tempImage, 0, 0, width, height, null);
                graphics2D.dispose();            
            }
        
        
            double centerX, centerY;
            int resizedHeight;
            if (exchangeSize) {
                centerX = (double)height / 2.0;
                centerY = (double)width / 2.0;            
                resizedHeight = width;
            } else {
                centerX = (double)width / 2.0;
                centerY = (double)height / 2.0;
                resizedHeight = height;
            }
                                
            BufferedImage thumbnailImage = resizedImage;
            if (quadrants != 0) {
                //set rotation transformationby the desired number of quadrants
                AffineTransform rotateT = new AffineTransform();
                rotateT.rotate(0.5 * Math.PI * (double)quadrants,
                        centerX, centerY);
            
                //find proper translations to ensure that rotation doesn't cut 
                //off any image data            
                if (quadrants != -2) {
                    AffineTransform rotateT2 = new AffineTransform();
                    rotateT2.rotate(-1.5 * Math.PI, centerX, centerY);
                    Point2D p2din = new Point2D.Double(0.0, 0.0);
                    Point2D p2dout = rotateT2.transform(p2din, null);
                    double ytrans = p2dout.getY();
            
                    p2din = new Point2D.Double(0.0, resizedHeight);
                    p2dout = rotateT2.transform(p2din, null);
                    double xtrans = p2dout.getX();
            
                    AffineTransform translateT = new AffineTransform();
                    translateT.translate(-xtrans, -ytrans);
                                                
                    rotateT.preConcatenate(translateT);        
                }
            
                //instantiate image that will contain the thumbnail
                thumbnailImage = new BufferedImage(width, height, 
                       bufferedImageType);
                graphics2D = thumbnailImage.createGraphics();  
                //transform filtered image with scaling and rotation
                graphics2D.drawImage(resizedImage, rotateT, null);
                graphics2D.dispose();
            }
        
        
            if (!ImageIO.write(thumbnailImage, format.getValue(), 
                    generatedThumbnailFile)) {
                //if format is not supported
                throw new IOException(); 
            }
        } finally {
            //decrease counter of threads no matter if thumbnail generation 
            //fails
            synchronized (this) {
                mNumThreads--;
                this.notifyAll();
            }            
        }
    }
}
