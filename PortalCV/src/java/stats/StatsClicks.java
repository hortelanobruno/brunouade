/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 *
 * @author Brunoli
 */
public class StatsClicks {

    private long redirectedPortal = 0;
    private long redirectedPortalPeak = 0;
    private long home = 0;
    private long homePeak = 0;
    private long premium = 0;
    private long premiumPeak = 0;
    private long standard = 0;
    private long standardPeak = 0;
    private long premiumSuccess = 0;
    private long premiumSuccessPeak = 0;
    private long standardEmail = 0;
    private long standardEmailPeak = 0;
    private long standardEmailSuccess = 0;
    private long standardEmailSuccessPeak = 0;
    private long standardFacebook = 0;
    private long standardFacebookPeak = 0;
    private long standardFacebookSuccess = 0;
    private long standardFacebookSuccessPeak = 0;
    private long standardGoogle = 0;
    private long standardGooglePeak = 0;
    private long standardGoogleSuccess = 0;
    private long standardGoogleSuccessPeak = 0;
    private long standardTwitter = 0;
    private long standardTwitterPeak = 0;
    private long standardTwitterSuccess = 0;
    private long standardTwitterSuccessPeak = 0;
    private long loginCoASuccess = 0;
    private long loginCoASuccessPeak = 0;
    private long loginCoAFail = 0;
    private long loginCoAFailPeak = 0;
    private long logoffCoASuccess = 0;
    private long logoffCoASuccessPeak = 0;
    private long logoffCoAFail = 0;
    private long logoffCoAFailPeak = 0;
    private long autoLoginSuccess = 0;
    private long autoLoginSuccessPeak = 0;
    private long autoLoginFail = 0;
    private long autoLoginFailPeak = 0;

    public StatsClicks() {
    }

    public String printStats() {
        return "StatsClicks{" + "redirectedPortal=" + redirectedPortal + ", redirectedPortalPeak=" + redirectedPortalPeak + ", home=" + home + ", homePeak=" + homePeak + ", premium=" + premium + ", premiumPeak=" + premiumPeak + ", standard=" + standard + ", standardPeak=" + standardPeak + ", premiumSuccess=" + premiumSuccess + ", premiumSuccessPeak=" + premiumSuccessPeak + ", standardEmail=" + standardEmail + ", standardEmailPeak=" + standardEmailPeak + ", standardEmailSuccess=" + standardEmailSuccess + ", standardEmailSuccessPeak=" + standardEmailSuccessPeak + ", standardFacebook=" + standardFacebook + ", standardFacebookPeak=" + standardFacebookPeak + ", standardFacebookSuccess=" + standardFacebookSuccess + ", standardFacebookSuccessPeak=" + standardFacebookSuccessPeak + ", standardGoogle=" + standardGoogle + ", standardGooglePeak=" + standardGooglePeak + ", standardGoogleSuccess=" + standardGoogleSuccess + ", standardGoogleSuccessPeak=" + standardGoogleSuccessPeak + ", standardTwitter=" + standardTwitter + ", standardTwitterPeak=" + standardTwitterPeak + ", standardTwitterSuccess=" + standardTwitterSuccess + ", standardTwitterSuccessPeak=" + standardTwitterSuccessPeak + ", loginCoASuccess=" + loginCoASuccess + ", loginCoASuccessPeak=" + loginCoASuccessPeak + ", loginCoAFail=" + loginCoAFail + ", loginCoAFailPeak=" + loginCoAFailPeak + ", logoffCoASuccess=" + logoffCoASuccess + ", logoffCoASuccessPeak=" + logoffCoASuccessPeak + ", logoffCoAFail=" + logoffCoAFail + ", logoffCoAFailPeak=" + logoffCoAFailPeak + ", autoLoginSuccess=" + autoLoginSuccess + ", autoLoginSuccessPeak=" + autoLoginSuccessPeak + ", autoLoginFail=" + autoLoginFail + ", autoLoginFailPeak=" + autoLoginFailPeak + '}';
    }

    public void reset() {
        redirectedPortal = 0;
        home = 0;
        premium = 0;
        premiumSuccess = 0;
        standard = 0;
        standardEmail = 0;
        standardEmailSuccess = 0;
        standardFacebook = 0;
        standardFacebookSuccess = 0;
        standardGoogle = 0;
        standardGoogleSuccess = 0;
        standardTwitter = 0;
        standardTwitterSuccess = 0;
        loginCoASuccess = 0;
        loginCoAFail = 0;
        logoffCoASuccess = 0;
        logoffCoAFail = 0;
        autoLoginSuccess = 0;
        autoLoginFail = 0;
    }

    public void incrementRedirectedPortal() {
        redirectedPortal++;
        if (redirectedPortal > redirectedPortalPeak) {
            redirectedPortalPeak = redirectedPortal;
        }
    }

    public void incrementHome() {
        home++;
        if (home > homePeak) {
            homePeak = home;
        }
    }

    public void incrementPremium() {
        premium++;
        if (premium > premiumPeak) {
            premiumPeak = premium;
        }
    }

    public void incrementStandard() {
        standard++;
        if (standard > standardPeak) {
            standardPeak = standard;
        }
    }

    public void incrementStandardEmail() {
        standardEmail++;
        if (standardEmail > standardEmailPeak) {
            standardEmailPeak = standardEmail;
        }
    }

    public void incrementStandardFacebook() {
        standardFacebook++;
        if (standardFacebook > standardFacebookPeak) {
            standardFacebookPeak = standardFacebook;
        }
    }

    public void incrementStandardGoogle() {
        standardGoogle++;
        if (standardGoogle > standardGooglePeak) {
            standardGooglePeak = standardGoogle;
        }
    }

    public void incrementStandardTwitter() {
        standardTwitter++;
        if (standardTwitter > standardTwitterPeak) {
            standardTwitterPeak = standardTwitter;
        }
    }

    public void incrementPremiumSuccess() {
        premiumSuccess++;
        if (premiumSuccess > premiumSuccessPeak) {
            premiumSuccessPeak = premiumSuccess;
        }
    }

    public void incrementStandardEmailSuccess() {
        standardEmailSuccess++;
        if (standardEmailSuccess > standardEmailSuccessPeak) {
            standardEmailSuccessPeak = standardEmailSuccess;
        }
    }

    public void incrementStandardFacebookSuccess() {
        standardFacebookSuccess++;
        if (standardFacebookSuccess > standardFacebookSuccessPeak) {
            standardFacebookSuccessPeak = standardFacebookSuccess;
        }
    }

    public void incrementStandardGoogleSuccess() {
        standardGoogleSuccess++;
        if (standardGoogleSuccess > standardGoogleSuccessPeak) {
            standardGoogleSuccessPeak = standardGoogleSuccess;
        }
    }

    public void incrementStandardTwitterSuccess() {
        standardTwitterSuccess++;
        if (standardTwitterSuccess > standardTwitterSuccessPeak) {
            standardTwitterSuccessPeak = standardTwitterSuccess;
        }
    }

    public void incrementLoginCoASuccess() {
        loginCoASuccess++;
        if (loginCoASuccess > loginCoASuccessPeak) {
            loginCoASuccessPeak = loginCoASuccess;
        }
    }

    public void incrementLoginCoAFail() {
        loginCoAFail++;
        if (loginCoAFail > loginCoAFailPeak) {
            loginCoAFailPeak = loginCoAFail;
        }
    }

    public void incrementLogoffCoASuccess() {
        logoffCoASuccess++;
        if (logoffCoASuccess > logoffCoASuccessPeak) {
            logoffCoASuccessPeak = logoffCoASuccess;
        }
    }

    public void incrementLogoffCoAFail() {
        logoffCoAFail++;
        if (logoffCoAFail > logoffCoAFailPeak) {
            logoffCoAFailPeak = logoffCoAFail;
        }
    }

    public void incrementAutoLoginSuccess() {
        autoLoginSuccess++;
        if (autoLoginSuccess > autoLoginSuccessPeak) {
            autoLoginSuccessPeak = autoLoginSuccess;
        }
    }

    public void incrementAutoLoginFail() {
        autoLoginFail++;
        if (autoLoginFail > autoLoginFailPeak) {
            autoLoginFailPeak = autoLoginFail;
        }
    }
}
