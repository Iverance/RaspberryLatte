package edu.sjsu.cab.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.sjsu.cab.common.CabConstant;
import edu.sjsu.cab.object.Freeway;

public class FreeWayTest {

    @Before
    public void setUp() {
        Freeway fwy101N = new Freeway();

        HashMap<Double, Double> fwy101N_LatLongs = new HashMap<Double, Double>();
        HashMap<Double, Double> fwy101S_LatLongs = new HashMap<Double, Double>();

        // add Exits for fwy101N/S data structure

    }

    @Test
    public void freeWay101STest() {
        ArrayList<List<Double>> latlong;
        Freeway fwy101S = new Freeway();
        fwy101S.setArrayOfExits(CabConstant.fwy101S_Exits);
        fwy101S.printExitList();
        fwy101S.setExitDescriptionArray(CabConstant.fwy101S_Exits, CabConstant.fwy101S_Desc);
        fwy101S.printDescriptionList();
        fwy101S.printExitAndDescriptionList();
        //latlong = fwy101S.latLongLoader();      
        //ArrayList<List<Double>> latlong = fwy101S.latLongLoader();
        //System.out.print(latlong.toString());
    }

}
