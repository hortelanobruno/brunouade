/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 *
 * @author Brunoli
 */
public class StatsRadiusCoA {

    private long logon = 0;
    private long logoff = 0;
    private long activateService = 0;
    private long deactivateService = 0;
    private long sessionQuery = 0;
    private long logonPeak = 0;
    private long logoffPeak = 0;
    private long activateServicePeak = 0;
    private long deactivateServicePeak = 0;
    private long sessionQueryPeak = 0;

    public StatsRadiusCoA() {
    }

    public String printStats() {
        return "StatsRadiusCoA{" + "logon=" + logon + ", logoff=" + logoff + ", activateService=" + activateService + ", deactivateService=" + deactivateService + ", sessionQuery=" + sessionQuery + ", logonPeak=" + logonPeak + ", logoffPeak=" + logoffPeak + ", activateServicePeak=" + activateServicePeak + ", deactivateServicePeak=" + deactivateServicePeak + ", sessionQueryPeak=" + sessionQueryPeak + '}';
    }

    public void reset() {
        logon = 0;
        logoff = 0;
        activateService = 0;
        deactivateService = 0;
        sessionQuery = 0;
    }

    public void incrementLogon() {
        logon++;
        if (logon > logonPeak) {
            logonPeak = logon;
        }
    }

    public void incrementLogoff() {
        logoff++;
        if (logoff > logoffPeak) {
            logoffPeak = logoff;
        }
    }

    public void incrementActivateService() {
        activateService++;
        if (activateService > activateServicePeak) {
            activateServicePeak = activateService;
        }
    }

    public void incrementDeactivateService() {
        deactivateService++;
        if (deactivateService > deactivateServicePeak) {
            deactivateServicePeak = deactivateService;
        }
    }

    public void incrementSessionQuery() {
        sessionQuery++;
        if (sessionQuery > sessionQueryPeak) {
            sessionQueryPeak = sessionQuery;
        }
    }
}
