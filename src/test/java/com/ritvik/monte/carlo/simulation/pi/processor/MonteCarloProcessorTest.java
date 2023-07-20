package com.ritvik.monte.carlo.simulation.pi.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonteCarloProcessorTest {
    @Test
    public void testZeroCount(){
        try {
            double pi = MonteCarloProcessor.build(0.0, 0).estimateValueOfPi();
            Assertions.assertEquals(0.0,pi);
        } catch (Exception ex){
            Assertions.fail();
        }
    }

    @Test
    public void testRadiusOne(){
        try {
            double pi = MonteCarloProcessor.build(1.0, 1).estimateValueOfPi();
            Assertions.assertEquals(4.0,pi);
        } catch (Exception ex){
            Assertions.fail();
        }
    }
}
