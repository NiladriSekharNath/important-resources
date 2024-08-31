package com.adidas.multithreading.performanceoptimization.latency;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * test program to understand/compare the latency of a program using a single-threaded approach and a multi-threaded approach
 *
 * We have a sample image of flowers in a park, white and purple, that we are using to colour white flowers to purple
 *
 * the image for our example is 3036 x 4048 pixels roughly 12 millions pixels. Each pixel is represented by 4 bytes (therefore can be stored in an integer)
 * variable where each value in this "0x00,00,FF,00" represented by Byte3, byte2, byte1, byte0, and each 0 or F is 4 bits(1 nibble) so if we need to shift 0x0000FF00 -> 0x000000FF
 * we need to right shift 8 bits
 *
 * namely ARGB
 *
 * A - Transparency
 * R - Red
 * G - Green
 * B - blue
 *
 * we can modify the values of these pixels to transform an image into any colour
 *
 * we try sequentially first and then in a multi-threaded approach.
 *
 * for more details regarding the approach please find the in evernotes notes with the title "Multi-threading and Concurrency in Java"
 */

@Slf4j
public class ImageProcessing {

    //private static final String SOURCE_FILE = "../resources/testingfiles/many-flowers.jpg";
    private static final String SOURCE_FILE = "C:\\Users\\nathnil\\Downloads\\design-patterns\\design-patterns\\src\\main\\java\\com\\adidas\\multithreading\\performanceoptimization\\latency\\resources\\testingfiles\\many-flowers.jpg";
    private static final String DESTINATION_FILE = "C:\\Users\\nathnil\\Downloads\\design-patterns\\design-patterns\\src\\main\\java\\com\\adidas\\multithreading\\performanceoptimization\\latency\\resources\\testingfiles\\output\\changed-many-flowers.jpg";

    public static void main(String args[]) throws IOException, InterruptedException {

        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();
        /**
         * this is done to as the SingleThreaded application was taking a lot of time to complete so was breaking that one to a different thread
         * which as the thread is set to Daemon the main thread would not wait for it complete
         */
        Thread singleThreadedCapturing = new Thread(() -> recolorSingleThreaded(originalImage, resultImage));

        singleThreadedCapturing.setDaemon(true);
        singleThreadedCapturing.start();
        singleThreadedCapturing.join(20000);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;


        log.info("Single Threaded Solution took {} milliseconds", duration);

        long startTimeMultiThreaded = System.currentTimeMillis();

        /**
         * please see the image under performanceoptimizations/resources/learningimages/Thread-partitioning-coloring-image.png
         * to understand how the threads are split up and created for recolor to compare the performance
         *
         * the value 10 is provided as this Adidas CPU is containing 10 cores after finding out the number of cores of the CPU from the internet
         * so anywhere between 1 - 10 can be provided
         */
        recolorMultiThreaded(originalImage, resultImage, 6);

        long endTimeMultiThreaded = System.currentTimeMillis();

        long multiThreadedSolutionTime = endTimeMultiThreaded - startTimeMultiThreaded;

        log.info("Multi Threaded Solution took {} milliseconds", multiThreadedSolutionTime);

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);
    }

    private static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
        recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }

    /**
     * (0,   0) -------      (width, 0)
     * |
     * |(0, y) ----(x,y)----- (width, y)
     * |
     * (0, height) ------  (width, height)
     * <p>
     * for more details please see the image in reources/learningimage/Thread-partitioning-coloring-images
     */

    private static void recolorMultiThreaded(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {

        List<Thread> threads = new ArrayList<>();
        int width = originalImage.getWidth(), height = originalImage.getHeight() / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {

            final int threadMultiplier = i;

            Thread thread = new Thread(
                    () -> {
                        int leftCorner = 0, topCorner = height * threadMultiplier;
                        recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
                    });
            threads.add(thread);

        }

        for (Thread eachThread : threads)
            eachThread.start();

        for (Thread eachThread : threads) {
            try {
                eachThread.join();
            } catch (InterruptedException e) {

            }
        }


    }

    /**
     * @param originalImage
     * @param resultImage
     * @param leftCorner    starting x axis of the image (0, y)
     * @param topCorner     starting y axix of the image (x, 0)
     * @param width
     * @param height        accepts the leftCorner and topCorner of the original image the width and height of the original image, which is also same for the final image
     *                      and re-colors that target Image
     */
    private static void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int leftCorner, int topCorner, int width, int height) {
        for (int x = leftCorner; x < leftCorner + width && x < originalImage.getWidth(); x++) {
            for (int y = topCorner; y < topCorner + height && y < originalImage.getHeight(); y++) {
                recolorPixel(originalImage, resultImage, x, y);
            }
        }
    }

    /**
     * this method is recolouring each individual pixel
     *
     * @param originalImage
     * @param resultImage
     * @param x             x - axis
     * @param y             y - axis
     */
    private static void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y) {
        int rgb = originalImage.getRGB(x, y);

        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed, newGreen, newBlue;

        /**
         * if the current pixel represented by (x,y) is a shade of grey we are taking that pixel and converting that to the shade of Purple
         * otherwise if the shade is not purple we leave the color as it is with the old colour
         */
        if (isShadeOfGrey(red, green, blue)) {
            newRed = Math.min(255, red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        int newRGB = createRGBFromColours(newRed, newGreen, newBlue);
        setRGB(resultImage, x, y, newRGB);
    }

    /**
     * set the colour value of an image for the particular pixel represented by (x, y)
     *
     * @param image
     * @param x
     * @param y
     * @param rgb
     */
    private static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }

    /**
     * @param red
     * @param green
     * @param blue
     * @return taking a pixel values and understanding if the pixel is a
     * shade of grey
     * <p>
     * the colour is almost a perfect mix of green, red and blue then the colour is shade of Grey
     */
    private static boolean isShadeOfGrey(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    /**
     * rgb = 0x00,00,00,00
     * blue = 0x00,00,00,46
     * rgb |= blue
     * rgb = 0x00,00,00,46
     * green (0x00,00,00,36)
     * green << 8 --> (0x00,00,36,00)
     * rgb |= (green << 8)
     */
    private static int createRGBFromColours(int red, int green, int blue) {
        int rgb = 0;
        rgb |= blue;
        rgb |= (green << 8);
        rgb |= (red << 16);
        /**
         * did this step in line 52 to make the image totally opaque
         * by setting it to the highest most value
         */
        rgb |= 0xFF000000;
        return rgb;
    }


    private static int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    private static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    private static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }

}
