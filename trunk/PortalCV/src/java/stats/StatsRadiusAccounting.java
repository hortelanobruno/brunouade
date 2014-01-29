/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 *
 * @author Brunoli
 */
public class StatsRadiusAccounting {

    private long acctStart = 0;
    private long acctAlive = 0;
    private long acctStop = 0;
    private long accStart = 0;
    private long accAlive = 0;
    private long accStop = 0;
    private long acctStartPeak = 0;
    private long acctAlivePeak = 0;
    private long acctStopPeak = 0;
    private long accStartPeak = 0;
    private long accAlivePeak = 0;
    private long accStopPeak = 0;

    public StatsRadiusAccounting() {
    }

    public String printStats() {
        return "StatsRadius{" + "acctStart=" + acctStart + ", acctAlive=" + acctAlive + ", acctStop=" + acctStop + ", accStart=" + accStart + ", accAlive=" + accAlive + ", accStop=" + accStop + ", acctStartPeak=" + acctStartPeak + ", acctAlivePeak=" + acctAlivePeak + ", acctStopPeak=" + acctStopPeak + ", accStartPeak=" + accStartPeak + ", accAlivePeak=" + accAlivePeak + ", accStopPeak=" + accStopPeak + '}';
    }

    public void reset() {
        acctStart = 0;
        acctAlive = 0;
        acctStop = 0;
        accStart = 0;
        accAlive = 0;
        accStop = 0;
    }

    public void incrementAcctStart() {
        acctStart++;
        if (acctStart > acctStartPeak) {
            acctStartPeak = acctStart;
        }
    }

    public void incrementAcctAlive() {
        acctAlive++;
        if (acctAlive > acctAlivePeak) {
            acctAlivePeak = acctAlive;
        }
    }

    public void incrementAcctStop() {
        acctStop++;
        if (acctStop > acctStopPeak) {
            acctStopPeak = acctStop;
        }
    }

    public void incrementAccStart() {
        accStart++;
        if (accStart > accStartPeak) {
            accStartPeak = accStart;
        }
    }

    public void incrementAccAlive() {
        accAlive++;
        if (accAlive > accAlivePeak) {
            accAlivePeak = accAlive;
        }
    }

    public void incrementAccStop() {
        accStop++;
        if (accStop > accStopPeak) {
            accStopPeak = accStop;
        }
    }
}
