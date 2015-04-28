package edu.sjsu.cab.test;

import org.junit.Before;
import org.junit.Test;

import edu.sjsu.cab.algorithm.ClarkeWrightMethod;
import edu.sjsu.cab.algorithm.MatrixLoader;

public class AlgorithmTest {

    ClarkeWrightMethod cwm;
    
    @Before
    public void setUp() {
        cwm = new ClarkeWrightMethod(MatrixLoader.randomMatrix(5));
    }

    @Test
    public void getRouteTest() {
        cwm.getRouteParallelly();
        System.out.print("\n" + cwm.getParallelTotalSavingValue() + "\n");
        cwm.getRouteSequentially();
        System.out.print("\n" + cwm.getSequentialTotalSavingValue());
    }

}
