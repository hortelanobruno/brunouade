/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Radacct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import radiuscoa.RadiusCoaResult;

/**
 *
 * @author bruno
 */
@Stateless
public class RadacctFacade extends AbstractFacade<Radacct> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RadacctFacade() {
        super(Radacct.class);
    }

    public RadiusCoaResult sessionValidFromISGAccounting(String username, String cpeIP) {
        //Radacct.findByFrameIPAndAcctStopTimeNull", query = "SELECT r.userName,r.ciscoServiceInfo,r.acctStartTime FROM Radacct r WHERE r.framedIPAddress = :framedIPAddress and r.ciscoServiceInfo not like '' and r.findByAcctStopTime is NULL"
        Object[] radacct = null;
        List lista;
        try {
            lista = (List) em.createNamedQuery("Radacct.findByFrameIPAndAcctStopTimeNull").setParameter("framedIPAddress", cpeIP).getResultList();
            if (!lista.isEmpty()) {
                for (Object o : lista) {
                    radacct = (Object[]) o;
                    if (((String) radacct[1]).contains("NREDIRECT_PORT80")) {
                        return RadiusCoaResult.FZONE_INTERNET_LIMITED;
                    }
                }
                return RadiusCoaResult.FZONE_INTERNET_FULL;
            }
            return RadiusCoaResult.FZONE_REDIRECT;
        } catch (NoResultException ex) {
            return RadiusCoaResult.FZONE_REDIRECT;
        } catch (Exception ex) {
            loggerError.error("Error en sessionValidFromISGAccounting. username: " + username + ". cpeIP: " + cpeIP + ".", ex);
        }
        return RadiusCoaResult.UNKNOWN;
    }
}
