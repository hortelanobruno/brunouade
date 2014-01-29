/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvFacebookdata;
import java.math.BigInteger;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvFacebookdataFacade extends AbstractFacade<PortalcvFacebookdata> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvFacebookdataFacade() {
        super(PortalcvFacebookdata.class);
    }

    public void saveFacebookData(Map parameterMap) {
        PortalcvFacebookdata facebook = null;
        String email = null;
        try {
            email = getValue("email", parameterMap);
            facebook = (PortalcvFacebookdata) em.createNamedQuery("PortalcvFacebookdata.findByEmail").setParameter("email", email).getSingleResult();
            //Existe, actualizar
            fillWithData(parameterMap, facebook);
            em.merge(facebook);
            em.flush();
        } catch (NoResultException ex) {
            //Nuevo
            facebook = new PortalcvFacebookdata();
            fillWithData(parameterMap, facebook);
            em.persist(facebook);
            em.flush();
        } catch (Exception ex) {
            loggerError.error("Error en saveFacebookData. email: " + email, ex);
        }
    }

    public static void main(String args[]) {
        String a = "100001622791088";
        System.out.println(new BigInteger(a));
    }

    private void fillWithData(Map parameterMap, PortalcvFacebookdata facebook) {
        logger.debug("Print Facebook map...");
        for (Object pbj : parameterMap.keySet()) {
            logger.debug("key: " + pbj + " " + ((String[]) parameterMap.get(pbj))[0]);
        }
        logger.debug("End Print Facebook map.");
        try {
            facebook.setId(new BigInteger(getValue("id", parameterMap)));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Id.", ex);
        }
        try {
            facebook.setName(getValue("name", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Name.", ex);
        }
        try {
            facebook.setGender(getValue("gender", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Gender.", ex);
        }
        try {
            facebook.setLink(getValue("link", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Link.", ex);
        }
        try {
            facebook.setAgeRange(getValue("age_range[min]", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with AgeRange.", ex);
        }
        try {
            facebook.setBirthday(getValue("birthday", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Birthday.", ex);
        }
        try {
            facebook.setEmail(getValue("email", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Email.", ex);
        }
        try {
            facebook.setHometown(getValue("hometown", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Hometown.", ex);
        }
        try {
            facebook.setLocation(getValue("location", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Location.", ex);
        }
        try {
            facebook.setFirstName(getValue("first_name", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with FirstName.", ex);
        }
        try {
            facebook.setLastName(getValue("last_name", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with LastName.", ex);
        }
        try {
            facebook.setPicture(getValue("picture[data][url]", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Picture.", ex);
        }
        try {
            facebook.setAddress(getValue("address", parameterMap));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Picture.", ex);
        }
    }

    private String getValue(String param, Map parameterMap) {
        Object value = parameterMap.get(param);
        if (value != null) {
            return ((String[]) value)[0].toString();
        }
        //otro chequeo
        for (Object pbj : parameterMap.keySet()) {
            if (pbj.toString().trim().equals(param.trim())) {
                return ((String[]) parameterMap.get(pbj))[0];
            }
        }
        return null;
    }
}
