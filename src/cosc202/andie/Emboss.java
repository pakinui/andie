package cosc202.andie;

import java.awt.image.BufferedImage;
/**
 * <p>
 * ImageOperation to add emboss effects to the image and Sobel filters.
 * </p>
 * 
 * <p>
 * Method takes a kernel option given by the user and uses that kernel to apply one of the 8 emboss filters or 
 * one of the sobel filters. 
 * {@link BufferedImage} class. 
 * </p>
 * 
 * @author Pippi Priestley King
 * @version 1.0
 */
    public class Emboss implements ImageOperation{
        int kernelNo = 0;
       
    double [][] kernel = null;
       
        Emboss(int kernelNo){
        this.kernelNo = kernelNo;

        System.out.println(kernelNo);
        }

    public BufferedImage apply(BufferedImage image){
        setKernel(kernelNo);
        for (int row = 0; row < image.getHeight(); row ++) {
            for (int col = 0; col < image.getWidth(); col++) {
                
                int red =0;
                int green=0;
                 int blue=0;
                 int px=0;
       
              for (int row_offset = -1 ; row_offset < 2; row_offset++ ) {
                for (int col_offset = -1 ; col_offset < 2 ; col_offset++ ) {
       
                  // subtract by half the kernel size to center the kernel
                  // on the pixel in question
                  int row_index = Math.min(Math.max(0, row + row_offset), image.getHeight()-1);
                  int col_index = Math.min(Math.max(0, col + col_offset), image.getWidth()-1);
       /*
                  System.out.println(row_index);
                  System.out.println(col_index);
                  System.out.println(image.getHeight());
                  System.out.println(image.getWidth());*/
                  int p = image.getRGB(col_index, row_index);

                  int r = (p>>16)&0xff;
                  int g = (p>>8)&0xff;
                  int b = p&0xff;
          
                  red += r * kernel[row_offset+1][col_offset+1];
                  green += g* kernel[row_offset+1][col_offset+1];
                  blue +=b * kernel[row_offset+1][col_offset+1];

                
                }
                
              }
             // System.out.println(red + ", " + green + ", " + blue);
              red+=127;
              green+=127;
              blue+=127;
              int a = (image.getRGB(col, row)>>24)&0xff;
              px = (a<<24) | (red<<16) | (green<<8) | blue;
              image.setRGB(col, row, px);
       
            }
          }
     
    return image;
    }

    public void setKernel(int kernelNo){
        // depending what emboss filter they chose
    if(kernelNo== 1){
     kernel = new double [][] {{0, 0, 0},{1, 0, -1},{ 0, 0, 0}}; }
    if(kernelNo == 2){kernel = new double[][]{{1, 0, 0},{0, 0, 0},{ 0, 0, 1}}; }
    if(kernelNo == 3){
    kernel = new double[][]{{0, 1, 0},{0, 0, 0},{0, -1, 0}}; }
    if(kernelNo == 4){
    kernel = new double[][]{{0, 0, 1},{0, 0,0},{ -1, 0, 0}}; }

    if(kernelNo == 5){
    kernel = new double[][]{{0, 0, 0},{-1, 0, 1},{ 0, 0, 0}}; }
    if(kernelNo == 6){
    kernel = new double[][]{{-1, 0, 0},{0, 0, 0},{0, 0, 1}};}
    if(kernelNo == 7){
    kernel = new double[][]{{0, -1, 0},{0, 0, 0},{0, 1, 0}}; }
    if(kernelNo == 8){
    kernel = new double[][]{{0, 0, -1},{0, 0, 0},{1, 0, 0}};  }
    if(kernelNo == 9){
        kernel = new double[][]{{(-1/2), -1, -(1/2)},{0, 0, 0},{1/2, 1, (1/2)}};  }
    if(kernelNo == 10){
            kernel = new double[][]{{-(1/2), 0, (1/2)},{-1, 0, 1},{-(1/2), 0, (1/2)}};  }

    }
}
