/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author Brunoli
 */
public class ManagerStatsRadiusAccounting {

    private Logger logger = Logger.getLogger("stats-radius-accounting");
    private static ManagerStatsRadiusAccounting self;
    private StatsRadiusAccounting stats;
    private ScheduledExecutorService service;

    private ManagerStatsRadiusAccounting() {
        logger.info("Initing...");
        stats = new StatsRadiusAccounting();
        initTimer();
        logger.info("Inited.");
    }

    public static ManagerStatsRadiusAccounting getInstance() {
        if (self == null) {
            self = new ManagerStatsRadiusAccounting();
        }
        return self;
    }

    public void incrementAcctStart() {
        stats.incrementAcctStart();
    }

    public void incrementAcctAlive() {
        stats.incrementAcctAlive();
    }

    public void incrementAcctStop() {
        stats.incrementAcctStop();
    }

    public void incrementAccStart() {
        stats.incrementAccStart();
    }

    public void incrementAccAlive() {
        stats.incrementAccAlive();
    }

    public void incrementAccStop() {
        stats.incrementAccStop();
    }

    public String printStats() {
        String print = stats.printStats();
        stats.reset();
        return print;
    }

    private void initTimer() {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Printer(), 1, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        logger.info("Stopping...");
        service.shutdown();
        logger.info("Stoped.");
    }

    private class Printer implements Runnable {

        @Override
        public void run() {
            logger.info(ManagerStatsRadiusAccounting.getInstance().printStats());
        }
    }
}
