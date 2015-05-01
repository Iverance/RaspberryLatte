package edu.sjsu.cab.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import edu.sjsu.cab.algorithm.AlgorithmProcessor;
import edu.sjsu.cab.object.Marker;
import edu.sjsu.cab.storage.Request;


public class ProceesorTest extends BaseTest{

    @Autowired
    AlgorithmProcessor algorithmProcessor;
    
    @Test
    public void getMarkersTest() {
        List<Marker> markers = algorithmProcessor.getMarkers();
        for(Marker marker:markers) {
            System.out.print(marker.getMessage()+'\n');
        }
        Assert.notEmpty(markers);
    }
    
}
