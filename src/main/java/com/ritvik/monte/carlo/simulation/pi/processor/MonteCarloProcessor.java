package com.ritvik.monte.carlo.simulation.pi.processor;

import com.ritvik.monte.carlo.simulation.pi.thread.RandomCoordinateEval;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloProcessor{

    //Radius of circle
    private final double radius;

    //Total Simulation Count
    private final int total;

    //Atomic integer to get count of points evaluated in circle
    private final AtomicInteger circleCount;

    /**
     * Create new simulation with given radius and total count
     * @param radius of given circle
     * @param total count of simulation
     */
    public static MonteCarloProcessor build(double radius, int total){
        return new MonteCarloProcessor(radius, total);
    }
    /**
     * Create new simulation with given radius and total count
     * @param radius of given circle
     * @param total count of simulation
     */
    private MonteCarloProcessor(double radius, int total){
        this.radius=radius;
        this.total=total;
        this.circleCount=new AtomicInteger(0);

    }

    //Run Simulation and estimate value of PI
    public double estimateValueOfPi() throws InterruptedException {
        //if no simulations are required
        if(total==0){
            return 0.0;
        }

        //run multiple threads in parallel
        int threadCount = Runtime.getRuntime().availableProcessors();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        RandomCoordinateEval[] threads = new RandomCoordinateEval[threadCount];

        //Divide Total among threads
        for(int i=1; i<threadCount; i++){
            threads[i] = new RandomCoordinateEval(radius,circleCount,total/threadCount, countDownLatch);
            threads[i].start();
        }

        //Remaining Count after dividing total
        threads[0] = new RandomCoordinateEval(radius,circleCount,(total/threadCount)+(total%threadCount), countDownLatch);
        threads[0].start();

        //waiting for other threads to complete
        countDownLatch.await();

        //return estimated value of PI
        return 4.0*( (double)circleCount.get() / (double)total);
    }
}
