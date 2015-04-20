package edu.sjsu.cab.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.sjsu.cab.algorithm.ClarkeWrightMethod;
import edu.sjsu.cab.algorithm.MatrixLoader;

public class AlgorithmTest {

    ClarkeWrightMethod cwm;
    
    @Before
    public void setUp() {
        cwm = new ClarkeWrightMethod(MatrixLoader.RandomMatrix(5));
    }

    @Test
    public void getRouteTest() {
        cwm.printRoute(cwm.getRouteParallelly());
        System.out.print("\n" + cwm.getParallelTotalSavingValue() + "\n");
        cwm.printRoute(cwm.getRouteSequentially());
        System.out.print("\n" + cwm.getSequentialTotalSavingValue());
    }

}
