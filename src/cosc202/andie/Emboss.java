package cosc202.andie;

import java.awt.image.BufferedImage;

import java.awt.image.*;

    public class Emboss implements ImageOperation{
        int kernelNo = 0;
        Kernel kernel = null;
       
        Emboss(int kernelNo){
        this.kernelNo = kernelNo;

        System.out.println(kernelNo);
        }

    public BufferedImage apply(BufferedImage image){
        
        setKernel(kernelNo);
        // int p = image.getRGB(0,0);
        // int a = (p>>24)&0xff;
        // int r = (p>>16)&0xff;
        // int g = (p>>8)&0xff;
        // int b = p&0xff;

        System.out.println(kernelNo);

    

    ConvolveOp convOp = new ConvolveOp(kernel); // Constructs a ConvloveOp with the given Kernel.
    BufferedImage output = new BufferedImage(image.getColorModel(), image.copyData(null),
            image.isAlphaPremultiplied(), null); // Constructs a new BufferedImage with a ColorModel and Raster
                                                 // and
                                                 // is AlphaPremulitplied returned true.
    convOp.filter(image, output);

     
    return output;
    }

    public void setKernel(int kernelNo){

        // depending what emboss filter they chose
    if(kernelNo== 1){
        kernel = new Kernel(3, 3,
    new float[] {0, 0, 0,1, 0, -1, 0, 0, 0}); }
    if(kernelNo == 2){kernel = new Kernel(3, 3,
    new float[] {1, 0, 0,0, 0, 0, 0, 0, 1}); }
    if(kernelNo == 3){
    kernel = new Kernel(3, 3,
    new float[] {0, 1, 0,0, 0, 0, 0, -1, 0}); }
    if(kernelNo == 4){
    kernel = new Kernel(3, 3,
    new float[] {0, 0, 1,0, 0,0, -1, 0, 0}); }

    if(kernelNo == 5){
    kernel = new Kernel(3, 3,
    new float[] {0, 0, 0,-1, 0, 1, 0, 0, 0}); }
    if(kernelNo == 6){
    kernel = new Kernel(3, 3,
    new float[] {-1, 0, 0,0, 0, 0, 0, 0, 1});}
    if(kernelNo == 7){
    kernel = new Kernel(3, 3,
    new float[] {0, -1, 0,0, 0, 0, 0, 1, 0}); }
    if(kernelNo == 8){
    kernel = new Kernel(3, 3,
    new float[] {0, 0, -1,0, 0, 0, 1, 0, 0});  }
    if(kernelNo == 9){
        kernel = new Kernel(3, 3,
        new float[] {-1/2, -1, -1/2,0, 0, 0, 1/2, 1, 1/2});  }
    if(kernelNo == 10){
            kernel = new Kernel(3, 3,
            new float[] {-1/2, 0, 1/2,-1, 0, 1, -1/2, 0, 1/2});  }

    }
}
