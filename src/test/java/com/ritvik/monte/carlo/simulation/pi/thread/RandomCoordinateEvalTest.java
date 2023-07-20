package com.ritvik.monte.carlo.simulation.pi.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomCoordinateEvalTest {
    @Test
    public void testRadiusZero(){
        try {
            RandomCoordinateEval randomCoordinateEval = new RandomCoordinateEval(0.0, new AtomicInteger(),5, new CountDownLatch(1));
            randomCoordinateEval.run();
            Assertions.fail();
        } catch (Exception ex){
            Assertions.assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testRetryZero(){
        try {
            AtomicInteger atomicInt = new AtomicInteger();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new RandomCoordinateEval(0.0, atomicInt,0, countDownLatch).start();
            countDownLatch.await();
            Assertions.assertEquals(0,atomicInt.get());
        } catch (Exception ex){
            Assertions.fail();
        }
    }
}
