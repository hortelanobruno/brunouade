/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import radiuscoa.RadiusCoaResult;
import static radiuscoa.RadiusCoaResult.FZONE_INTERNET_FULL;
import static radiuscoa.RadiusCoaResult.FZONE_INTERNET_LIMITED;
import static radiuscoa.RadiusCoaResult.FZONE_REDIRECT;
import static radiuscoa.RadiusCoaResult.INVALID_PASSWORD;
import static radiuscoa.RadiusCoaResult.INVALID_USERNAME;
import static radiuscoa.RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD;
import static radiuscoa.RadiusCoaResult.LOGON;
import static radiuscoa.RadiusCoaResult.MEMORY_OR_INTERNAL_ERROR;
import radiuscoa.RadiusResult;
import radiuscoa.RadiusSender;
import stats.ManagerStatsClicks;
import twitter4j.User;
import util.*;
import static util.LoginResult.SUCCESSFULL;
import vo.SettingsConfiguration;

/**
 *
 * @author bruno
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LoginSessionBean {

//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/10.0.0.39_6979/webServiceSPR.wsdl")
//    private WebServiceSPR_Service service_1;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/10.0.0.39_6969/webServiceApplicationFunction.wsdl")
//    private WebServiceApplicationFunction_Service service;
    private Logger loggerReporter = Logger.getLogger("reportes");
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    private Logger loggerProfiling = Logger.getLogger("portalProfiling");
    private final String defaultPassword = "callis123";
    private final String serviceBlockPort80 = "REDIRECT_PORT80";
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    UserTransaction uc;
    @Resource
    private SessionContext context;
    @EJB
    private SettingsFacade settingsFacade;
    @EJB
    private EmailFacade emailFacade;
    @EJB
    private PlanregexFacade planregexFacade;
    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private ClientLoginFacade clientLoginFacade;
    @EJB
    private PortalcvWlcaccountingFacade portalcvWlcaccountingFacade;
    @EJB
    private PortalcvUseragentFacade portalcvUseragentFacade;
    @EJB
    private PortalcvLoginFacade portalcvLoginFacade;
    @EJB
    private PortalcvHomeFacade portalcvHomeFacade;
    @EJB
    private PortalcvAutologinFacade portalcvAutologinFacade;
    @EJB
    private PortalcvFiberteluserFacade portalcvFiberteluserFacade;
    @EJB
    private PortalcvFacebookdataFacade portalcvFacebookdataFacade;
    @EJB
    private PortalcvGoogledataFacade portalcvGoogledataFacade;
    @EJB
    private PortalcvTwitterdataFacade portalcvTwitterdataFacade;
    @EJB
    private RadacctFacade radacctFacade;
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void loginTemporaryAccountForCV(String remoteAddr) {
        loginAccount(settingsFacade.getSettingsConfiguration().getProperty("temporaryaccountcv"), defaultPassword, remoteAddr, ClientType.CLIENTE, true);
    }

    public void loginTemporaryAccountForNOCV(String remoteAddr) {
        loginAccount(settingsFacade.getSettingsConfiguration().getProperty("temporaryaccountnocv"), defaultPassword, remoteAddr, ClientType.NO_CLIENTE, true);
    }

    public void logoutTemporaryAccountForCV(String remoteIp) {
        logoutAccount(settingsFacade.getSettingsConfiguration().getProperty("temporaryaccountcv"), remoteIp, ClientType.CLIENTE, true);
    }

    public void logoutTemporaryAccountForNOCV(String remoteIp) {
        logoutAccount(settingsFacade.getSettingsConfiguration().getProperty("temporaryaccountnocv"), remoteIp, ClientType.NO_CLIENTE, true);
    }

    public boolean registerUserNOCV(String mail) {
        try {
            logger.debug("Registering new User NOCV: Mail: " + mail + ".");
            //me fijo si esta el usuario
            RegisteredUser user = (RegisteredUser) em.createNamedQuery("RegisteredUser.findByEmail").setParameter("email", mail).getSingleResult();
            //crear usuario
            user.setActivated(false);
            user.setEmail(mail);
            user.setDateCreated(Calendar.getInstance().getTime());
            em.merge(user);
            logger.debug("Updating user NOCV info.");
        } catch (Exception e) {
            RegisteredUser user = new RegisteredUser();
            user.setActivated(false);
            user.setEmail(mail);
            user.setDateCreated(Calendar.getInstance().getTime());
            em.persist(user);
            logger.debug("New user NOCV info saved.");
        }
        try {
            //crear validacion
            logger.debug("Creating validation info...");
            AccountValidation validation = new AccountValidation();
            validation.setEmail(mail);
            validation.setHashcode(generateHashCodeForMail());
            em.persist(validation);
            logger.debug("Validation info created.");
            em.flush();
            //mandar mail
            if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("enableinternet"))) {
                logger.debug("Sending email to " + mail + "...");
//            SendMail mailSender = new SendMail();
                Email email = emailFacade.getEmailInfo();
//            mailSender.postMail(settingsFacade.getSettingsConfiguration().getProperty("emailServerIP"), email.getFromMail(), mail, email.getTitle(), getMailMessageForActivation2(email.getContent(), validation));
                encolarTrabajo(new SendMail(settingsFacade.getSettingsConfiguration().getProperty("emailServerIP"), email.getFromMail(), mail, email.getTitle(), getMailMessageForActivation2(email.getContent(), validation)));
                logger.debug("Email sent.");
            }
            return true;
        } catch (Exception e) {
            context.setRollbackOnly();
            logger.debug("error registerUser.", e);
            return false;
        }
    }

    public String generateHashCodeForMail() {
        RandomString an = new RandomString(20);
        return an.nextString();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ActivateUserResult activateUserNOCV(String hash) {
        ActivateUserResult re = new ActivateUserResult();
        try {
            logger.debug("properties: " + settingsFacade.getSettingsConfiguration());
            logger.debug("Activating account for hash: " + hash + ".");
            AccountValidation result = (AccountValidation) em.createNamedQuery("AccountValidation.findByHashcode").setParameter("hashcode", hash).getSingleResult();
            em.remove(result);
            logger.debug("Activation info removed.");
            RegisteredUser user = (RegisteredUser) em.createNamedQuery("RegisteredUser.findByEmail").setParameter("email", result.getEmail()).getSingleResult();
            re.setEmail(user.getEmail());
            logger.debug("Updating info user. Email: " + user.getEmail() + ".");
            user.setActivated(true);
            user.setDateValidated(Calendar.getInstance().getTime());
            em.merge(user);
            logger.debug("Info user updated.");
            em.flush();
            re.setResult(true);
            return re;
        } catch (NoResultException e) {
            logger.warn("Invalid activation info.");
            return re;
        } catch (Exception e) {
            context.setRollbackOnly();
            loggerError.error("error activateAccount.", e);
            return re;
        }
    }

    public boolean deactivateServiceRedirectPort80(String email, String remoteIp) {
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        boolean result = false;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
            } else {
                //MODE ISG
                result = deactivateServiceISG(email, defaultPassword, remoteIp, serviceBlockPort80);
            }
        } else {
            //DEFAULT MODE ISG
            result = deactivateServiceISG(email, defaultPassword, remoteIp, serviceBlockPort80);
        }
        return result;
    }

    /**
     * Este es el metodo general que hace la logica del login ya se si trabaja
     * con el ISG o si trabaja con el CSM
     */
    private boolean loginAccount(String email, String password, String remoteIp, ClientType client, Boolean temporary) {
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        boolean result = false;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
//                result = loginCSM(email, password, remoteIp, client, temporary);
            } else {
                //MODE ISG
                result = loginISG(email, password, remoteIp, client, temporary);
            }
        } else {
            //DEFAULT MODE ISG
            result = loginISG(email, password, remoteIp, client, temporary);
        }
        return result;
    }

    private boolean loginAndActivateServiceAccount(String email, String password, String remoteIp, ClientType client, Boolean temporary, String service) {
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        boolean result = false;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
//                result = loginCSM(email, password, remoteIp, client, temporary);
            } else {
                //MODE ISG
                result = loginISG(email, password, remoteIp, client, temporary);
                if (result) {
                    activateServiceISG(email, password, remoteIp, client, temporary, service);
                }
            }
        } else {
            //DEFAULT MODE ISG
            result = loginISG(email, password, remoteIp, client, temporary);
            if (result) {
                activateServiceISG(email, password, remoteIp, client, temporary, service);
            }
        }
        return result;
    }

    private boolean loginISG(String email, String password, String remoteIp, ClientType client, Boolean temporary) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiuscoaenable"))) {
            RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
            logger.debug("Sending Radius CoA logon...");
            RadiusResult result = radius.logon(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, password, remoteIp);
            switch (result.getResultType()) {
                case LOGON:
                    ManagerStatsClicks.getInstance().incrementLoginCoASuccess();
                    logger.debug("Radius CoA Logon Successfully.");
                    return true;
                case MEMORY_OR_INTERNAL_ERROR:
                case INVALID_PASSWORD:
                case INVALID_USERNAME:
                case INVALID_USERNAME_AND_PASSWORD:
                default:
                    ManagerStatsClicks.getInstance().incrementLoginCoAFail();
                    logger.debug("Radius CoA Logon Unsuccessfully.");
                    return false;
            }
        } else {
            return true;
        }
    }

    private boolean activateServiceISG(String email, String password, String remoteIp, ClientType client, Boolean temporary, String service) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiuscoaenable"))) {
            RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
            logger.debug("Sending Radius CoA activateService... service: " + service);
            RadiusResult result = radius.activateService(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, password, remoteIp, service);
            switch (result.getResultType()) {
                case LOGON:
                    ManagerStatsClicks.getInstance().incrementLoginCoASuccess();
                    logger.debug("Radius CoA activateService Successfully.");
                    return true;
                case MEMORY_OR_INTERNAL_ERROR:
                case INVALID_PASSWORD:
                case INVALID_USERNAME:
                case INVALID_USERNAME_AND_PASSWORD:
                default:
                    ManagerStatsClicks.getInstance().incrementLoginCoAFail();
                    logger.debug("Radius CoA activateService Unsuccessfully.");
                    return false;
            }
        } else {
            return true;
        }
    }

    private RadiusCoaResult sessionValidISG(String email, String password, String remoteIp) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiuscoaenable"))) {
            //chequeo si tiene el servicio de redirect, si lo tiene return false
            RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
            logger.debug("Sending Radius CoA sessionValidISG... ");
            RadiusResult result = radius.sessionQueryRedirect2(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, remoteIp);
            return result.getResultType();
//            switch (result.getResultType()) {
//                case INVALID_SESSION:
//                    logger.debug("No tiene servicio de redireccion y esta ilimitado.");
//                    return true;
//                case VALID_SESSION:
//                    logger.debug("Tiene servicio de redireccion.");
//                    return false;
//                case INVALID_LIMITED_SESSION:
//                    logger.debug("No tiene servicio de redireccion y esta limitado.");
//                default:
//                    logger.debug("Radius CoA sessionValidISG Unsuccessfully. result: " + result);
//                    return true;
//            }
        } else {
            return RadiusCoaResult.FZONE_INTERNET_FULL;
        }
    }

    private boolean deactivateServiceISG(String email, String password, String remoteIp, String service) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiuscoaenable"))) {
            RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
            logger.debug("Sending Radius CoA deactivateService... service: " + service);
            RadiusResult result = radius.deactivateService(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, password, remoteIp, service);
            switch (result.getResultType()) {
                case LOGON:
                    ManagerStatsClicks.getInstance().incrementLoginCoASuccess();
                    logger.debug("Radius CoA deactivateService Successfully.");
                    return true;
                case MEMORY_OR_INTERNAL_ERROR:
                case INVALID_PASSWORD:
                case INVALID_USERNAME:
                case INVALID_USERNAME_AND_PASSWORD:
                default:
                    ManagerStatsClicks.getInstance().incrementLoginCoAFail();
                    logger.debug("Radius CoA deactivateService Unsuccessfully.");
                    return false;
            }
        } else {
            return true;
        }
    }

    private boolean logoffISG(String email, String remoteAddr, ClientType client, Boolean temporary) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiuscoaenable"))) {
            RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
            logger.debug("Sending Radius CoA logoff...");
            RadiusResult result = radius.logoff(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, remoteAddr);
            switch (result.getResultType()) {
                case LOGOFF:
                    ManagerStatsClicks.getInstance().incrementLogoffCoASuccess();
                    logger.debug("Radius CoA Logoff Successfully.");
                    return true;
                case INVALID_PASSWORD:
                case INVALID_USERNAME:
                case INVALID_USERNAME_AND_PASSWORD:
                default:
                    ManagerStatsClicks.getInstance().incrementLogoffCoAFail();
                    logger.debug("Radius CoA Logoff Unsuccessfully.");
                    return false;
            }
        } else {
            return true;
        }
    }

//    private boolean loginCSM(String email, String password, String remoteIp, ClientType client, Boolean temporary) {
//        try {
//            ResponseDynamicServiceOperation response = null;
//            if (temporary) {
//                switch (client) {
//                    case NO_CLIENTE_EMAIL:
//                        //portalSubscriberLogin y luego startSessionDescriptor
//                        //subscriberId,subscriberIP,packageId,technology,productId
//                        portalSubscriberLogin(email, remoteIp, -1, "cable", "NO_CLIENTE", null);
//                        response = startSessionDynamicServiceDescriptor(email, "NO_CLIENTE_TEMPORARY", null);
//                        break;
//                }
//            } else {
//                switch (client) {
//                    case CLIENTE:
//                        portalSubscriberLogin(email, remoteIp, -1, "cable", "CLIENTE", null);
//                        response = startSessionDynamicServiceDescriptor(email, "CLIENTE", null);
//                        break;
//                    case NO_CLIENTE:
//                    case NO_CLIENTE_FACEBOOK:
//                        portalSubscriberLogin(email, remoteIp, -1, "cable", "NO_CLIENTE", null);
//                        response = startSessionDynamicServiceDescriptor(email, "NO_CLIENTE", null);
//                        break;
//                    case NO_CLIENTE_EMAIL:
//                        response = startSessionDynamicServiceDescriptor(email, "NO_CLIENTE", null);
//                        break;
//                }
//            }
//            logger.debug("loginCSM response: " + getResponse(response));
//            if(response!=null&&response.getErrorCode().equals("A0000")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//    private boolean logoutCSM(String email, String remoteAddr, ClientType client, Boolean temporary) {
//        try {
//            ResponseDynamicServiceOperation response = null;
//            if (temporary) {
//                switch (client) {
//                    case NO_CLIENTE_EMAIL:
//                        response = stopSessionDynamicServiceDescriptor(email, "NO_CLIENTE_TEMPORARY", null, null);
//                        break;
//                }
//            } else {
//                switch (client) {
//                    case CLIENTE:
//                        response = stopSessionDynamicServiceDescriptor(email, "CLIENTE", null, null);
//                        break;
//                    case NO_CLIENTE:
//                    case NO_CLIENTE_FACEBOOK:
//                    case NO_CLIENTE_EMAIL:
//                        response = stopSessionDynamicServiceDescriptor(email, "NO_CLIENTE", null, null);
//                        break;
//                }
//            }
//            logger.debug("logoutCSM response: " + getResponse(response));
//            if(response!=null&&response.getErrorCode().equals("A0000")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    public SettingsConfiguration getSettingsConfiguration() {
        return settingsFacade.getSettingsConfiguration();
    }

    public void loadInfoForRadiusUser(String email, String resultplan) {
        long aux = System.currentTimeMillis();
        //Cargar radcheck
        logger.debug("loadInfoForRadiusUser...");
        try {
            try {
                logger.debug("updating radcheck...");
                //Hay que insertar en radcheck sino existe
                Radcheck user = (Radcheck) em.createNamedQuery("Radcheck.findByUserName").
                        setParameter("userName", email).getSingleResult();
                logger.debug("Existe el usuario en radcheck.");
            } catch (NoResultException ex) {
                //No existe, lo creo entonces
                logger.debug("Creando usuario email: " + email + "...");
                Radcheck user = new Radcheck();
                user.setUserName(email);
                user.setAttribute("Auth-Type");
                user.setValue("Accept");
                user.setOp(":=");
                em.persist(user);
                em.flush();
                logger.debug("Usuario creado en radcheck.");
            }
        } catch (Exception e) {
            context.setRollbackOnly();
            loggerError.error("error loadInfoForRadiusUser parte 1.", e);
        }
        //Cargar subscribers
        String plan = null;
        try {
            try {
                logger.debug("updating subscribers...");
                //Hay que insertar en radcheck sino existe
                Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").
                        setParameter("userName", email).getSingleResult();
                plan = sub.getGroupName();
                if (resultplan != null) {
                    String defaultPlan = getCalculatePlan(resultplan);
                    if (defaultPlan != null && !defaultPlan.equals(sub.getGroupName())) {
                        plan = defaultPlan;
                        sub.setGroupName(plan);
                        logger.debug("Cambiando plan to " + sub.getUserName() + " plan: " + sub.getGroupName() + ".");
                        em.merge(sub);
                        em.flush();
                    }
                } else {
                    //Este es caso de no cliente, me fijo si le tengo que cambiar el plan 
                    //por la nueva logica ( si esta en la base de clientes)
                    plan = getDefaultCVPlan();
                    logger.debug("Chequeando si existe el email: " + email + " en la base de fibertel.");
                    if (portalcvFiberteluserFacade.exists(email)) {
                        logger.debug("Existe.");
                        sub.setGroupName(plan);
                        logger.debug("Cambiando plan to " + sub.getUserName() + " plan: " + sub.getGroupName() + ".");
                        em.merge(sub);
                        em.flush();
                        updateAutoLoginInfo(sub.getUserName(), "cliente");
                    } else {
                        logger.debug("No existe.");
                    }
                }
                logger.debug("Existe el usuario en subscribers.");
            } catch (NoResultException ex) {
                //No existe, lo creo entonces
                String defaultPlan = getCalculatePlan(resultplan);
                if (defaultPlan != null) {
                    plan = defaultPlan;
                } else {
                    if (resultplan == null) {
                        //user no CV
                        if (portalcvFiberteluserFacade.exists(email)) {
                            plan = getDefaultCVPlan();
                        } else {
                            plan = getDefaultNONCVPlan();
                        }
                    } else {
                        //user CV
                        plan = getDefaultCVPlan();
                    }
                }
                logger.debug("Creando usuario para subscribers: email: " + email + " and planname: " + plan + "...");
                Subscribers sub = new Subscribers();
                sub.setUserName(email);
                sub.setGroupName(plan);
                sub.setPriority(1);
                logger.debug("Creando sub con plan to " + sub.getUserName() + " plan: " + sub.getGroupName() + ".");
                em.persist(sub);
                em.flush();
                logger.debug("Usuario creado en subscribers.");
            }
        } catch (Exception e) {
            context.setRollbackOnly();
            loggerError.error("error loadInfoForRadiusUser parte 2.", e);
        }
        //Cargo quota inicial si es la primera vez
        try {
            logger.debug("quota for plan..." + plan);
            List quotaConfigs = em.createNamedQuery("QuotaConfig.findByPlan").
                    setParameter("plan", plan).getResultList();
            Quota quota;
            QuotaConfig qC;
            for (Object quotaConfig : quotaConfigs) {
                qC = (QuotaConfig) quotaConfig;
                try {
                    quota = (Quota) em.createNamedQuery("Quota.findByUsernameByTypeByService").
                            setParameter("username", email).setParameter("type", qC.getType()).
                            setParameter("service", qC.getService()).getSingleResult();
                    quota.setSessionQuota(new BigInteger("0"));
                    em.merge(quota);
                } catch (NoResultException ex) {
                    logger.debug("cargando quota ..." + qC.getService() + " " + qC.getType() + " " + qC.getQuota());
                    quota = new Quota();
                    quota.setUsername(email);
                    quota.setAttribute("Cisco-Control-Info");
                    quota.setOp("+=");
                    quota.setValue(qC.getQuota());
                    quota.setType(qC.getType());
                    quota.setService(qC.getService());
                    em.persist(quota);
                }
            }
            try {
                List quotas = em.createNamedQuery("Quota.findByUsername").
                        setParameter("username", email).getResultList();
                boolean exist = false;
                for (Object q : quotas) {
                    quota = (Quota) q;
                    exist = false;
                    for (Object quotaConfig : quotaConfigs) {
                        qC = (QuotaConfig) quotaConfig;
                        if (qC.getType().equals(quota.getType())
                                && qC.getService().equals(quota.getService())) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        em.remove(quota);
                    }
                }
            } catch (NoResultException ex) {
            }
            em.flush();
            logger.debug("Tiene quota seteada");
        } catch (NoResultException ex) {
            //No hay quota configurada, borro todas las que hayan si quedaron rancias
            try {
                List quotas = em.createNamedQuery("Quota.findByUsername").
                        setParameter("username", email).getResultList();
                for (Object quota : quotas) {
                    em.remove(quota);
                }
                em.flush();
            } catch (Exception ex1) {
            }
        }
        aux = System.currentTimeMillis() - aux;
        if (aux > 100) {
            loggerProfiling.debug("loadInfoForRadiusUser email: " + email + ". resultplan: " + resultplan + ". Tardo: " + aux);
        }
    }

    public LoginResult fastLogin(String email, String remoteIp) {
        if (loginAccount(email, defaultPassword, remoteIp, ClientType.NO_CLIENTE, false)) {
            return LoginResult.SUCCESSFULL;
        } else {
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    public LoginResult loginNOCVAccount(String email, String remoteIp) {
        try {
            long aux = System.currentTimeMillis();
            LoginResult result;
            logger.debug("Logging user. Email: " + email + ". IPAddress: " + remoteIp + ".");
            RegisteredUser user = null;
            try {
                user = (RegisteredUser) em.createNamedQuery("RegisteredUser.findByEmail").setParameter("email", email).getSingleResult();
            } catch (NoResultException e) {
                //Lo creo y lo leo
                createUserNOCV(email);
                user = (RegisteredUser) em.createNamedQuery("RegisteredUser.findByEmail").setParameter("email", email).getSingleResult();
            }
            //Usuario NO Cablevision
            if (user != null && user.getActivated()) {
                if (loginAccount(email, defaultPassword, remoteIp, ClientType.NO_CLIENTE, false)) {
                    result = LoginResult.SUCCESSFULL;
                } else {
                    result = LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
                }
            } else {
                logger.debug("The user is not actived.");
                result = LoginResult.ACCOUNT_NOT_VALIDATED;
            }
            aux = System.currentTimeMillis() - aux;
            logger.debug("loginNOCVAccount. email: " + email + ". remoteIp: " + remoteIp + ". result: " + result + ". Tardo: " + aux);
            return result;
        } catch (Exception e) {
            context.setRollbackOnly();
            loggerError.error("error login. email: " + email + ". remoteIp: " + remoteIp, e);
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    public LoginResult loginNOCVAccountViaFacebook(String email, String remoteIp) {
        try {
            long aux = System.currentTimeMillis();
            LoginResult result;
            if (loginAccount(email, defaultPassword, remoteIp, ClientType.NO_CLIENTE_FACEBOOK, false)) {
                result = LoginResult.SUCCESSFULL;
            } else {
                result = LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
            }
            aux = System.currentTimeMillis() - aux;
            logger.debug("loginNOCVAccountViaFacebook. email: " + email + ". remoteIp: " + remoteIp + ". result: " + result + ". Tardo: " + aux);
            return result;
        } catch (NoResultException e) {
            context.setRollbackOnly();
            loggerError.error("error login.", e);
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    public LoginResult loginCVAccount(String email, String remoteIp) {
        try {
            //Usuario Cablevision
            long aux = System.currentTimeMillis();
            LoginResult result;
            if (loginAccount(email, defaultPassword, remoteIp, ClientType.CLIENTE, false)) {
                result = LoginResult.SUCCESSFULL;
            } else {
                result = LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
            }
            aux = System.currentTimeMillis() - aux;
            logger.debug("loginCVAccount. email: " + email + ". remoteIp: " + remoteIp + ". result: " + result + ". Tardo: " + aux);
            return result;
        } catch (NoResultException e) {
            context.setRollbackOnly();
            loggerError.error("error login.", e);
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    private LoginResult loginCVLimitedAccount(String email, String remoteIp) {
        try {
            long aux = System.currentTimeMillis();
            //Usuario Cablevision
            LoginResult result;
            if (loginAndActivateServiceAccount(email, defaultPassword, remoteIp, ClientType.CLIENTE, false, serviceBlockPort80)) {
                result = LoginResult.SUCCESSFULL;
            } else {
                result = LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
            }
            aux = System.currentTimeMillis() - aux;
            logger.debug("loginCVLimitedAccount. email: " + email + ". remoteIp: " + remoteIp + ". result: " + result + ". Tardo: " + aux);
            return result;
        } catch (NoResultException e) {
            context.setRollbackOnly();
            loggerError.error("error login.", e);
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    public RadiusCoaResult sessionValid(String username, String cpeIP) {
        if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("ValidateSessionViaISG"))) {
            return sessionValidThroughISG(username, cpeIP);
        } else {
            RadiusCoaResult result = sessionValidThroughDBAccounting(username, cpeIP);
            if (result.equals(RadiusCoaResult.UNKNOWN)) {
                return sessionValidThroughISG(username, cpeIP);
            } else {
                return result;
            }
        }
    }

    private LoginResult deactivateBlockPort80Service(String email, String remoteIp) {
        try {
            //Usuario Cablevision
            logger.debug("Login cablevision user.");
            if (deactivateServiceISG(email, defaultPassword, remoteIp, serviceBlockPort80)) {
                return LoginResult.SUCCESSFULL;
            } else {
                return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
            }
        } catch (NoResultException e) {
            context.setRollbackOnly();
            loggerError.error("error login.", e);
            return LoginResult.INCORRECT_USERNAME_AND_PASSWORD;
        }
    }

    public LoginResult logoutBlockPort80Service(String email, String remoteIp) {
        try {
            int reintentos = 0;
            boolean terminoOk = false;
            LoginResult result = null;
            while (reintentos < 3) {
                result = deactivateBlockPort80Service(email, remoteIp);
                switch (result) {
                    case SUCCESSFULL:
                        ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
                        terminoOk = true;
                        break;
                    default:
                        reintentos++;
                        ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                        Thread.sleep(100L);
                        break;
                }
                if (terminoOk) {
                    break;
                }
            }
            return result;
        } catch (Exception ex) {
            loggerError.error("error logoutBlockPort80Service. email: " + email + ". remoteIp: " + remoteIp + ".", ex);
        }
        return null;
    }

    public LoginResult loginCVVideoAccount(String email, String remoteIp) {
        try {
            int reintentos = 0;
            boolean terminoOk = false;
            LoginResult result = null;
            while (reintentos < 3) {
                result = loginCVAccount(email, remoteIp);
                switch (result) {
                    case SUCCESSFULL:
                        ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
                        terminoOk = true;
                        break;
                    default:
                        reintentos++;
                        ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                        Thread.sleep(100L);
                        break;
                }
                if (terminoOk) {
                    break;
                }
            }
            return result;
        } catch (Exception ex) {
            loggerError.error("error loginCVVideoAccount. email: " + email + ". remoteIp: " + remoteIp + ".", ex);
        }
        return null;
    }

    public String getCalculatePlan(String resultplan) {
        if (resultplan != null) {
            String plan = planregexFacade.getPlanByRegex(resultplan);
            if (plan != null) {
                return plan;
            } else {
                return null;
            }
        } else {
            return getDefaultNONCVPlan();
        }
    }

    private String getDefaultCVPlan() {
        return settingsFacade.getSettingsConfiguration().getProperty("defaultcvplan");
    }

    private String getDefaultNONCVPlan() {
        return settingsFacade.getSettingsConfiguration().getProperty("defaultnoncvplan");
    }

    public void sessionQuery(String email, String remoteAddr) {
        RadiusSender radius = new RadiusSender(Integer.valueOf(settingsFacade.getSettingsConfiguration().getProperty("radiusserverport")), settingsFacade.getSettingsConfiguration().getProperty("radiussecret"));
        logger.debug("Sending Radius CoA logon...");
        RadiusResult result = radius.sessionQuery2(settingsFacade.getSettingsConfiguration().getProperty("radiusserverip"), email, remoteAddr);
//        switch (result.getResultType()) {
//            case LOGON:
//                logger.debug("Radius CoA Logon Successfully.");
//                return true;
//            case INVALID_PASSWORD:
//            case INVALID_USERNAME:
//            case INVALID_USERNAME_AND_PASSWORD:
//            default:
//                logger.debug("Radius CoA Logon Unsuccessfully.");
//                return false;
//        }
    }

    private String getMailMessageForActivation(AccountValidation validation) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Gracias por registrarte en FiberZone!</p> " + "<p>Accede al siguiente enlace para activar la cuenta: </p>" + "<p>").
                append(settingsFacade.getSettingsConfiguration().getProperty("url")).append("/confirmation?hash=").append(validation.getHashcode()).
                append(" </p>" + "<p>- FiberZone - Powered by Callis ").append(settingsFacade.getSettingsConfiguration().getProperty("url")).append(" </p>");
        return sb.toString();
    }

    private String getMailMessageForActivation2(String content, AccountValidation validation) {
        String link = settingsFacade.getSettingsConfiguration().getProperty("url") + "/confirmation?hash=" + validation.getHashcode();
        logger.debug("Link validacion: " + link);
        return content.replaceAll("%%CONFIRMATIONLINK%%", link);
    }

    public void saveInfoUserData(String username, String ipAddr, String userAgent, String logintype) {
        logger.debug("saveInfoUserData: username: " + username + ". ipAddr: " + ipAddr + ". userAgent: " + userAgent + ". logintype: " + logintype + ".");
        userdataFacade.saveInfoUserData(username, ipAddr, userAgent, logintype);
    }

    public static void encolarTrabajo(Runnable r) {
        cachedThreadPool.submit(r);
    }

    public void getsACVPorLaInfo(String code, String userid) {
        encolarTrabajo(new GetInfoFibertel(this, code, userid));
    }

    public void saveClienteInfo(String email, String resultplan) {
        //Cargar subscribers
        try {
            logger.debug("updating client info plan...");
            String plan = planregexFacade.getPlanByRegex(resultplan);
            if (plan != null) {
                uc.begin();
                Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").
                        setParameter("userName", email).getSingleResult();
                sub.setGroupName(plan);
                em.merge(sub);
                em.flush();
                uc.commit();
            }
            logger.debug("updated client info plan...");
        } catch (Exception e) {
            loggerError.error("error saveClienteInfo.", e);
            context.setRollbackOnly();
        }
    }

    public ClientLogin getClientLoginByKey(String logggedKey) {
        try {
            logger.debug("getClientLoginByKey...");
            //Hay que insertar en radcheck sino existe
            ClientLogin cli = (ClientLogin) em.createNamedQuery("ClientLogin.findByAuthKey").
                    setParameter("authKey", logggedKey).getSingleResult();
            logger.debug("getClientLoginByKey.");
            return cli;
        } catch (NoResultException ex) {
        }
        return null;
    }

    public void createClientLoginAuth(String username, String authCookie) {
        try {
            logger.debug("createClientLoginAuth...");
            //Hay que insertar en radcheck sino existe
            ClientLogin cli = (ClientLogin) em.createNamedQuery("ClientLogin.findByEmail").
                    setParameter("email", username).getSingleResult();
            cli.setAuthKey(authCookie);
            em.persist(cli);
            em.flush();
            logger.debug("createClientLoginAuth.");
        } catch (NoResultException ex) {
            try {
                logger.debug("createClientLoginAuth2...");
                ClientLogin clientLogin = new ClientLogin();
                clientLogin.setEmail(username);
                clientLogin.setAuthKey(authCookie);
                clientLogin.setDateLastLogin(Calendar.getInstance().getTime());
                em.persist(clientLogin);
                em.flush();
                logger.debug("createClientLoginAuth2.");
            } catch (Exception e) {
                loggerError.error("error createClientLoginAuth.", e);
                context.setRollbackOnly();
            }
        }
    }

    public void updateClientLogin(ClientLogin clientLogin) {
        try {
            logger.debug("updateClientLogin...");
            em.merge(clientLogin);
            em.flush();
            logger.debug("updateClientLogin.");
        } catch (Exception ex) {
            loggerError.error("error updateClientLogin.", ex);
            context.setRollbackOnly();
        }
    }

    public void removeClientLoginKey(String userAuth) {
        try {
            logger.debug("removeClientLoginKey...");
            //Hay que insertar en radcheck sino existe
            ClientLogin cli = (ClientLogin) em.createNamedQuery("ClientLogin.findByAuthKey").
                    setParameter("authKey", userAuth).getSingleResult();
            em.remove(cli);
            em.flush();
            logger.debug("removeClientLoginKey.");
        } catch (NoResultException ex) {
        }
    }

    public void createUserNOCV(String email) {
        try {
            logger.debug("Registering new User NOCV: Mail: " + email + ".");
            //me fijo si esta el usuario
            RegisteredUser user = (RegisteredUser) em.createNamedQuery("RegisteredUser.findByEmail").setParameter("email", email).getSingleResult();
            //crear usuario
            user.setActivated(true);
            user.setEmail(email);
            user.setDateCreated(Calendar.getInstance().getTime());
            em.merge(user);
            em.flush();
            logger.debug("Updating user NOCV info.");
        } catch (Exception e) {
            RegisteredUser user = new RegisteredUser();
            user.setActivated(true);
            user.setEmail(email);
            user.setDateCreated(Calendar.getInstance().getTime());
            em.persist(user);
            em.flush();
            logger.debug("New user NOCV info saved.");
        }
    }

    //Comentado para cuando usa la version del service manager
//    private ResponseDynamicServiceOperation startSessionDynamicServiceDescriptor(java.lang.String arg0, java.lang.String arg1, policyserver.ws.af.PropertiesWs arg2) {
//        policyserver.ws.af.WebServiceApplicationFunction port = service.getWebServiceApplicationFunctionPort();
//        return port.startSessionDynamicServiceDescriptor(arg0, arg1, arg2);
//    }
//
//    private String portalSubscriberLogin(java.lang.String arg0, java.lang.String arg1, java.lang.Integer arg2, java.lang.String arg3, java.lang.String arg4, policyserver.ws.spr.PropertiesWs arg5) {
//        policyserver.ws.spr.WebServiceSPR port = service_1.getWebServiceSPRPort();
//        return port.portalSubscriberLogin(arg0, arg1, arg2, arg3, arg4, arg5);
//    }
//
//    private ResponseDynamicServiceOperation stopSessionDynamicServiceDescriptor(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, policyserver.ws.af.PropertiesWs arg3) {
//        policyserver.ws.af.WebServiceApplicationFunction port = service.getWebServiceApplicationFunctionPort();
//        return port.stopSessionDynamicServiceDescriptor(arg0, arg1, arg2, arg3);
//    }
//    private String getResponse(ResponseDynamicServiceOperation response) {
//        StringBuilder sb = new StringBuilder();
//        if (response != null) {
//            sb.append("DynamicServiceDescriptor: ").append(response.getDynamicServiceDescriptor()).append(". ");
//            sb.append("ErrorCode: ").append(response.getErrorCode()).append(". ");
//            sb.append("Message: ").append(response.getMessage()).append(". ");
//            sb.append("Response: ").append(response.getResponse()).append(". ");
//            sb.append("SubscriberId: ").append(response.getSubscriberId()).append(". ");
//        }
//        return sb.toString();
//    }

    public void saveInfoReportesFromGetHome(String cpeIP, String userAgent) {
        PortalcvWlcaccounting wlcData = portalcvWlcaccountingFacade.getInfoByCPEIP(cpeIP);
        if (wlcData != null) {
            PortalcvUseragent ua = portalcvUseragentFacade.generateUserAgent(userAgent);
        } else {
            loggerReporter.info("No info WLC in saveInfoReportesFromGetHome. For CPEIP: " + cpeIP + ". userAgent: " + userAgent + ".");
        }
    }

    public void saveLoginData(String username, String cpeIp, String userAgent, String loginType) {
        long aux = System.currentTimeMillis();
        PortalcvWlcaccounting wlcAcctData = portalcvWlcaccountingFacade.getInfoByCPEIP(cpeIp);
        PortalcvUseragent userAgentData = portalcvUseragentFacade.generateUserAgent(userAgent);
        portalcvLoginFacade.saveLoginData(username, cpeIp, wlcAcctData, userAgentData, loginType);
        aux = System.currentTimeMillis() - aux;
        if (aux > 500) {
            loggerProfiling.debug("saveLoginData username: " + username + ". cpeIp: " + cpeIp + ". Tardo: " + aux);
        }
    }

    public PortalcvHomeresource getPortalLayout(PortalcvAp idAP, String url, String type) {
        return portalcvHomeFacade.getPortalLayout(idAP, url, type);
    }

    public PortalcvHomeresource getPortalLayout(Integer homeId, String url, String type) {
        return portalcvHomeFacade.getPortalLayout(homeId, url, type);
    }

    public PortalcvHomeresource getPortalLayout(String url, String type) {
        return portalcvHomeFacade.getPortalLayout(-1, url, type);
    }

    public void createAutoLoginInfo(String email, String logintype, LoginType loginType, String remoteAddr) {
        createAutoLoginInfo(email, logintype, remoteAddr);
    }

    public void createAutoLoginInfo(String username, String logintype, String remoteAddr) {
        PortalcvWlcaccounting wlcAcc = portalcvWlcaccountingFacade.getInfoByCPEIP(remoteAddr);
        if (wlcAcc != null && wlcAcc.getMaccpe() != null) {
            portalcvAutologinFacade.createAutoLoginInfo(username, wlcAcc.getMaccpe(), logintype);
            logger.warn("Creating AutoLogin data for username: " + username + ". cpeMac: " + wlcAcc.getMaccpe() + ". logintype: " + logintype + ".");
        } else {
            logger.warn("No info from WLC for AutoLogin process.");
        }
    }

    public void updateAutoLoginInfo(String username, String logintype) {
        portalcvAutologinFacade.updateAutoLoginInfo(username, logintype);
    }

    public void updateAutoLoginInfo(PortalcvAutologin autoLogin) {
        long aux = System.currentTimeMillis();
        portalcvAutologinFacade.updateAutoLoginInfo(autoLogin);
        aux = System.currentTimeMillis() - aux;
        if (aux > 500) {
            loggerProfiling.debug("updateAutoLoginInfo. Tardo: " + aux);
        }
    }

    public boolean checkSessionActiveAndAutoLoginByCPEMac(String cpeMac, String cpeIP) {
        RadiusCoaResult sessionValid = sessionValid("frula", cpeIP);
        switch (sessionValid) {
            case FZONE_REDIRECT:
                //Esta siendo redirigido
                PortalcvAutologin autoLogin = validAutoLoginDataByCPEMac(cpeMac);
                if (autoLogin != null) {
                    autoLoginLimitedByCPEIP(cpeIP);
                }
                return true;
            default:
                break;
        }
        return false;
    }

    public void autoLogin(PortalcvAutologin autoLogin, String cpeMac, String cpeIP, String tipo) {
        logger.debug("autoLogin START.");
        try {
            long aux = System.currentTimeMillis();
            saveInfoUserData(autoLogin.getUsername(), cpeIP, null, "autologin " + tipo);
            int reintentos = 0;
            boolean terminoOk = false;
            if (Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("AutoLoginLimitedEnabled"))) {
                RadiusCoaResult sessionValid = sessionValid(autoLogin.getUsername(), cpeIP);
                LoginResult result;
                while (reintentos < 3) {
                    switch (sessionValid) {
                        case FZONE_INTERNET_FULL:
                            result = LoginResult.SUCCESSFULL;
                            logger.info("autoLogin Devolvio FZONE_INTERNET_FULL no hago nada.");
                            break;
                        case FZONE_INTERNET_LIMITED:
                            result = deactivateBlockPort80Service(autoLogin.getUsername(), cpeIP);
                            break;
                        case FZONE_REDIRECT:
                            result = loginCVAccount(autoLogin.getUsername(), cpeIP);
                            break;
                        default:
                            result = LoginResult.SUCCESSFULL;
                            logger.info("autoLogin Devolvio " + result + " . Esto no deberia pasar. No hago nada.");
                            break;
                    }
                    switch (result) {
                        case SUCCESSFULL:
                            ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
                            saveLoginData(autoLogin.getUsername(), cpeIP, null, "autologin " + tipo);
                            updateAutoLoginInfo(autoLogin);
                            logger.info("autoLogin OK.");
                            terminoOk = true;
                            break;
                        default:
                            reintentos++;
                            ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                            logger.warn("autoLogin FAIL. cpeIP: " + cpeIP);
                            Thread.sleep(100L);
                            break;
                    }
                    if (terminoOk) {
                        break;
                    }
                }
            } else {
                LoginResult result;
                while (reintentos < 3) {
                    result = loginCVAccount(autoLogin.getUsername(), cpeIP);
                    switch (result) {
                        case SUCCESSFULL:
                            ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
                            saveLoginData(autoLogin.getUsername(), cpeIP, null, "autologin " + tipo);
                            updateAutoLoginInfo(autoLogin);
                            logger.info("autoLogin OK.");
                            terminoOk = true;
                            break;
                        default:
                            reintentos++;
                            ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                            logger.warn("autoLogin FAIL. cpeIP: " + cpeIP);
                            Thread.sleep(100L);
                            break;
                    }
                    if (terminoOk) {
                        break;
                    }
                }
            }
            if (!terminoOk) {
                logger.warn("autoLogin NO terminoOk for autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
            }
            aux = (System.currentTimeMillis() - aux);
            if (aux > 500) {
                loggerProfiling.debug("/autoLogin from " + cpeIP + " costs " + aux + " ms.");
            }
        } catch (Exception ex) {
            loggerError.error("Error autoLogin.", ex);
        }
        logger.debug("autoLogin FIN2.");
    }

    private boolean autoLoginLimited(PortalcvAutologin autoLogin, String cpeMac, String cpeIP, String tipo, boolean limitedEnabled) {
        logger.debug("autoLoginLimited START. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
        boolean response = false;
        try {
            long aux = System.currentTimeMillis();
            saveInfoUserData(autoLogin.getUsername(), cpeIP, null, "autologin " + tipo);
            int reintentos = 0;
            boolean terminoOk = false;
            if (limitedEnabled) {
                RadiusCoaResult sessionValid = sessionValid(autoLogin.getUsername(), cpeIP);
                switch (sessionValid) {
                    case FZONE_INTERNET_FULL:
                    case FZONE_INTERNET_LIMITED:
                        logger.info("autoLoginLimited NO hago nada porque ya tenia internet. sessionValid: " + sessionValid + ". autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                        break;
                    case FZONE_REDIRECT:
                        while (reintentos < 3) {
                            LoginResult result = loginCVLimitedAccount(autoLogin.getUsername(), cpeIP);
                            switch (result) {
                                case SUCCESSFULL:
                                    ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
//                                    saveLoginData(autoLogin.getUsername(), cpeIP, null, "autologin limited " + tipo);
                                    updateAutoLoginInfo(autoLogin);
                                    logger.info("autoLogin OK. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                                    terminoOk = true;
                                    break;
                                default:
                                    reintentos++;
                                    ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                                    logger.warn("autoLogin FAIL. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                                    Thread.sleep(100L);
                                    break;
                            }
                            if (terminoOk) {
                                break;
                            }
                        }
                        break;
                    default:
                        logger.info("autoLoginLimited NO hago nada. sessionValid: " + sessionValid + ". autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                        break;
                }
            } else {
                while (reintentos < 3) {
                    LoginResult result = loginCVAccount(autoLogin.getUsername(), cpeIP);
                    switch (result) {
                        case SUCCESSFULL:
                            ManagerStatsClicks.getInstance().incrementAutoLoginSuccess();
                            saveLoginData(autoLogin.getUsername(), cpeIP, null, "autologin limited " + tipo);
                            updateAutoLoginInfo(autoLogin);
                            logger.info("autoLogin OK2. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                            terminoOk = true;
                            break;
                        default:
                            reintentos++;
                            ManagerStatsClicks.getInstance().incrementAutoLoginFail();
                            logger.warn("autoLogin FAIL2. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
                            Thread.sleep(100L);
                            break;
                    }
                    if (terminoOk) {
                        break;
                    }
                }
            }
            response = terminoOk;
            if (!terminoOk) {
                logger.warn("autoLoginLimited NO terminoOk for autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
            }
            aux = (System.currentTimeMillis() - aux);
            if (aux > 500) {
                loggerProfiling.debug("autoLoginLimited from " + cpeIP + " costs " + aux + " ms.");
            }
        } catch (Exception ex) {
            response = false;
            loggerError.error("Error autoLoginLimited. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".", ex);
        }
        logger.debug("autoLoginLimited FIN2. autoLogin: " + autoLogin + ". cpeMac: " + cpeMac + ". cpeIP: " + cpeIP + ". tipo: " + tipo + ".");
        return response;
    }

    public LoginResult logoutAccount(String ip) {
        if (logoutAccount("pepe", ip, ClientType.ANY, false)) {
            return LoginResult.SUCCESSFULL;
        } else {
            return LoginResult.ERROR;
        }
    }

    public LoginResult signout(String ip) {
        PortalcvWlcaccounting wlcAcct = getWLCInfoByCPEIP(ip);
        if (wlcAcct != null) {
            if (logoutAccount("pepe", ip, ClientType.ANY, false)) {
                portalcvAutologinFacade.removeAutoLogin(wlcAcct.getMaccpe());
                return LoginResult.SUCCESSFULL;
            } else {
                return LoginResult.ERROR;
            }
        } else {
            loggerError.error("Error en signout. wlcAcct null. ip: " + ip);
            return LoginResult.ERROR;
        }
    }

    /**
     * Este es el metodo general que hace la logica del logout ya se si trabaja
     * con el ISG o si trabaja con el CSM
     */
    private boolean logoutAccount(String email, String remoteAddr, ClientType client, Boolean temporary) {
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        boolean result = false;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
//                result = logoutCSM(email, remoteAddr, client, temporary);
            } else {
                //MODE ISG
                result = logoffISG(email, remoteAddr, client, temporary);
            }
        } else {
            //DEFAULT MODE ISG
            result = logoffISG(email, remoteAddr, client, temporary);
        }
        return result;
    }

    public PortalcvWlcaccounting getWLCInfoByCPEIP(String cpeIP) {
        return portalcvWlcaccountingFacade.getInfoByCPEIP(cpeIP);
    }

    public PortalcvAutologin validAutoLoginDataByCPEMac(String cpeMac) {
        try {
            PortalcvAutologin autoLogin = portalcvAutologinFacade.getAutoLogin(cpeMac);
            if (autoLogin != null) {
                if (portalcvAutologinFacade.validAutoLoginData(autoLogin, settingsFacade.getSettingsConfiguration().getProperty("AutoLoginValidTimeInMinutes"))) {
                    return autoLogin;
                }
            }
        } catch (Exception ex) {
            loggerError.error("Error validAutoLoginDataByCPEIP.", ex);
        }
        return null;
    }

    public AutoLoginResult autoLoginByCPEIP(String cpeIP) {
        PortalcvWlcaccounting wlcAcc = null;
        try {
            wlcAcc = portalcvWlcaccountingFacade.getInfoByCPEIP(cpeIP);
            if (wlcAcc != null) {
                PortalcvAutologin autoLogin = portalcvAutologinFacade.getAutoLogin(wlcAcc.getMaccpe());
                if (autoLogin != null) {
                    boolean cliente = isClientDueToIsInDBFibertel(autoLogin.getUsername());
                    if (cliente || portalcvAutologinFacade.validAutoLoginData(autoLogin, settingsFacade.getSettingsConfiguration().getProperty("AutoLoginValidTimeInMinutes"))) {
                        loadInfoForRadiusUser(autoLogin.getUsername(), null);
                        autoLogin(autoLogin, wlcAcc.getMaccpe(), cpeIP, autoLogin.getLogintype());
                        if (cliente) {
                            return AutoLoginResult.CLIENTE;
                        } else {
                            return AutoLoginResult.VALID_NO_CLIENTE;
                        }
                    } else {
                        return AutoLoginResult.INVALID_NO_CLIENTE;
                    }
                }
            }
        } catch (Exception ex) {
            loggerError.error("Error autoLoginByCPEIP. cpeIP: " + cpeIP + ". wlcAcc: " + wlcAcc, ex);
        }
        return AutoLoginResult.OTHER;
    }

    public boolean autoLoginLimitedByCPEIP(String cpeIP) {
        PortalcvWlcaccounting wlcAcc = null;
        boolean result = false;
        long aux = 0;
        long aux2 = 0;
        long aux3 = 0;
        try {
            aux = System.currentTimeMillis();
            aux2 = System.currentTimeMillis();
            wlcAcc = portalcvWlcaccountingFacade.getInfoByCPEIP(cpeIP);
            PortalcvAutologin autoLogin = portalcvAutologinFacade.getAutoLogin(wlcAcc.getMaccpe());
            if (autoLogin != null) {
                aux2 = System.currentTimeMillis() - aux2;
                if (portalcvAutologinFacade.validAutoLoginData(autoLogin, settingsFacade.getSettingsConfiguration().getProperty("AutoLoginValidTimeInMinutes"))) {
                    aux3 = System.currentTimeMillis();
                    loadInfoForRadiusUser(autoLogin.getUsername(), null);
                    result = autoLoginLimited(autoLogin, wlcAcc.getMaccpe(), cpeIP, autoLogin.getLogintype(), Boolean.valueOf(settingsFacade.getSettingsConfiguration().getProperty("LoginAppsLimitedEnabled")));
                    aux3 = System.currentTimeMillis() - aux3;
                }
            }
            aux = System.currentTimeMillis() - aux;
        } catch (Exception ex) {
            loggerError.error("Error autoLoginByCPEIP. cpeIP: " + cpeIP + ". wlcAcc: " + wlcAcc, ex);
        }
        if (aux > 500) {
            loggerProfiling.debug("autoLoginLimitedByCPEIP cpeIP: " + cpeIP + ". Tardo: " + aux + ". aux2: " + aux2 + ". aux3: " + aux3 + ".");
        }
        return result;
    }

    public boolean isClientDueToIsInDBFibertel(String username) {
        try {
            //Chequea si el plan del subscriber es igual al default de los clientes.
            logger.debug("updating subscribers...");
            Subscribers sub = (Subscribers) em.createNamedQuery("Subscribers.findByUserName").
                    setParameter("userName", username).getSingleResult();
            if (sub.getGroupName().equalsIgnoreCase(getDefaultCVPlan())) {
                return true;
            }
        } catch (Exception ex) {
            loggerError.error("Error isClientDueToIsInDBFibertel. username: " + username, ex);
        }
        return false;
    }

    public String getLoginTypeByFibertelEmails(String username) {
        if (portalcvFiberteluserFacade.exists(username)) {
            return "cliente";
        } else {
            return "no cliente";
        }
    }

    public void saveFacebookData(Map parameterMap) {
        portalcvFacebookdataFacade.saveFacebookData(parameterMap);
    }

    public void saveGoogleData(Map<String, String> datos) {
        portalcvGoogledataFacade.saveGoogleData(datos);
    }

    public void saveTwitterData(User userTwitter) {
        portalcvTwitterdataFacade.saveTwitterData(userTwitter);
    }

    public boolean validateMundoCliente(String email, String password) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        boolean result = false;
        try {
            //Get
            HttpPost getRequest = new HttpPost("http://www.cablevisionfibertel.com.ar/handler/login.ashx");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("ClientId", "556E7106-9133-472E-965E-E86333A48F36"));
            nameValuePairs.add(new BasicNameValuePair("Redirect", "/"));
            nameValuePairs.add(new BasicNameValuePair("Username", email));
            nameValuePairs.add(new BasicNameValuePair("Password", password));
            getRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //{"Status":"OK", "Errors":"", "Redirect":"http://fbzauth.fibertel.com.ar/loginCV?UserId=ssensabastiano@gmail.com&Code=1E21B2BE-0472-4DCC-BC42-5F72872F9E13", "UserData":"Id=724553&Name=SEBASTIAN SENSABASTIANO&LastName=&Email=Ssensabastiano@gmail.com&Cuic=00427286366"}
            //{"Status":"OK", "Errors":"", "Redirect":"http://fbzauth.fibertel.com.ar/loginCV?UserId=ssensabastiano@gmail.com&Code=B83184F3-0A16-448E-A52B-BAA6D8BEE5EB", "UserData":"Id=724553&Name=SEBASTIAN SENSABASTIANO&LastName=&Email=ssensabastiano@gmail.com&Cuic=00427286366"}
            //{"Status":"ERROR", "Errors":"Invalid_User", "Redirect":"/", "UserData":""}

            HttpResponse response2 = httpClient.execute(getRequest);
            if (response2.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response2.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response2.getEntity().getContent())));
            String output;
            StringBuilder sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            if (sb.toString().contains("\"Status\":\"OK\"")) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception ex) {
            loggerError.error("Error validateMundoCliente. email: " + email + ". password: " + password + ".", ex);
        }
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    private RadiusCoaResult sessionValidFromISGAccounting(String username, String defaultPassword, String cpeIP) {
        return radacctFacade.sessionValidFromISGAccounting(username, cpeIP);
    }

    private RadiusCoaResult sessionValidThroughISG(String username, String cpeIP) {
        //Valido la session via session query ISG
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        RadiusCoaResult result = null;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
                return RadiusCoaResult.FZONE_INTERNET_FULL;
            } else {
                //MODE ISG
                result = sessionValidISG(username, defaultPassword, cpeIP);
            }
        } else {
            //DEFAULT MODE ISG
            result = sessionValidISG(username, defaultPassword, cpeIP);
        }
        return result;
    }

    private RadiusCoaResult sessionValidThroughDBAccounting(String username, String cpeIP) {
        //Valido la session contra la tabla de accounting
        Object mode = settingsFacade.getSettingsConfiguration().getProperty("mode");
        RadiusCoaResult result = null;
        if (mode != null) {
            if (mode.toString().equals("servicemanager")) {
                return RadiusCoaResult.FZONE_INTERNET_FULL;
            } else {
                //MODE ISG
                result = sessionValidFromISGAccounting(username, defaultPassword, cpeIP);
            }
        } else {
            //DEFAULT MODE ISG
            result = sessionValidFromISGAccounting(username, defaultPassword, cpeIP);
        }
        return result;
    }
}
