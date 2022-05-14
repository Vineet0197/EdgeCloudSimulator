package org.vin.edgecloudsim.utility;

import cern.jet.random.Poisson;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PoissonDistr {
    Poisson poisson;
    RandomEngine engine;

    /**
     * Creates a new exponential number generator.
     *
     * @param mean the mean for the distribution.
     */
    public PoissonDistr(double mean) {
        engine = new MersenneTwister(new Date());
        poisson = new Poisson(mean, engine);

        //always sleep for some milliseconds in order not to have same seed for iterative PoissonDistr construction
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            SimLogger.printLine("impossible is occurred! Poisson random number cannot be created!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Generate a new random number.
     *
     * @return the next random number in the sequence
     */
    public double sample() {
        return poisson.nextDouble();
    }
}
