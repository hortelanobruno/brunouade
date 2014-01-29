/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ClientLogin;
import entity.PortalcvAutologin;
import entity.PortalcvHomeresource;
import entity.PortalcvWlcaccounting;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import radiuscoa.RadiusCoaResult;
import session.LoginSessionBean;
import session.RadiusServerBean;
import stats.ManagerStatsClicks;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import util.ActivateUserResult;
import util.AutoLoginResult;
import static util.AutoLoginResult.CLIENTE;
import static util.AutoLoginResult.INVALID_NO_CLIENTE;
import static util.AutoLoginResult.OTHER;
import static util.AutoLoginResult.VALID_NO_CLIENTE;
import util.ConexionType;
import static util.ConexionType.PREMIUM;
import static util.ConexionType.STANDARD;
import util.GoogleAuthHelper;
import util.LoginResult;
import static util.LoginResult.SUCCESSFULL;
import util.LoginType;
import static util.LoginType.FACEBOOK;
import static util.LoginType.GOOGLE;
import static util.LoginType.MUNDO_CLIENTE;
import static util.LoginType.VIDEO;

/**
 *
 * @author bruno
 */
@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns = {
    "/inicio",
    "/home",
    "/terminosP",
    "/terminosS",
    "/connectCV",
    "/connectNOCV",
    "/registernocv",
    "/confirmation",
    "/loginCV",
    "/loginCV2",
    "/loginNOCV",
    "/loginNOCVFacebook",
    "/loginNOCVGoogle",
    "/loginNOCVTwitter",
    "/callbackFacebook",
    "/login_success_facebook_nocv",
    "/login_success_google_nocv",
    "/oauth2callback",
    "/login_success_twitter_nocv",
    "/library/test/success.html",
    "/condiciones",
    "/validateUserAuth",
    "/autoLogin",
    "/autoLoginNOCV",
    "/select/wifiloginsuccess/",
    "/logout",
    "/signout",
    "/macap",
    "/fastLogin",
    "/changeToPremium",
    "/premium",
    "/standard",
    "/loginFibertel",
    "/validateFibertel",
    "/loginFacebook",
    "/loginGoogle",
    "/loginVideo",
    "/welcome"
})
public class ControllerServlet extends HttpServlet {

    @EJB
    private LoginSessionBean loginSessionBean;
    @EJB
    private RadiusServerBean radiusServerBean;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerLogin = Logger.getLogger("portalLogin");
    private Logger loggerError = Logger.getLogger("portalError");
    private Logger loggerProfiling = Logger.getLogger("portalProfiling");
    private String userPath;
    public static final String INDEX_PAGE = "/";
    public Integer DEFAULT_HOME_ID = 4;
    public static final String CPE_NA = "N/A";
    public static final int AP_NA = -1;
    private static Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        logger.debug("init...");
        radiusServerBean.initRadiusServer();
        try {
            DEFAULT_HOME_ID = Integer.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("defaulthomepage"));
        } catch (Exception ex) {
        }
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            userPath = request.getServletPath();
            if (userPath.equals("/inicio")) {
                //index
                inicio(request, response);
                return;
            } else if (userPath.equals("/home")) {
                //home
                ManagerStatsClicks.getInstance().incrementHome();
                home(request, response);
                return;
            } else if (userPath.equals("/connectCV")) {
                //premium
                ManagerStatsClicks.getInstance().incrementPremium();
                connectCV(request, response);
                return;
            } else if (userPath.equals("/connectNOCV")) {
                //standard
                ManagerStatsClicks.getInstance().incrementStandard();
                connectNOCV(request, response);
                return;
            } else if (userPath.equals("/registernocv")) {
                //puso mail para standard
                ManagerStatsClicks.getInstance().incrementStandardEmail();
                registernocv(request, response);
                return;
            } else if (userPath.equals("/confirmation")) {
                //confirmo link de standard
                ManagerStatsClicks.getInstance().incrementStandardEmailSuccess();
                confirmation(request, response);
                return;
            } else if (userPath.equals("/loginNOCV")) {
                //aca llega cuando se valido correctamente el link de confirmacion
                loginNOCV(request, response);
                return;
            } else if (userPath.equals("/loginNOCVFacebook")) {
                //aca llega cuando elije facebook
                ManagerStatsClicks.getInstance().incrementStandardFacebook();
                loginNOCVFacebook(request, response);
                return;
            } else if (userPath.equals("/loginNOCVGoogle")) {
                //aca llega cuando elije google
                ManagerStatsClicks.getInstance().incrementStandardGoogle();
                loginNOCVGoogle(request, response);
                return;
            } else if (userPath.equals("/loginNOCVTwitter")) {
                //aca llega cuando elije twitter
                ManagerStatsClicks.getInstance().incrementStandardTwitter();
                loginNOCVTwitter(request, response);
                return;
            } else if (userPath.equals("/callbackFacebook")) {
                callbackFacebook(request, response);
                return;
            } else if (userPath.equals("/login_success_facebook_nocv")) {
                //aca llega cuando se valida en facebook
                ManagerStatsClicks.getInstance().incrementStandardFacebookSuccess();
                login_success_facebook_nocv(request, response);
                return;
            } else if (userPath.equals("/oauth2callback")) {
                //aca llega cuando se valida en google
                ManagerStatsClicks.getInstance().incrementStandardGoogleSuccess();
                login_success_google_nocv(request, response);
                return;
            } else if (userPath.equals("/login_success_google_nocv")) {
                //aca llega cuando se valida en google
                ManagerStatsClicks.getInstance().incrementStandardGoogleSuccess();
                login_success_google_nocv(request, response);
                return;
            } else if (userPath.equals("/login_success_twitter_nocv")) {
                //aca llega cuando se valida en twitter
                ManagerStatsClicks.getInstance().incrementStandardTwitterSuccess();
                login_success_twitter_nocv(request, response);
                return;
            } else if (userPath.equals("/condiciones")) {
                //Aca carga los terminos y condiciones
                condiciones(request, response);
                return;
            } else if (userPath.equals("/loginCV")) {
                //Aca llega cuando se valido correctamente en la sucursal virtual
                ManagerStatsClicks.getInstance().incrementPremiumSuccess();
                loginCV(request, response);
                return;
            } else if (userPath.equals("/loginCV2")) {
                //Aca llega para terminar la validacion de un usuario premium
                loginCV2(request, response);
                return;
            } else if (userPath.equals("/terminosP")) {
                terminosP(request, response);
                return;
            } else if (userPath.equals("/terminosS")) {
                terminosS(request, response);
                return;
            } else if (userPath.equals("/validateUserAuth")) {
                //Este metodo me parece que esta rancio, aca llega cuando antes en la pagina del home detectabamos que tenia que hacer un autologin
                validateUserAuth(request, response);
                return;
            } else if (userPath.equals("/autoLogin")) {
                //Autologin cliente
                autoLogin(request, response);
                return;
            } else if (userPath.equals("/autoLoginNOCV")) {
                //Autologin no cliente
                autoLoginNOCV(request, response);
                return;
            } else if (userPath.equals("/changeToPremium")) {
                //Aca llega cuando a partir de un autologin, un no cliente se quiere pasar a premium
                changeToPremium(request, response);
                return;
            } else if (userPath.equals("/library/test/success.html")) {
                request.getRequestDispatcher("/success.html").forward(request, response);
                return;
            } else if (userPath.equals("/select/wifiloginsuccess/")) {
                request.getRequestDispatcher("/successBB.jsp").forward(request, response);
                return;
            } else if (userPath.equals("/logout")) {
                logout(request, response);
                return;
            } else if (userPath.equals("/signout")) {
                signout(request, response);
                return;
            } else if (userPath.equals("/macap")) {
                macap(request, response);
                return;
            } else if (userPath.equals("/fastLogin")) {
                fastLogin(request, response);
                return;
            } else if (userPath.equals("/premium")) {
                premium(request, response);
                return;
            } else if (userPath.equals("/standard")) {
                standard(request, response);
                return;
            } else if (userPath.equals("/loginFibertel")) {
                loginFibertel(request, response);
                return;
            } else if (userPath.equals("/validateFibertel")) {
                validateFibertel(request, response);
                return;
            } else if (userPath.equals("/loginFacebook")) {
                loginFacebook(request, response);
                return;
            } else if (userPath.equals("/loginGoogle")) {
                loginGoogle(request, response);
                return;
            } else if (userPath.equals("/loginVideo")) {
                loginVideo(request, response);
                return;
            } else if (userPath.equals("/welcome")) {
                welcome(request, response);
                return;
            }
            loggerError.debug("ACA NO DEBERIA LLEGAR. get " + userPath + ". from " + request.getRemoteAddr() + ". request.getServletPath(): " + request.getServletPath() + "...");
        } catch (Exception ex) {
            loggerError.error("Error core ControllerServlet. userPath: " + userPath, ex);
        }
        userPath = "/default/home.jsp";
        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (Exception ex) {
            loggerError.error("Error request dispacher general. userPath: " + userPath, ex);
        }
        logger.debug("fin get " + userPath + ". from " + request.getRemoteAddr() + "...");
    }

    /**
     * Este metodo te renderea el index. Multiples portales segun AP.
     *
     * @param request
     * @param response
     */
    private void inicio(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("/inicio START.");
        loggerLogin.debug("Login at " + Calendar.getInstance().getTime().toGMTString() + " from " + request.getRemoteAddr() + " with useragent: " + request.getHeader("user-agent"));
        try {
            long aux = System.currentTimeMillis();
            HttpSession session = request.getSession();
            PortalcvHomeresource homeResource;
            PortalcvWlcaccounting wlcAcc = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
            logger.debug("wlcAcc: " + wlcAcc);
            if (wlcAcc != null) {
                session.setAttribute("HOMEID", wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes());
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/inicio", "default");
                userPath = homeResource.getFilePath();
            } else {
                //Este caso se da cuando se prueba en desarrollo, osea no hay wlcAccounting
                session.setAttribute("HOMEID", DEFAULT_HOME_ID);
                homeResource = loginSessionBean.getPortalLayout(DEFAULT_HOME_ID, "/inicio", "default");
                userPath = homeResource.getFilePath();
            }
            request.getRequestDispatcher(userPath).forward(request, response);
            logger.debug("/inicio FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            loggerProfiling.debug("/inicio from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
        } catch (Exception ex) {
            loggerError.error("Error en /inicio. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
        logger.debug("/inicio FIN.");
    }

    /**
     * Este metodo te renderea el home. Multiples portales segun AP.
     *
     * @param request
     * @param response
     */
    private void home(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("/home START.");
        loggerLogin.debug("Login at " + Calendar.getInstance().getTime().toGMTString() + " from " + request.getRemoteAddr() + " with useragent: " + request.getHeader("user-agent"));
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                PortalcvWlcaccounting wlcAcc = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
                logger.debug("wlcAcc: " + wlcAcc);
                session.setAttribute("CPEIP", request.getRemoteAddr());
                String macCPE;
                Integer homeId;
                boolean redirect = false;
                if (wlcAcc != null) {
                    macCPE = wlcAcc.getMaccpe();
                    session.setAttribute("APID", wlcAcc.getIdAP().getIdAP());
                    homeId = wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes();
                } else {
                    //Este caso se da cuando se prueba en desarrollo, osea no hay wlcAccounting
                    macCPE = CPE_NA;
                    session.setAttribute("APID", AP_NA);
                    homeId = DEFAULT_HOME_ID;
                }
                session.setAttribute("CPEMAC", macCPE);
                session.setAttribute("HOMEID", homeId);
                logger.debug("macCPE: " + macCPE);
                if (macCPE != null && !macCPE.equals(CPE_NA)) {
                    homeResource = loginSessionBean.getPortalLayout(homeId, "/home", "default");
                    userPath = homeResource.getFilePath();
                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                        if (loginSessionBean.getSettingsConfiguration().getProperty("autoLoginMethod").equals("mac")) {
                            PortalcvAutologin autoLogin = loginSessionBean.validAutoLoginDataByCPEMac(macCPE);
                            if (validAutoLogin(autoLogin)) {
                                request.setAttribute("autoLogin", true);
                                String url = getUrlAutoLogin(autoLogin, homeId);
                                homeResource = loginSessionBean.getPortalLayout(homeId, "/home", url);
                                request.setAttribute("autoLoginURL", homeResource.getFilePath());
                                userPath = homeResource.getFilePath();
                                redirect = true;
                            } else {
                                request.setAttribute("autoLogin", false);
                            }
                        } else {
                            request.setAttribute("autoLogin", false);
                        }
                    } else {
                        request.setAttribute("autoLogin", false);
                    }
                } else {
                    //Este caso se da cuando se prueba en desarrollo, osea no hay wlcAccounting
                    homeResource = loginSessionBean.getPortalLayout(DEFAULT_HOME_ID, "/home", "default");
                    userPath = homeResource.getFilePath();
                    request.setAttribute("autoLogin", false);
                }
                loginSessionBean.saveInfoReportesFromGetHome(request.getRemoteAddr(), request.getHeader("user-agent"));
                //Chequeo si lo tengo que redirigir al autologin
                if (redirect) {
                    redirectTo(response, userPath);
                } else {
                    request.getRequestDispatcher(userPath).forward(request, response);
                }
                logger.debug("/home FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                loggerProfiling.debug("/home from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /home");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /home. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
        logger.debug("/home FIN.");
    }

    private void loginNOCVFacebook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                PortalcvHomeresource homeResource;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    logger.debug("/loginNOCVFacebook START.");
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCVFacebook", "internet");
                    String path = homeResource.getFilePath();
                    path = path.replace("APPID", loginSessionBean.getSettingsConfiguration().getProperty("facebookappidnocv"));
                    path = path.replace("URL", loginSessionBean.getSettingsConfiguration().getProperty("url"));
                    redirectTo(response, path);
                    userPath = path;
                    loggerProfiling.debug("/loginNOCVFacebook from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginNOCVFacebook FIN.");
                } else {
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCVFacebook", "no-internet");
                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                }
                logger.debug("/loginNOCVFacebook FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginNOCVFacebook");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginNOCVFacebook. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginNOCVGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                PortalcvHomeresource homeResource;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    logger.debug("/loginNOCVGoogle START.");
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());
                    final GoogleAuthHelper helper = new GoogleAuthHelper(loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientId"), loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientSecret"));
                    redirectTo(response, helper.buildLoginUrl());
                    loggerProfiling.debug("/loginNOCVGoogle from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginNOCVGoogle FIN.");
                } else {
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCVGoogle", "no-internet");
                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                }
                logger.debug("/loginNOCVGoogle FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginNOCVGoogle");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginNOCVGoogle. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginNOCVTwitter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                PortalcvHomeresource homeResource;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    logger.debug("/loginNOCVTwitter START.");
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());

                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true).setOAuthConsumerKey("Wy20jrZOwzLMHZ2KPDCuw").setOAuthConsumerSecret("pM3s4xNHMqTcZeHsSHlI02RV4YAu6aCyFoLiE574");
                    TwitterFactory tf = new TwitterFactory(cb.build());
                    Twitter twitter = tf.getInstance();
                    request.getSession().setAttribute("twitter", twitter);
                    try {
                        StringBuffer callbackURL = request.getRequestURL();
                        int index = callbackURL.lastIndexOf(INDEX_PAGE);
                        callbackURL.replace(index, callbackURL.length(), "").append("/login_success_twitter_nocv");

                        RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
                        request.getSession().setAttribute("requestToken", requestToken);
                        response.sendRedirect(requestToken.getAuthenticationURL());

                    } catch (TwitterException e) {
                        throw new ServletException(e);
                    }

                    loggerProfiling.debug("/loginNOCVTwitter from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginNOCVTwitter FIN.");
                } else {
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCVTwitter", "no-internet");
                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                }
                logger.debug("/loginNOCVTwitter FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginNOCVTwitter");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginNOCVTwitter. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo te muestra la pagina standard para la version 1 del portal.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void connectNOCV(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/connectNOCV START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/connectNOCV", "default");
                userPath = homeResource.getFilePath();
                request.getRequestDispatcher(userPath).forward(request, response);
                loggerProfiling.debug("/connectNOCV from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                logger.debug("/connectNOCV FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /connectNOCV");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /connectNOCV. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo te muestra la pagina premium para la version 1 del portal.
     *
     * @param request
     * @param response
     */
    private void connectCV(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/connectCV START.");
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForCV(request.getRemoteAddr());
                    PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/connectCV", "default");
                    redirectTo(response, homeResource.getFilePath() + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappclientid"));
                    loggerProfiling.debug("/connectCV from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                } else {
                    redirectTo(response, "/loginCV");
                }
                logger.debug("/connectCV FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /connectCV");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /connectCV. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void changeToPremium(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/changeToPremium START.");
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    long aux = System.currentTimeMillis();
                    loginSessionBean.logoutAccount(request.getRemoteAddr());
                    redirectTo(response, "/connectCV");
                    loggerProfiling.debug("/changeToPremium from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                } else {
                    redirectTo(response, "/loginCV");
                }
                logger.debug("/changeToPremium FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /changeToPremium");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /changeToPremium. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void autoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            logger.debug("/autoLogin START. ip: " + request.getRemoteAddr());
            PortalcvHomeresource homeResource = null;
            Integer homeId = null;
            if (session != null) {
                homeId = (Integer) session.getAttribute("HOMEID");
                logger.debug("/autoLogin ip: " + request.getRemoteAddr() + ". homeId: " + homeId + ".");
                if (homeId != null) {
                    AutoLoginResult result = loginSessionBean.autoLoginByCPEIP(request.getRemoteAddr());
                    logger.debug("/autoLogin ip: " + request.getRemoteAddr() + ". result: " + result + ".");
                    switch (result) {
                        case CLIENTE:
                        case VALID_NO_CLIENTE:
                            Object saveUrlOriginal = session.getAttribute("saveUrlOriginalNoCliente");
                            if (saveUrlOriginal == null) {
                                request.setAttribute("saveUrlOriginal", true);
                            } else {
                                session.removeAttribute("saveUrlOriginalNoCliente");
                            }
                            session.setAttribute("autoLogin", true);
                            session.setAttribute("autoLoginResult", result);
                            homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "default");
                            break;
                        case INVALID_NO_CLIENTE:
                            homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "video");
                            break;
                        case OTHER:
                            homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                            break;
                    }
                } else {
                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                }
            } else {
                //session
                logger.debug("/autoLogin START2.");
                PortalcvWlcaccounting wlcAcc = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
                if (wlcAcc != null) {
                    homeId = wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes();
                    if (homeId != null) {
                        PortalcvAutologin autoLogin = loginSessionBean.validAutoLoginDataByCPEMac(wlcAcc.getMaccpe());
                        if (autoLogin != null) {
                            AutoLoginResult result = loginSessionBean.autoLoginByCPEIP(request.getRemoteAddr());
                            switch (result) {
                                case CLIENTE:
                                case VALID_NO_CLIENTE:
                                    logger.debug("/autoLogin session null pero valid autologin. Regenero session.");
                                    session = request.getSession(true);
                                    Object saveUrlOriginal = session.getAttribute("saveUrlOriginalNoCliente");
                                    if (saveUrlOriginal == null) {
                                        request.setAttribute("saveUrlOriginal", true);
                                    } else {
                                        session.removeAttribute("saveUrlOriginalNoCliente");
                                    }
                                    session.setAttribute("autoLogin", true);
                                    session.setAttribute("autoLoginResult", result);
                                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "default");
                                    break;
                                case INVALID_NO_CLIENTE:
                                case OTHER:
                                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                                    break;
                            }
                        } else {
                            homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                        }
                    } else {
                        homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                    }
                } else {
                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                }
            }
            request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
            logger.debug("/autoLogin FIN. userPath: " + homeResource.getFilePath() + ". sessionId: " + (session != null ? session.getId() : "NULLONI") + ". homeId: " + homeId);
        } catch (Exception ex) {
            loggerError.error("Error en /autoLogin. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void autoLoginNOCV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/autoLoginNOCV START.");
                PortalcvHomeresource homeResource;
                Integer homeId = 1;
                if (session != null) {
                    homeId = (Integer) session.getAttribute("HOMEID");
                    if (homeId != null) {
                        request.setAttribute("saveUrlOriginalNoCliente", true);
                        session.setAttribute("saveUrlOriginalNoCliente", true);
                        homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "default");
                    } else {
                        homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "other");
                    }
                } else {
                    logger.debug("/autoLoginNOCV START2.");
                    PortalcvWlcaccounting wlcAcc = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
                    if (wlcAcc != null) {
                        PortalcvAutologin autoLogin = loginSessionBean.validAutoLoginDataByCPEMac(wlcAcc.getMaccpe());
                        if (autoLogin != null) {
                            if (autoLogin.getLogintype().contains("no cliente")) {
                                homeId = wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes();
                                if (homeId != null) {
                                    request.setAttribute("saveUrlOriginalNoCliente", true);
                                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "default");
                                } else {
                                    homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "other");
                                }
                            } else {
                                homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "other");
                            }
                        } else {
                            homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLoginNOCV", "other");
                        }
                    } else {
                        homeResource = loginSessionBean.getPortalLayout(homeId, "/autoLogin", "other");
                    }
                }
                request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                logger.debug("/autoLoginNOCV FIN. sessionId: " + (session != null ? session.getId() : "NULLONI") + ". homeId: " + homeId);
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /autoLoginNOCV");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /autoLoginNOCV. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void redirectTo(HttpServletResponse response, String url) {
        try {
            response.sendRedirect(url);
        } catch (Exception ex) {
            loggerError.error("redirectTo. url: " + url, ex);
        }
    }

    private void redirectToIndex(HttpServletResponse response) {
        try {
            response.sendRedirect(INDEX_PAGE);
        } catch (Exception ex) {
            loggerError.error("redirectToIndex.", ex);
        }
    }

    public boolean validateEmail(String hex) {
        if (hex != null && !hex.isEmpty()) {
            Matcher matcher = pattern.matcher(hex);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * Este metodo valida y manda email de confirmacion de cuenta standard para
     * la version 1 del portal1.
     *
     * @param request
     * @param response
     */
    private void registernocv(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/registernocv START.");
                long aux = System.currentTimeMillis();
                loggerProfiling.debug("/registernocv from " + request.getRemoteAddr() + " start");
                String mail = request.getParameter("usremail");
                PortalcvHomeresource homeResource;
                try {
                    if (validateEmail(mail)) {
                        long aux1 = System.currentTimeMillis();
                        if (loginSessionBean.registerUserNOCV(mail)) {
                            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/registernocv", "valid-internet");
                                userPath = homeResource.getFilePath();
                            } else {
                                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/registernocv", "valid-no-internet");
                                userPath = homeResource.getFilePath();
                            }
                            request.getRequestDispatcher(userPath).forward(request, response);
                            //Hago un logon que dura 20 min asi puede revisar el mail
                            long aux2 = System.currentTimeMillis();
                            loggerProfiling.debug("/registernocv from " + request.getRemoteAddr() + " etapa 1 " + (aux2 - aux1) + " ms.");
                            loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());
                            loggerProfiling.debug("/registernocv from " + request.getRemoteAddr() + " etapa 2 " + (System.currentTimeMillis() - aux1) + " ms.");
                        } else {
                            homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/registernocv", "invalid");
                            userPath = homeResource.getFilePath();
                            request.setAttribute("invalidemail", true);
                            request.getRequestDispatcher(userPath).forward(request, response);
                        }
                        loggerProfiling.debug("/registernocv from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                        logger.debug("/registernocv FIN.");
                        return;
                    }
                } catch (Exception ex) {
                    loggerError.error("Error registernocv.", ex);
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/registernocv", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error registernocv2.", ex);
                }
                logger.debug("/registernocv FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /registernocv");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /registernocv. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginCV(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/loginCV START.");
                long aux = System.currentTimeMillis();
                loggerProfiling.debug("/loginCV from " + request.getRemoteAddr() + " start");
                String userid = null;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    userid = request.getParameter("UserId");
                } else {
                    userid = "userCliente@cablevision.com.ar";
                }
                PortalcvHomeresource homeResource;
                if (validateEmail(userid)) {
                    String code = request.getParameter("Code");
                    logger.info("LoginCV: userid: " + userid + ". code: " + code);
                    //Aca deberia ir al ws pero por ahora lo mandamos directo
                    long aux1 = System.currentTimeMillis();
                    loginSessionBean.logoutTemporaryAccountForCV(request.getRemoteAddr());
                    long aux2 = System.currentTimeMillis();
                    loggerProfiling.debug("/loginCV from " + request.getRemoteAddr() + " etapa 1 " + (aux2 - aux1) + " ms.");
                    String resultPlan = "NO PLAN";
                    loggerProfiling.debug("/loginCV from " + request.getRemoteAddr() + " etapa 2 " + (System.currentTimeMillis() - aux2) + " ms.");
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginCV", "valid");
                    userPath = homeResource.getFilePath();
                    session.setAttribute("userid", userid);
                    session.setAttribute("code", code);
                    session.setAttribute("resultplan", resultPlan);
                    redirectTo(response, userPath);
                    logger.info("Redirecting...");
                    loggerProfiling.debug("/loginCV from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginCV FIN. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                    return;
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginCV", "other");
                userPath = homeResource.getFilePath();
                logger.debug("Invalid email " + userid + " redirecting to " + userPath);
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error loginCV.", ex);
                }
                logger.debug("/loginCV FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginCV");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginCV. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginCV2(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/loginCV2 START.");
                PortalcvHomeresource homeResource;
                try {
                    long aux = System.currentTimeMillis();
                    if (!Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                        session.setAttribute("code", "nointernetmode");
                    }
                    if (session.getAttribute("code") != null) {
                        String code = session.getAttribute("code").toString();
                        String username = session.getAttribute("userid").toString();
                        if (validateEmail(username)) {
                            String resultplan = session.getAttribute("resultplan").toString();
                            //Por ahora cargo la info como un no cv pero el plan se deberia obtener de otra forma
                            loginSessionBean.loadInfoForRadiusUser(username, resultplan);
                            String logintype = "cliente";
                            loginSessionBean.saveInfoUserData(username, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                                loginSessionBean.getsACVPorLaInfo(code, username);
                            }
                            LoginResult result = loginSessionBean.loginCVAccount(username, request.getRemoteAddr());
                            switch (result) {
                                case SUCCESSFULL:
                                    loginSessionBean.saveLoginData(username, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginCV2", "valid");
                                    userPath = homeResource.getFilePath();

                                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                        if (loginSessionBean.getSettingsConfiguration().getProperty("autoLoginMethod").equals("mac")) {
                                            loginSessionBean.createAutoLoginInfo(username, logintype, request.getRemoteAddr());
                                        } else if (loginSessionBean.getSettingsConfiguration().getProperty("autoLoginMethod").equals("cookie")) {
                                            String authCookie = loginSessionBean.generateHashCodeForMail();
                                            loginSessionBean.createClientLoginAuth(username, authCookie);
                                            request.setAttribute("saveCookieLogged", true);
                                            request.setAttribute("authCookie", authCookie);
                                        }
                                    }
                                    request.getRequestDispatcher(userPath).forward(request, response);
                                    loggerProfiling.debug("/loginCV2 from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                    logger.debug("/loginCV2 FIN. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                                    return;
                            }
                        }
                    } else {
                        logger.debug("/loginCV2 invalid session.");
                    }
                } catch (Exception ex) {
                    logger.error("error en /loginCV2.", ex);
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginCV2", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error loginCV2.", ex);
                }
                logger.debug("/loginCV2 FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginCV2");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginCV2. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo valida la confirmacion del email de la cuenta standard para
     * la version 1 del portal
     *
     * @param request
     * @param response
     */
    private void confirmation(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/confirmation START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                try {
                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                        String hash = request.getParameter("hash");
                        if (hash != null) {
                            ActivateUserResult re = loginSessionBean.activateUserNOCV(hash);
                            if (re.isResult() && validateEmail(re.getEmail())) {
                                request.setAttribute("actived", true);
                                loginSessionBean.logoutTemporaryAccountForNOCV(request.getRemoteAddr());
                                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/confirmation", "valid-internet");
                                userPath = homeResource.getFilePath();
                                session.setAttribute("username", re.getEmail());
                                session.setAttribute("type", "email");
                                redirectTo(response, userPath);
                                loggerProfiling.debug("/confirmation from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                logger.debug("/confirmation FIN1. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                                return;
                            } else {
                                loggerProfiling.debug("/confirmation from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                logger.debug("/confirmation FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                            }
                        } else {
                            loggerProfiling.debug("/confirmation from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                            logger.debug("/confirmation FIN3. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                        }
                    } else {
                        request.setAttribute("actived", true);
                        loginSessionBean.logoutTemporaryAccountForNOCV(request.getRemoteAddr());
                        homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/confirmation", "valid-no-internet");
                        userPath = homeResource.getFilePath();
                        session.setAttribute("username", "userMail@cablevision.com.ar");
                        session.setAttribute("type", "email");
                        redirectTo(response, userPath);
                        loggerProfiling.debug("/confirmation from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                        logger.debug("/confirmation FIN4. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                        return;
                    }
                } catch (Exception ex) {
                    loggerError.error("Error confirmation.", ex);
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/confirmation", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error confirmation2.", ex);
                }
                logger.debug("/confirmation FIN5. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /confirmation");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /confirmation. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo llega cuand se valida un correo o se valida via facebook.
     * Valido para el portal version 1.
     *
     * @param request
     * @param response
     */
    private void loginNOCV(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/loginNOCV START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                try {
                    if (session.getAttribute("username") != null) {
                        String username = session.getAttribute("username").toString();
                        if (validateEmail(username)) {
                            String tipo = session.getAttribute("type").toString();
                            LoginResult result;
                            loginSessionBean.loadInfoForRadiusUser(username, null);
                            String loginType;
                            if (tipo.equals("email")) {
                                loginType = "no cliente email";
                                result = loginSessionBean.loginNOCVAccount(username, request.getRemoteAddr());
                            } else {
                                loginType = "no cliente " + tipo;
                                result = loginSessionBean.loginNOCVAccountViaFacebook(username, request.getRemoteAddr());
                            }
                            loginSessionBean.saveInfoUserData(username, request.getRemoteAddr(), request.getHeader("user-agent"), loginType);
                            switch (result) {
                                case SUCCESSFULL:
                                    loginSessionBean.saveLoginData(username, request.getRemoteAddr(), request.getHeader("user-agent"), loginType);
                                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCV", "valid");
                                    userPath = homeResource.getFilePath();

                                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                        if (loginSessionBean.getSettingsConfiguration().getProperty("autoLoginMethod").equals("mac")) {
                                            if (loginSessionBean.isClientDueToIsInDBFibertel(username)) {
                                                loginSessionBean.createAutoLoginInfo(username, "cliente", request.getRemoteAddr());
                                            } else {
                                                loginSessionBean.createAutoLoginInfo(username, loginType, request.getRemoteAddr());
                                            }
                                        } else if (loginSessionBean.getSettingsConfiguration().getProperty("autoLoginMethod").equals("cookie")) {
                                            String authCookie = loginSessionBean.generateHashCodeForMail();
                                            loginSessionBean.createClientLoginAuth(username, authCookie);
                                            request.setAttribute("saveCookieLogged", true);
                                            request.setAttribute("NOCVauthCookie", authCookie);
                                        }
                                    }
                                    request.getRequestDispatcher(userPath).forward(request, response);
                                    loggerProfiling.debug("/loginNOCV from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                    logger.debug("/loginNOCV FIN. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                                    return;
                            }
                        }
                    }
                } catch (Exception ex) {
                    loggerError.error("Error loginNOCV.", ex);
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginNOCV", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error loginNOCV.", ex);
                }
                logger.debug("/loginNOCV FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginNOCV");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginNOCV. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo funciona para los 2 portales
     *
     * @param request
     * @param response
     */
    private void callbackFacebook(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/callbackFacebook START.");
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/callbackFacebook", "default");
                request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                logger.debug("/callbackFacebook FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /callbackFacebook");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /callbackFacebook. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    /**
     * Este metodo funciona para los 2 portales
     *
     * @param request
     * @param response
     */
    private void login_success_facebook_nocv(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/login_success_facebook_nocv START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                String email;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    email = request.getParameter("email");
                    loginSessionBean.saveFacebookData(request.getParameterMap());
                } else {
                    email = "userFacebook@cablevision.com.ar";
                }
                loginSessionBean.logoutTemporaryAccountForNOCV(request.getRemoteAddr());
                logger.info("Login no CV via Facebook. Email: " + email + ".... \n");
                if (validateEmail(email)) {
                    loginSessionBean.createUserNOCV(email);
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_facebook_nocv", "valid");
                    userPath = homeResource.getFilePath();
                    session.setAttribute("username", email);
                    session.setAttribute("type", "facebook");
                    redirectTo(response, userPath);
                    loggerProfiling.debug("/login_success_facebook_nocv from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/login_success_facebook_nocv FIN.");
                    return;
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_facebook_nocv", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error login_success_facebook_nocv.", ex);
                }
                logger.debug("/login_success_facebook_nocv FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /login_success_facebook_nocv");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /login_success_facebook_nocv. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void login_success_google_nocv(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/login_success_google_nocv START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                String email = "userGoogle@cablevision.com.ar";
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    try {
                        GoogleAuthHelper helper = new GoogleAuthHelper(loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientId"), loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientSecret"));
                        Map<String, String> datos = helper.getInfoJson(request.getParameter("code"));
                        if (datos.containsKey("email")) {
                            email = datos.get("email");
                        }
                        loginSessionBean.saveGoogleData(datos);
                    } catch (Exception ex) {
                        loggerError.error("Error login_success_facebook_nocv parte google get email.", ex);
                    }
                }
                loginSessionBean.logoutTemporaryAccountForNOCV(request.getRemoteAddr());
                logger.info("Login no CV via Google. Email: " + email + ".... \n");
                if (validateEmail(email)) {
                    loginSessionBean.createUserNOCV(email);
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_google_nocv", "valid");
                    userPath = homeResource.getFilePath();
                    session.setAttribute("username", email);
                    session.setAttribute("type", "google");
                    redirectTo(response, userPath);
                    loggerProfiling.debug("/login_success_google_nocv from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/login_success_google_nocv FIN.");
                    return;
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_facebook_nocv", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error login_success_facebook_nocv.", ex);
                }
                logger.debug("/login_success_google_nocv FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /login_success_google_nocv");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /login_success_google_nocv. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void login_success_twitter_nocv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TwitterException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/login_success_twitter_nocv START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                String email = null;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
                    RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
                    String verifier = request.getParameter("oauth_verifier");
                    try {
                        twitter.getOAuthAccessToken(requestToken, verifier);
                        request.getSession().removeAttribute("requestToken");
                    } catch (TwitterException e) {
                        throw new ServletException(e);
                    }
                    email = twitter.getId() + "@twitterlogin.com";
                    User userTwitter = twitter.verifyCredentials();
                    System.out.println("userTwitter: " + userTwitter);
                    loginSessionBean.saveTwitterData(userTwitter);
                } else {
                    email = "userTwitter@cablevision.com.ar";
                }
                loginSessionBean.logoutTemporaryAccountForNOCV(request.getRemoteAddr());
                logger.info("Login no CV via Twitter. Email: " + email + ".... \n");
                if (validateEmail(email)) {
                    loginSessionBean.createUserNOCV(email);
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_twitter_nocv", "valid");
                    userPath = homeResource.getFilePath();
                    session.setAttribute("username", email);
                    session.setAttribute("type", "twitter");
                    redirectTo(response, userPath);
                    loggerProfiling.debug("/login_success_twitter_nocv from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/login_success_twitter_nocv FIN.");
                    return;
                }
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/login_success_twitter_nocv", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error login_success_twitter_nocv.", ex);
                }
                logger.debug("/login_success_twitter_nocv FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /login_success_twitter_nocv");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /login_success_twitter_nocv. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void condiciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/condiciones START.");
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/condiciones", "default");
                request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                logger.debug("/condiciones FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /condiciones");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /condiciones. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
//            if (session != null) {
            logger.debug("/logout START. request.getRemoteAddr()");
            loginSessionBean.logoutAccount(request.getRemoteAddr());
            request.setAttribute("authNOCVInvalid", true);
            request.setAttribute("authInvalid", true);
            try {
//                    PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/logoff", "default");
//                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                String url = "/WEB-INF/view/default/logoutSuccess.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                loggerError.error("Error logout.", ex);
            }

            if (session != null) {
                logger.debug("/logout FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                session.invalidate();
            } else {
                logger.debug("/logout FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". SessionId: null");
            }
//            } else {
            //Aca no deberia entrar papa, andate pal index
//                logger.error("Session null. /logout");
//                redirectToIndex(response);
//            }
        } catch (Exception ex) {
            loggerError.error("Error en /logout. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void signout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
//            if (session != null) {
            logger.debug("/signout START. cpeIP: " + request.getRemoteAddr() + ".");
            LoginResult result = loginSessionBean.signout(request.getRemoteAddr());
            logger.debug("/signout result " + result + ". cpeIP: " + request.getRemoteAddr() + ".");
            request.setAttribute("authNOCVInvalid", true);
            request.setAttribute("authInvalid", true);
            try {
//                    PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/logoff", "default");
//                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                String url = "/WEB-INF/view/default/signoutSuccess.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                loggerError.error("Error en /signout. cpeIP: " + request.getRemoteAddr() + ".", ex);
            }
            if (session != null) {
                logger.debug("/signout FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                session.invalidate();
            } else {
                logger.debug("/signout FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". SessionId: null");
            }

//            } else {
            //Aca no deberia entrar papa, andate pal index
//                logger.error("Session null. /signout  cpeIP: " + request.getRemoteAddr() + ".");
//                redirectToIndex(response);
//            }
        } catch (Exception ex) {
            loggerError.error("Error en /signout. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void terminosP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/terminosP START.");
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/terminosP", "default");
                request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                logger.debug("/terminosP FIN.");
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /terminosP");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /terminosP. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void terminosS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/terminosS START.");
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/terminosS", "default");
                request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                logger.debug("/terminosS FIN.");
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /terminosS");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /terminosS. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void validateUserAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/validateUserAuth START.");
                String userAuth = request.getParameter("userAuth");
                logger.debug("userAuth: " + userAuth);
                if (userAuth != null) {
                    //Validar si tengo que loguearlo automaticamente
                    ClientLogin clientLogin = loginSessionBean.getClientLoginByKey(userAuth);
                    logger.debug("clientLogin: " + clientLogin);
                    if (validClientLogin(clientLogin)) {
                        logger.debug("validClientLogin: si");
                        String logintype = loginSessionBean.getLoginTypeByFibertelEmails(clientLogin.getEmail());
                        loginSessionBean.createAutoLoginInfo(clientLogin.getEmail(), logintype, request.getRemoteAddr());
                        if (logintype.equals("cliente")) {
                            autoLogin(request, response);
                        } else if (logintype.equals("no cliente")) {
                            autoLoginNOCV(request, response);
                        }
                    } else {
                        logger.debug("validClientLogin: no");
                        request.setAttribute("authInvalid", true);
                        PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/validateUserAuth", "other");
                        userPath = homeResource.getFilePath();
                        request.getRequestDispatcher(userPath).forward(request, response);
                    }
                }
                logger.debug("/validateUserAuth FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /validateUserAuth");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /validateUserAuth. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private boolean validClientLogin(ClientLogin clientLogin) {
        if (clientLogin != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(clientLogin.getDateLastLogin());
            long aux = cal.getTimeInMillis();
            //30*24*60*1000
            logger.debug("aux: " + aux + ".");
            logger.debug("(cal.getTimeInMillis() + (30 * 24 * 60 * 60 * 1000)): " + (aux + (30 * 24 * 60 * 60 * 1000L)) + ". Calendar.getInstance().getTimeInMillis(): " + Calendar.getInstance().getTimeInMillis());
            if ((aux + (30 * 24 * 60 * 60 * 1000L)) > Calendar.getInstance().getTimeInMillis()) {
                return true;
            }
        }
        return false;
    }

    private void macap(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/macap START. request.getRemoteAddr()");
                PortalcvWlcaccounting wlcAcctInfo = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
                request.setAttribute("clientIP", request.getRemoteAddr());
                if (wlcAcctInfo != null) {
                    request.setAttribute("clientMac", wlcAcctInfo.getMaccpe());
                    request.setAttribute("apMac", wlcAcctInfo.getIdAP().getMac());
                    request.setAttribute("apName", wlcAcctInfo.getIdAP().getName());
                }
                request.getRequestDispatcher("/macap.jsp").forward(request, response);
                logger.debug("/macap FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /macap");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /macap. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void fastLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/fastLogin START. request.getRemoteAddr()");
                String email = "fastLogin@fzone.com.ar";
                loginSessionBean.loadInfoForRadiusUser(email, null);
                PortalcvWlcaccounting wlcAcc = loginSessionBean.getWLCInfoByCPEIP(request.getRemoteAddr());
                LoginResult result = loginSessionBean.fastLogin(email, request.getRemoteAddr());
                PortalcvHomeresource homeResource;
                switch (result) {
                    case SUCCESSFULL:
                        loginSessionBean.saveLoginData(email, request.getRemoteAddr(), request.getHeader("user-agent"), "fastLogin");
                        homeResource = loginSessionBean.getPortalLayout(wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes(), "/fastLogin", "valid");
                        userPath = homeResource.getFilePath();
                        request.getRequestDispatcher(userPath).forward(request, response);
                        logger.debug("/fastLogin FIN. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
                        return;
                }
                homeResource = loginSessionBean.getPortalLayout(wlcAcc.getIdAP().getIdapgroups().getIdhomes().getIdhomes(), "/fastLogin", "other");
                userPath = homeResource.getFilePath();
                request.getRequestDispatcher(userPath).forward(request, response);
                logger.debug("/fastLogin FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /fastLogin");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /fastLogin. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void premium(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/premium START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/premium", "default");
                userPath = homeResource.getFilePath();
                request.getRequestDispatcher(userPath).forward(request, response);
                session.setAttribute("ConexionType", ConexionType.PREMIUM);
                loggerProfiling.debug("/premium from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                logger.debug("/premium FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /premium");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            logger.debug("/macap FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
        }
    }

    private void standard(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/standard START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/standard", "default");
                userPath = homeResource.getFilePath();
                request.getRequestDispatcher(userPath).forward(request, response);
                session.setAttribute("ConexionType", ConexionType.STANDARD);
                loggerProfiling.debug("/standard from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                logger.debug("/standard FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /standard");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            logger.debug("/macap FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
        }
    }

    private void loginFibertel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/loginFibertel START.");
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForCV(request.getRemoteAddr());
                    PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginFibertel", "default");
//                    redirectTo(response, homeResource.getFilePath() + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappclientid"));
                    userPath = homeResource.getFilePath();
                    request.getRequestDispatcher(userPath).forward(request, response);
                    session.setAttribute("LoginType", LoginType.MUNDO_CLIENTE);
                    loggerProfiling.debug("/loginFibertel from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                } else {
                    redirectTo(response, "/loginCV");
                }
                logger.debug("/loginFibertel FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginFibertel");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginFibertel. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void validateFibertel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/validateFibertel START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource;
                String email, password;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    email = request.getParameter("usuario");
                    password = request.getParameter("password");
                } else {
                    email = "userFibertel@cablevision.com.ar";
                    password = "password";
                }
                logger.info("Login CV via Mundo Cliente. Email: " + email + " Password: " + password + ".... \n");
                if (validateEmailAndPassword(email, password) && loginSessionBean.validateMundoCliente(email, password)) {
                    loginSessionBean.logoutTemporaryAccountForCV(request.getRemoteAddr());
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/validateFibertel", "valid");
                    userPath = homeResource.getFilePath();
                    session.setAttribute("username", email);
                    session.setAttribute("type", "facebook");
//                    request.getRequestDispatcher(userPath).forward(request, response);
                    redirectTo(response, userPath);
                    loggerProfiling.debug("/validateFibertel from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/validateFibertel FIN.");
                    return;
                }
                request.setAttribute("invalidUsername", true);
                homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/validateFibertel", "other");
                userPath = homeResource.getFilePath();
                try {
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    loggerError.error("Error validateFibertel.", ex);
                }
                logger.debug("/validateFibertel FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /validateFibertel");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /validateFibertel. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginFacebook(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                PortalcvHomeresource homeResource;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    logger.debug("/loginFacebook START.");
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginFacebook", "default");
                    String path = homeResource.getFilePath();
                    path = path.replace("APPID", loginSessionBean.getSettingsConfiguration().getProperty("facebookappidnocv"));
                    path = path.replace("URL", loginSessionBean.getSettingsConfiguration().getProperty("url"));
                    redirectTo(response, path);
                    session.setAttribute("LoginType", LoginType.FACEBOOK);
                    loggerProfiling.debug("/loginNOCVFacebook from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginFacebook FIN.");
                } else {
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginFacebook", "other");
                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                }
                logger.debug("/loginFacebook FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginFacebook");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginFacebook. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginGoogle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                PortalcvHomeresource homeResource;
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableinternet"))) {
                    logger.debug("/loginGoogle START.");
                    long aux = System.currentTimeMillis();
                    loginSessionBean.loginTemporaryAccountForNOCV(request.getRemoteAddr());
                    final GoogleAuthHelper helper = new GoogleAuthHelper(loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientId"), loginSessionBean.getSettingsConfiguration().getProperty("GoogleClientSecret"));
                    redirectTo(response, helper.buildLoginUrl());
                    session.setAttribute("LoginType", LoginType.GOOGLE);
                    loggerProfiling.debug("/loginGoogle from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                    logger.debug("/loginGoogle FIN.");
                } else {
                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginGoogle", "other");
                    request.getRequestDispatcher(homeResource.getFilePath()).forward(request, response);
                }
                logger.debug("/loginGoogle FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginGoogle");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginGoogle. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void loginVideo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/loginVideo START.");
                long aux = System.currentTimeMillis();
                PortalcvHomeresource homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/loginVideo", "default");
                userPath = homeResource.getFilePath();
                request.getRequestDispatcher(userPath).forward(request, response);
                session.setAttribute("LoginType", LoginType.VIDEO);
                session.setAttribute("username", "video@fzone.com.ar");
                loggerProfiling.debug("/loginVideo from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                logger.debug("/loginVideo FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /loginVideo");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            loggerError.error("Error en /loginVideo. cpeIP: " + request.getRemoteAddr() + ".", ex);
        }
    }

    private void welcome(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                logger.debug("/welcome START. cpeIP: " + request.getRemoteAddr() + ".");
                PortalcvHomeresource homeResource = null;
                AutoLoginResult resultAutoLogin = null;
                long aux = System.currentTimeMillis();
                if (session.getAttribute("autoLogin") != null && ((Boolean) session.getAttribute("autoLogin"))) {
                    logger.debug("/welcome autoLogin. cpeIP: " + request.getRemoteAddr() + ".");
                    //autoLogin
                    int homeId = 4;
                    try {
                        resultAutoLogin = (AutoLoginResult) session.getAttribute("autoLoginResult");
                        homeId = (Integer) session.getAttribute("HOMEID");
                        switch (resultAutoLogin) {
                            case CLIENTE:
                                homeResource = loginSessionBean.getPortalLayout(homeId, "/welcome", "premium");
                                break;
                            default:
                                homeResource = loginSessionBean.getPortalLayout(homeId, "/welcome", "standard");
                                break;
                        }
                        userPath = homeResource.getFilePath();
                        request.getRequestDispatcher(userPath).forward(request, response);
                        logger.debug("/welcome FIN31. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
//                        logger.debug("/welcome invalidando");
//                        session.invalidate();
                        return;
                    } catch (Exception ex) {
                        loggerError.error("Error welcome autoLogin. homeResource: " + homeResource + ". resultAutoLogin: " + resultAutoLogin + ". homeId: " + homeId + ". cpeIP: " + request.getRemoteAddr() + ".", ex);
                    }
                } else {
                    logger.debug("/welcome comun. cpeIP: " + request.getRemoteAddr() + ".");
                    if (session.getAttribute("username") != null) {
                        ConexionType conexionType = (ConexionType) session.getAttribute("ConexionType");
                        LoginType loginType = (LoginType) session.getAttribute("LoginType");
                        String email = session.getAttribute("username").toString();
                        if (validData(email, conexionType, loginType)) {
                            loginSessionBean.loadInfoForRadiusUser(email, null);
                            boolean isClient = loginSessionBean.isClientDueToIsInDBFibertel(email);
                            String logintype = generateLoginType(conexionType, loginType, isClient);
                            loginSessionBean.saveInfoUserData(email, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("AutoLoginLimitedEnabled"))) {
                                if (normalLogin(logintype)) {
                                    LoginResult result = loginSessionBean.loginCVAccount(email, request.getRemoteAddr());
                                    switch (result) {
                                        case SUCCESSFULL:
                                            loginSessionBean.saveLoginData(email, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                                            homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/welcome", getLayoutType(conexionType, loginType, isClient));
                                            userPath = homeResource.getFilePath();
                                            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                                loginSessionBean.createAutoLoginInfo(email, logintype, loginType, request.getRemoteAddr());
                                            }
                                            request.getRequestDispatcher(userPath).forward(request, response);
                                            loggerProfiling.debug("/welcome from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                            logger.debug("/welcome FIN3. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
//                                        logger.debug("/welcome invalidando");
//                                        session.invalidate();
                                            return;
                                    }
                                } else {
                                    LoginResult result;
                                    RadiusCoaResult sessionValid = loginSessionBean.sessionValid(email, request.getRemoteAddr());
                                    logger.debug("/welcome video sessionValid: " + sessionValid + ". cpeIP: " + request.getRemoteAddr() + ".");
                                    switch (sessionValid) {
                                        case FZONE_INTERNET_FULL:
                                            logger.warn("/welcome aca no deberia llegar. Ya tenia internet full. cpeIP: " + request.getRemoteAddr() + ".");
                                            break;
                                        case FZONE_INTERNET_LIMITED:
                                            logger.debug("/welcome le pongo solo 80. cpeIP: " + request.getRemoteAddr() + ".");
                                            result = loginSessionBean.logoutBlockPort80Service(email, request.getRemoteAddr());
                                            switch (result) {
                                                case SUCCESSFULL:
                                                    loginSessionBean.saveLoginData(email, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                                                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/welcome", getLayoutType(conexionType, loginType, isClient));
                                                    userPath = homeResource.getFilePath();
                                                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                                        loginSessionBean.createAutoLoginInfo(email, logintype, loginType, request.getRemoteAddr());
                                                    }
                                                    request.getRequestDispatcher(userPath).forward(request, response);
                                                    loggerProfiling.debug("/welcome from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                                    logger.debug("/welcome FIN4. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
//                                        logger.debug("/welcome invalidando");
//                                        session.invalidate();
                                                    return;
                                            }
                                            break;
                                        case FZONE_REDIRECT:
                                            logger.debug("/welcome le pongo todo. cpeIP: " + request.getRemoteAddr() + ".");
                                            result = loginSessionBean.loginCVVideoAccount(email, request.getRemoteAddr());
                                            switch (result) {
                                                case SUCCESSFULL:
                                                    loginSessionBean.saveLoginData(email, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                                                    homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/welcome", getLayoutType(conexionType, loginType, isClient));
                                                    userPath = homeResource.getFilePath();
                                                    if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                                        loginSessionBean.createAutoLoginInfo(email, logintype, loginType, request.getRemoteAddr());
                                                    }
                                                    request.getRequestDispatcher(userPath).forward(request, response);
                                                    loggerProfiling.debug("/welcome from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                                    logger.debug("/welcome FIN5. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
//                                        logger.debug("/welcome invalidando");
//                                        session.invalidate();
                                                    return;
                                            }
                                            break;
                                        default:
                                            logger.warn("/welcome aca no deberia llegar default. cpeIP: " + request.getRemoteAddr() + ".");
                                            break;
                                    }
                                }
                            } else {
                                LoginResult result = loginSessionBean.loginCVAccount(email, request.getRemoteAddr());
                                switch (result) {
                                    case SUCCESSFULL:
                                        loginSessionBean.saveLoginData(email, request.getRemoteAddr(), request.getHeader("user-agent"), logintype);
                                        homeResource = loginSessionBean.getPortalLayout((Integer) session.getAttribute("HOMEID"), "/welcome", getLayoutType(conexionType, loginType, isClient));
                                        userPath = homeResource.getFilePath();
                                        if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("enableautologin"))) {
                                            loginSessionBean.createAutoLoginInfo(email, logintype, loginType, request.getRemoteAddr());
                                        }
                                        request.getRequestDispatcher(userPath).forward(request, response);
                                        loggerProfiling.debug("/welcome from " + request.getRemoteAddr() + " costs " + (System.currentTimeMillis() - aux) + " ms.");
                                        logger.debug("/welcome FIN3. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
//                                        logger.debug("/welcome invalidando");
//                                        session.invalidate();
                                        return;
                                }
                            }
                        } else {
                            logger.warn("/welcome invalidData. email: " + email + ". conexionType: " + conexionType + ". loginType: " + loginType + ". cpeIP: " + request.getRemoteAddr() + ".");
                        }
                    } else {
                        logger.warn("/welcome invalid session. cpeIP: " + request.getRemoteAddr() + ".");
                    }
                }
                int homeId = 4;
                try {
                    //llegan un par de errores y no hay homeid en la session, supongo que es nuevo portal 4
//                    homeId = (Integer) session.getAttribute("HOMEID");
                    homeResource = loginSessionBean.getPortalLayout(homeId, "/welcome", "other");
                    userPath = homeResource.getFilePath();
                    request.getRequestDispatcher(userPath).forward(request, response);
                } catch (Exception ex) {
                    //Aca no deberia entrar papa, andate pal index
                    loggerError.error("Error welcome no deberia llegar aca papa. homeResource: " + homeResource + ". homeId: " + homeId + ". cpeIP: " + request.getRemoteAddr() + ".", ex);
                    redirectToIndex(response);
                }
                logger.debug("/welcome FIN2. userPath: " + userPath + ". cpeIP: " + request.getRemoteAddr() + ". HOME ID: " + (Integer) session.getAttribute("HOMEID") + ". SessionId: " + session.getId());
            } else {
                //Aca no deberia entrar papa, andate pal index
                logger.error("Session null. /welcome. cpeIP: " + request.getRemoteAddr() + ".");
                redirectToIndex(response);
            }
        } catch (Exception ex) {
            //Aca no deberia entrar papa, andate pal index
            loggerError.error("Error en /welcome. cpeIP: " + request.getRemoteAddr() + ".", ex);
            redirectToIndex(response);
        }
    }

    private String generateLoginType(ConexionType conexionType, LoginType loginType, boolean client) {
        StringBuilder sb = new StringBuilder();
        if (conexionType != null) {
            sb.append(conexionType.toString()).append(" ");
        }
        sb.append(loginType.toString());
        if (client) {
            sb.append(" CLIENTE");
        } else {
            sb.append(" NO CLIENTE");
        }
        return sb.toString();
    }

    private boolean validData(String email, ConexionType conexionType, LoginType loginType) {
        if (validateEmail(email) && conexionType != null && loginType != null) {
            return true;
        } else {
            if (loginType != null && loginType.equals(LoginType.AUTOLOGIN)) {
                return true;
            }
        }
        return false;
    }

    private String getLayoutType(ConexionType conexionType, LoginType loginType, boolean isClient) {
        if (conexionType != null) {
            switch (conexionType) {
                case PREMIUM:
                    switch (loginType) {
                        case MUNDO_CLIENTE:
                            return "premium";
                        case FACEBOOK:
                        case GOOGLE:
                            if (isClient) {
                                return "premium";
                            } else {
                                return "premium-to-standard";
                            }
                    }
                    break;
                case STANDARD:
                    switch (loginType) {
                        case FACEBOOK:
                        case GOOGLE:
                            if (isClient) {
                                return "standard-to-premium";
                            } else {
                                return "standard";
                            }
                        case VIDEO:
                            return "standard";
                    }
                    break;
            }
        } else {
            switch (loginType) {
                case AUTOLOGIN:
                    return "standard";
            }
        }
        //No matcheo con nada papa
        logger.error("No matcheo en getLayoutType. conexionType: " + conexionType + ". loginType: " + loginType + ". isClient: " + isClient + ".");
        return "standard";
    }

    private String getUrlAutoLogin(PortalcvAutologin autoLogin, Integer homeId) {
        if (homeId == 4) {
            //portal nuevo
            return "autoLogin";
        } else {
            if (autoLogin.getLogintype().contains("no cliente")) {
                return "autoLoginNOCV";
            } else {
                return "autoLogin";
            }
        }
    }

    private boolean validateEmailAndPassword(String email, String password) {
        if (validateEmail(email) && password != null && !password.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean normalLogin(String logintype) {
        if (logintype != null) {
            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("LoginAppsLimitedEnabled"))) {
                if (logintype.toLowerCase().contains("video")) {
                    return false;
                }
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean validAutoLogin(PortalcvAutologin autoLogin) {
        if (autoLogin != null) {
            if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("LoginAppsLimitedEnabled"))) {
                if (autoLogin.getLogintype().toLowerCase().contains("video")) {
                    return false;
                }
                return true;
            } else {
                return true;
            }
        }
        return false;
    }
}
