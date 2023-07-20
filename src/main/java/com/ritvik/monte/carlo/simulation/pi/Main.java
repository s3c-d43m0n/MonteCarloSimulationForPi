package com.ritvik.monte.carlo.simulation.pi;

import com.ritvik.monte.carlo.simulation.pi.processor.MonteCarloProcessor;

import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getAnonymousLogger();

    public static void main(String[] args) throws InterruptedException {
        double radius = 50000.0;
        int total_simulation = 1000000;
        log.info("Estimated Value of PI: "+MonteCarloProcessor.build(radius, total_simulation).estimateValueOfPi());
    }
}