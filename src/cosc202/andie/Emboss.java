package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.awt.image.*;
import java.awt.*;

public class Emboss implements ImageOperation {
    int kernelNo = 0;
    Kernel kernel = null;
    int imageWidth;
    int imageHeight;
    Color[] arr = new Color[9];
    boolean zero;
    boolean twofivefive;
    boolean once;

    Emboss(int kernelNo) {
        this.kernelNo = kernelNo;
        zero = false;
        twofivefive = false;
        System.out.println(kernelNo);
        once = false;
    }

    public BufferedImage apply(BufferedImage image) {
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        setKernel(kernelNo);
        BufferedImage finImage = iterateImage(image);
        // int p = image.getRGB(0,0);
        // int a = (p>>24)&0xff;
        // int r = (p>>16)&0xff;
        // int g = (p>>8)&0xff;
        // int b = p&0xff;

        

        // ConvolveOp convOp = new ConvolveOp(kernel); // Constructs a ConvloveOp with
        // the given Kernel.
        // BufferedImage output = new BufferedImage(image.getColorModel(),
        // image.copyData(null),
        // image.isAlphaPremultiplied(), null); // Constructs a new BufferedImage with a
        // ColorModel and Raster
        // and
        // BufferedImage buff = new ConolveOp(kernel); // is AlphaPremulitplied returned
        // true.
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);
        // output = convOp.filter(image, null);

        return image;
       // return finImage;
    }

    protected BufferedImage trial(BufferedImage image){

        int radius = 1;
        int length = 3;
        for (int x = radius; x < (imageWidth - radius); x = (x +1)) {
            for (int y = radius; y < (imageHeight - radius); y = (y +1)) {

                int r=0;
                int g=0;
                int b=0;
               

            }
        }


        return image;
    }

    protected void setKernel(int kernelNo) {

        // depending what emboss filter they chose
        if (kernelNo == 1) {
            kernel = new Kernel(3, 3,
                    // new float[] { 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f });
                    new float[] { 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, .0f });
             //kernel = changeKernel(kernel);
            float[] f = new float[9];
            f = kernel.getKernelData(f);
            System.out.println("kern: " + Arrays.toString(f));
        } else if (kernelNo == 2) {
            kernel = new Kernel(3, 3,
                    new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f });
        } else if (kernelNo == 3) {
            kernel = new Kernel(3, 3,
                    new float[] { 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f });
        } else if (kernelNo == 4) {
            kernel = new Kernel(3, 3,
                    new float[] { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f });

        } else if (kernelNo == 5) {
            kernel = new Kernel(3, 3,
                    new float[] { 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f });
        } else if (kernelNo == 6) {
            kernel = new Kernel(3, 3,
                    new float[] { -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f });
        } else if (kernelNo == 7) {
            kernel = new Kernel(3, 3,
                    new float[] { 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f });
        } else if (kernelNo == 8) {
            kernel = new Kernel(3, 3,
                    new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f });
        } else if (kernelNo == 9) {
            kernel = new Kernel(3, 3,
                    new float[] { -0.5f, -1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 0.5f });
        } else if (kernelNo == 10) {
            kernel = new Kernel(3, 3,
                    new float[] { -0.5f, 0.0f, 0.5f, -1.0f, 0.0f, 1.0f, -0.5f, 0.0f, 0.5f });

        } else {
            System.out.println("other");
        }
    }

    protected Kernel changeKernel(Kernel k) {

        float[] f = new float[9];
        f = k.getKernelData(f);
        for (int i = 0; i < 9; i++) {
            f[i] = (f[i] + 127);
        }
        Kernel newK = new Kernel(3, 3, f);
        return newK;
    }

    protected BufferedImage iterateImage(BufferedImage image) {
        int radius = 1;
        int length = 3;

        // for (int x = radius; x < (imageWidth - radius); x = (x + length)) {
        //     for (int y = radius; y < (imageHeight - radius); y = (y + length)) {
        for (int x = radius; x < (imageWidth - radius); x++) {
            for (int y = radius; y < (imageHeight - radius); y++) {

                int[][] array = getRGB(x, y, image);
                // zero = false;
                // twofivefive = false;
                for (int i = 0; i < 9; i++) {
                    // if(x < 10 && y < 10)System.out.println(array[0][i] + " - " + array[1][i] + "
                    // - " + array[2][i]);
                    try {
                        int r = Math.min(Math.abs((array[0][i]-array[0][i+i])+128),255);
                        int g = Math.min(Math.abs((array[1][i]-array[1][i+i])+128),255);
                        int b = Math.min(Math.abs((array[2][i]-array[2][i+i])+128),255);
                        //Color c = new Color(array[0][i], array[1][i], array[2][i]);
                        Color c = new Color(r, g, b);
                        int rgb = c.getRGB();
                        image.setRGB(x, y, rgb);
                    } catch (Exception ex) {
                        // if(x < 100 && y < 100)System.out.println("error - " + array[0][i] + " - " +
                        // array[1][i] + " - " + array[2][i]);
                    }
                }

            }
        }
        return image;
    }

    protected int[][] getRGB(int x, int y, BufferedImage image) {
        arr = new Color[] { new Color(image.getRGB(x - 1, y - 1)), new Color(image.getRGB(x - 1, y)),
                new Color(image.getRGB(x - 1, y + 1)),
                new Color(image.getRGB(x, y - 1)), new Color(image.getRGB(x, y)), new Color(image.getRGB(x, y + 1)),
                new Color(image.getRGB(x + 1, y - 1)),
                new Color(image.getRGB(x + 1, y)), new Color(image.getRGB(x + 1, y + 1)) };
        int[][] currValues = getValues(arr);
        currValues = kernelMath(currValues);
        if (x == 1 && y == 1) {
            for (int[] ar : currValues) {
                for (int a : ar) {
                    System.out.print(a + " ");
                }
                System.out.println();
            }
            System.out.println("z: " + zero + ", 255: " + twofivefive);
        }
        return currValues;
    }

    protected int[][] kernelMath(int[][] arr) {
        float[] f = new float[9];
        f = kernel.getKernelData(f);
        for (int i = 0; i < 9; i++) {

            arr[0][i] = (int) (arr[0][i] * f[i]);
            arr[1][i] = (int) (arr[1][i] * f[i]);
            arr[2][i] = (int) (arr[2][i] * f[i]);
            if (arr[0][i] < 0 || arr[1][i] < 0 || arr[2][i] < 0)
                zero = true;
            if (arr[0][i] > 255 || arr[1][i] > 255 || arr[2][i] > 255)
                twofivefive = true;

        }
        arr = checkRange(arr);
        zero = false;
        twofivefive = false;
        return arr;
    }

    protected int[][] checkRange(int[][] arr) {

        try {
            int lowest = 0;
            int highest = 0;

            int[] ave = new int[] { 0, 0, 0 };

            int[][] arr2 = new int[3][9];
            for (int q = 0; q < 3; q++) {
                for (int qq = 0; qq < 9; qq++) {
                    arr2[q][qq] = arr[q][qq];
                    if (arr[q][qq] < lowest)
                        lowest = arr[q][qq];
                    if (arr[q][qq] > highest)
                        highest = arr[q][qq];

                    ave[q] += arr[q][qq];

                }
            }

            int change = 0;
            if (zero) {
                if (lowest < -127)
                    change = 128;
                else
                    change = 127;

            } else if (twofivefive) {

                change = -127;
            } else if (zero && twofivefive) {
                change = 0;
                System.out.println("shit");
            } else {

            }
            for (int i = 0; i < 9; i++) {
                // zero = false;
                // twofivefive = false;
                // change = 0;
                // if (arr[0][i] < 0 || arr[1][i] < 0 || arr[2][i] < 0)
                // zero = true;
                // if (arr[0][i] > 255 || arr[1][i] > 255 || arr[2][i] > 255)
                // twofivefive = true;

                // if (zero) {
                // if (lowest < -127)
                // change = 128;
                // else
                // change = 127;
                // change = Math.abs(lowest);

                // } else if (twofivefive) {

                // change = -127;
                // } else if (zero && twofivefive) {
                // change = 0;
                // System.out.println("shit");
                // } else {
                // change = 0;
                // }
                arr[0][i] = (arr[0][i] + change);
                arr[1][i] = (arr[1][i] + change);
                arr[2][i] = (arr[2][i] + change);

                if (0 > arr[0][i] || arr[0][i] > 255) {
                    if (!once) {

                        System.out.println("------------------");
                        System.out.println(Arrays.toString(arr2[0]));
                        System.out.println(Arrays.toString(arr2[1]));
                        System.out.println(Arrays.toString(arr2[2]));
                        System.out.println("------------------");
                        System.out.println(Arrays.toString(arr[0]));
                        System.out.println(Arrays.toString(arr[1]));
                        System.out.println(Arrays.toString(arr[2]));
                        System.out.println(zero + " z-t " + twofivefive);
                        System.out.println("change: " + change);
                        System.out.println("low: " + lowest + ", high: " + highest);
                        for (int a : ave) {
                            System.out.println((double) a / 9);
                        }
                        once = true;
                    }
                }
            }
        } catch (Exception e) {

        }

        return arr;
    }

    protected int[][] getValues(Color[] arr) {

        int[][] newArr = new int[3][9];
        // for(int c = 0; c < 3; c++){
        for (int i = 0; i < 9; i++) {
            newArr[0][i] = arr[i].getRed();
            newArr[1][i] = arr[i].getGreen();
            newArr[2][i] = arr[i].getBlue();
        }
        // }

        return newArr;
    }
}
