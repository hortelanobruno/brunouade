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
public class ManagerStatsClicks {

    private static ManagerStatsClicks self;
    private Logger logger = Logger.getLogger("stats-clicks");
    private StatsClicks stats;
    private ScheduledExecutorService service;

    private ManagerStatsClicks() {
        logger.info("Initing...");
        stats = new StatsClicks();
        initTimer();
        logger.info("Inited.");
    }

    public static ManagerStatsClicks getInstance() {
        if (self == null) {
            self = new ManagerStatsClicks();
        }
        return self;
    }

    public void incrementRedirectedPortal() {
        stats.incrementRedirectedPortal();
    }

    public void incrementHome() {
        stats.incrementHome();
    }

    public void incrementPremium() {
        stats.incrementPremium();
    }

    public void incrementPremiumSuccess() {
        stats.incrementPremiumSuccess();
    }

    public void incrementStandard() {
        stats.incrementStandard();
    }

    public void incrementStandardEmail() {
        stats.incrementStandardEmail();
    }

    public void incrementStandardEmailSuccess() {
        stats.incrementStandardEmailSuccess();
    }

    public void incrementStandardFacebook() {
        stats.incrementStandardFacebook();
    }

    public void incrementStandardFacebookSuccess() {
        stats.incrementStandardFacebookSuccess();
    }

    public void incrementStandardGoogle() {
        stats.incrementStandardGoogle();
    }

    public void incrementStandardGoogleSuccess() {
        stats.incrementStandardGoogleSuccess();
    }

    public void incrementStandardTwitter() {
        stats.incrementStandardTwitter();
    }

    public void incrementStandardTwitterSuccess() {
        stats.incrementStandardTwitterSuccess();
    }

    public void incrementLoginCoASuccess() {
        stats.incrementLoginCoASuccess();
    }

    public void incrementLoginCoAFail() {
        stats.incrementLoginCoAFail();
    }

    public void incrementLogoffCoASuccess() {
        stats.incrementLogoffCoASuccess();
    }

    public void incrementLogoffCoAFail() {
        stats.incrementLogoffCoAFail();
    }

    public void incrementAutoLoginSuccess() {
        stats.incrementAutoLoginSuccess();
    }

    public void incrementAutoLoginFail() {
        stats.incrementAutoLoginFail();
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
            logger.info(ManagerStatsClicks.getInstance().printStats());
        }
    }
}
