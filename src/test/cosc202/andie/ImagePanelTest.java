package test.cosc202.andie;

import org.junit.jupiter.api.Test;

import cosc202.andie.ImagePanel;

import org.junit.jupiter.api.Assertions;



public class ImagePanelTest{
    @Test

    void initalDummyTest(){

    }

    @Test
    void getZoomInitalValue(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100,0,testPanel.getZoom());

    }

    @Test
    void getZoomAftersetZoom0(){
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom()==100.0);
        Assertions.assertTrue(testPanel.getZoom()>=50.0);

    }

    
}
