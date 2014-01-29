/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import mdb.events.WLCAutologinEvent;
import org.apache.log4j.Logger;
import session.LoginSessionBean;

/**
 *
 * @author bruno
 */
@MessageDriven(mappedName = "jms/WLCAutologinQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class WLCAutologinMDB implements MessageListener {

    private Logger logger = Logger.getLogger("wlcAutologinMDB");
    @EJB
    private LoginSessionBean loginSessionBean;

    public WLCAutologinMDB() {
    }

    @Override
    public void onMessage(Message message) {

        ObjectMessage msg;
        WLCAutologinEvent event;
        int reintentos = 0;
        boolean result = false;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                event = (WLCAutologinEvent) msg.getObject();
                if (event != null) {
                    //procesar WLCAutologin
                    while (reintentos < 5) {
                        result = procesarWLCAutologin(event);
                        if (result) {
                            //Salio todo bien
                            break;
                        } else {
                            Thread.sleep(1000L);
                        }
                    }
                    if (result) {
                        logger.info("Autologin success event: " + event + ". reintentos: " + reintentos + ".");
                    } else {
                        logger.warn("El evento " + event + " no se pudo cargar. reintentos: " + reintentos + ".");
                    }
//                    logger.info("Llego durmiendo..." + event);
//                    Thread.sleep(20000);
//                    logger.info("Fin, thread wake up. " + event);
                }
            } else {
                logger.warn("Message of wrong type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("ERROR en onMessage2. message: " + message + ".", e);
        } catch (Exception ex) {
            logger.error("ERROR en onMessage. message: " + message + ".", ex);
        }
    }

    private boolean procesarWLCAutologin(WLCAutologinEvent event) {
        try {
            return loginSessionBean.autoLoginLimitedByCPEIP(event.getCpeIP());
        } catch (Exception ex) {
            logger.error("ERROR en procesarWLCAutologin. event: " + event + ".", ex);
        }
        return false;
    }
}
