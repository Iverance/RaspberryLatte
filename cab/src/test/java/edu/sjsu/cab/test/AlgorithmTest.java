package edu.sjsu.cab.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import edu.sjsu.cab.algorithm.ClarkeWrightMethod;
import edu.sjsu.cab.algorithm.MatrixLoader;

public class AlgorithmTest {

    ClarkeWrightMethod cwm;

    @Before
    public void setUp() {
        cwm = new ClarkeWrightMethod(MatrixLoader.randomMatrix(20));
    }

    @Test
    public void getRouteTest() {
        String[] pRoute = cwm.getRouteParallelly();
        Assert.notNull(pRoute);
        System.out.print("\n" + cwm.getParallelTotalSavingValue() + "\n");
        String[] sRoute = cwm.getRouteSequentially();
        Assert.notNull(sRoute);
        System.out.print("\n" + cwm.getSequentialTotalSavingValue());
    }

}
