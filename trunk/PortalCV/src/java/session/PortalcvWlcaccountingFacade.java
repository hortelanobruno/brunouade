/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvAp;
import entity.PortalcvAutologin;
import entity.PortalcvWlcaccounting;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import mdb.events.WLCAutologinEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvWlcaccountingFacade extends AbstractFacade<PortalcvWlcaccounting> {

    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    private Logger loggerProfiling = Logger.getLogger("portalProfiling");
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @EJB
    private LoginSessionBean loginSessionBean;
    @Resource(mappedName = "jms/WLCAutologinConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/WLCAutologinQueue")
    private Queue queue;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvWlcaccountingFacade() {
        super(PortalcvWlcaccounting.class);
    }

    public void createAcctStart(long currentTimeMillis, String cpeIP, String cpeMac, PortalcvAp ap, String sessionId) {
        try {
            long aux = System.currentTimeMillis();
            long aux2 = 0;
            long aux3 = 0;
            if (cpeIP != null) {
                PortalcvWlcaccounting wlc = new PortalcvWlcaccounting();
                wlc.setAcctStart(new Timestamp(currentTimeMillis));
                wlc.setMaccpe(cpeMac);
                wlc.setIdAP(ap);
                wlc.setIpcpe(cpeIP);
                em.persist(wlc);
                em.flush();

                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("AutoLoginLimitedEnabled"))) {
                    //Hacer autoLogin
                    aux2 = System.currentTimeMillis();
                    PortalcvAutologin autoLogin = loginSessionBean.validAutoLoginDataByCPEMac(cpeMac);
                    aux2 = System.currentTimeMillis() - aux2;
                    if (validAutologin(autoLogin)) {
                        aux3 = System.currentTimeMillis();
//                        loginSessionBean.autoLoginLimitedByCPEIP(cpeIP);
                        WLCAutologinEvent event = new WLCAutologinEvent();
                        event.setCpeIP(cpeIP);
                        event.setCpeMac(cpeMac);
                        ponerEnLaCola(event);
                        aux3 = System.currentTimeMillis() - aux3;
                    }
                }
            }
            aux = System.currentTimeMillis() - aux;
            if (aux > 500) {
                loggerProfiling.debug("createAcctStart cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". Tardo: " + aux + ". aux2: " + aux2 + ". aux3: " + aux3 + ".");
            }
        } catch (Exception ex) {
            loggerError.error("Error en createAcctStart. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". ap: " + ap, ex);
        }
    }

    public void createAcctAlive(long currentTimeMillis, String cpeIP, String cpeMac, PortalcvAp ap, String sessionId) {
        PortalcvWlcaccounting wlc = null;
        try {
            long aux = System.currentTimeMillis();
            wlc = getInfoByCPEIP(cpeIP);
            if (wlc == null || wlc.getIdAP() == null || !wlc.getIdAP().equals(ap) || wlc.getIpcpe() == null || !wlc.getIpcpe().equals(cpeIP) || wlc.getMaccpe() == null || !wlc.getMaccpe().equals(cpeMac)) {
                createAcctStop(currentTimeMillis, cpeIP, cpeMac, sessionId);
                createAcctStart(currentTimeMillis, cpeIP, cpeMac, ap, sessionId);
            } else {
                if (Boolean.valueOf(loginSessionBean.getSettingsConfiguration().getProperty("AutoLoginLimitedEnabled"))) {
                    boolean result = loginSessionBean.checkSessionActiveAndAutoLoginByCPEMac(cpeMac, cpeIP);
                    if (result) {
                        logger.info("checkSessionActiveAndAutoLoginByCPEMac true: cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + ap);
                    } else {
                        logger.info("Llego acctAlive no hago nada: cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". apMac: " + ap);
                    }
                }
            }
            aux = System.currentTimeMillis() - aux;
            if (aux > 500) {
                loggerProfiling.debug("createAcctAlive cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". Tardo: " + aux);
            }
        } catch (Exception ex) {
            loggerError.error("Error en createAcctAlive. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". ap: " + ap + ". wlc: " + wlc, ex);
        }
    }

    public void createAcctStop(long currentTimeMillis, String cpeIP, String cpeMac, String sessionId) {
        try {
            long aux = System.currentTimeMillis();
            PortalcvWlcaccounting wlc = getInfoByCPEIP(cpeIP);
            if (wlc != null) {
                wlc.setAcctStop(new Timestamp(currentTimeMillis));
                em.merge(wlc);
                em.flush();
            } else {
//                loggerError.warn("En createAcctStop wlc NULL. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". ap: " + ap);
            }
            aux = System.currentTimeMillis() - aux;
            if (aux > 500) {
                loggerProfiling.debug("createAcctStop cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ". Tardo: " + aux);
            }
        } catch (Exception ex) {
            loggerError.error("Error en createAcctAlive. cpeIP: " + cpeIP + ". cpeMac: " + cpeMac + ".", ex);
        }
    }

    public PortalcvWlcaccounting getInfoByCPEIP(String cpeIP) {
        PortalcvWlcaccounting wlcAccounting = null;
        long aux = System.currentTimeMillis();
        try {
            wlcAccounting = (PortalcvWlcaccounting) em.createNamedQuery("PortalcvWlcaccounting.findByIpcpeAndAcctStopNull").setParameter("ipcpe", cpeIP).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
//            loggerError.warn("NO DATA getInfoByCPEIP. cpeIP: " + cpeIP);
        } catch (Exception ex) {
            loggerError.error("Error en getInfoByCPEIP. cpeIP: " + cpeIP, ex);
        }
        aux = System.currentTimeMillis() - aux;
        if (aux > 500) {
            loggerProfiling.debug("getInfoByCPEIP cpeIP: " + cpeIP + ". Tardo: " + aux);
        }
        return wlcAccounting;
    }

    private boolean validAutologin(PortalcvAutologin autoLogin) {
        if (autoLogin != null) {
//            if (autoLogin.getLogintype().toLowerCase().contains("video")) {
//                return false;
//            }
            return true;
        }
        return false;
    }

    private void ponerEnLaCola(WLCAutologinEvent event) {
        javax.jms.Connection connection = null;
        javax.jms.Session session = null;
        MessageProducer messageProducer = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);

            ObjectMessage message;
            message = session.createObjectMessage();
            message.setObject(event);
            messageProducer.send(message);
            logger.info("ponerEnLaCola. event: " + event + ".");
        } catch (Exception ex) {
            logger.error("ERROR en ponerEnLaCola. event: " + event + ".", ex);
        } finally {
            if (messageProducer != null) {
                try {
                    messageProducer.close();
                } catch (Exception ex) {
                    logger.error("ERROR en ponerEnLaCola. event: " + event + ".", ex);
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex) {
                    logger.error("ERROR en ponerEnLaCola. event: " + event + ".", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    logger.error("ERROR en ponerEnLaCola. event: " + event + ".", ex);
                }
            }
        }
    }
}
