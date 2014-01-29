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
public class ManagerStatsRadiusCoA {

    private Logger logger = Logger.getLogger("stats-radius-coa");
    private static ManagerStatsRadiusCoA self;
    private StatsRadiusCoA stats;
    private ScheduledExecutorService service;

    private ManagerStatsRadiusCoA() {
        logger.info("Initing...");
        stats = new StatsRadiusCoA();
        initTimer();
        logger.info("Inited.");
    }

    public static ManagerStatsRadiusCoA getInstance() {
        if (self == null) {
            self = new ManagerStatsRadiusCoA();
        }
        return self;
    }

    public void incrementLogon() {
        stats.incrementLogon();
    }

    public void incrementLogoff() {
        stats.incrementLogoff();
    }

    public void incrementActivateService() {
        stats.incrementActivateService();
    }

    public void incrementDeactivateService() {
        stats.incrementDeactivateService();
    }

    public void incrementSessionQuery() {
        stats.incrementSessionQuery();
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
            logger.info(ManagerStatsRadiusCoA.getInstance().printStats());
        }
    }
}
