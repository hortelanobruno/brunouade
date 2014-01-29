/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvLogin;
import entity.PortalcvUseragent;
import entity.PortalcvWlcaccounting;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Brunoli
 */
@Stateless
public class PortalcvLoginFacade extends AbstractFacade<PortalcvLogin> {
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvLoginFacade() {
        super(PortalcvLogin.class);
    }

    public void saveLoginData(String username, String cpeIp, PortalcvWlcaccounting wlcAcctData, PortalcvUseragent userAgentData, String loginType) {
        try{
            PortalcvLogin login = new PortalcvLogin();
            login.setUsername(username);
            login.setIpAddress(cpeIp);
            login.setLogintype(loginType);
            login.setTimestamp(new Date(System.currentTimeMillis()));
            if(wlcAcctData!=null){
                login.setCpemac(wlcAcctData.getMaccpe());
                login.setApsidAP(wlcAcctData.getIdAP());
            }
            if(userAgentData!=null){
                login.setUseragentsidUserAgent(userAgentData);
            }
            em.persist(login);
            em.flush();
        }catch(Exception ex){
            loggerError.error("Error en saveLoginData.", ex);
        }
    }
    
}
