package com.ritvik.monte.carlo.simulation.pi.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomCoordinateEval extends Thread{
    //Random Number Generator
    private final Random random;

    //Radius of Circle
    private final Double radius;

    //Thread Safe Circle Count
    private final AtomicInteger circleCount;

    //CountDownLatch to be used by thread invoker
    private final CountDownLatch countDownLatch;

    //Random Coordinate Evaluation count
    private int retry;

    /**
     * Create a thread with given radius of given circle and
     * atomic integer to maintain count if random coordinate is inside the circle
     *
     * @param radius         of given circle
     * @param circleCount    for coordinate inside circle
     * @param countDownLatch for thread invoker controls
     */
    public RandomCoordinateEval(Double radius, AtomicInteger circleCount, int retry, CountDownLatch countDownLatch){
        this.random=new Random();
        this.radius=radius;
        this.retry=retry;
        this.circleCount=circleCount;
        this.countDownLatch=countDownLatch;
    }

    /**
     * Run method to evaluate random coordinate and increase count if it is falling under circle
     */
    @Override
    public void run() {
        while (retry-->0){
            //Checking if random point is falling under circle or not
            double distanceFromCenter = Math.sqrt(Math.pow(random.nextInt(radius.intValue()),2)
                    +Math.pow(random.nextInt(radius.intValue()),2));
            if(distanceFromCenter<=radius){
                circleCount.incrementAndGet();
            }
        }

        //Mark Completion of the thread
        countDownLatch.countDown();
    }
}
