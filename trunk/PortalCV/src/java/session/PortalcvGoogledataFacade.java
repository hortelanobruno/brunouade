/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvFacebookdata;
import entity.PortalcvGoogledata;
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
public class PortalcvGoogledataFacade extends AbstractFacade<PortalcvGoogledata> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvGoogledataFacade() {
        super(PortalcvGoogledata.class);
    }

    public void saveGoogleData(Map<String, String> datos) {
        PortalcvGoogledata google = null;
        String email = null;
        try {
            email = datos.get("email");
            google = (PortalcvGoogledata) em.createNamedQuery("PortalcvGoogledata.findByEmail").setParameter("email", email).getSingleResult();
            //Existe, actualizar
            fillWithData(datos, google);
            em.merge(google);
            em.flush();
        } catch (NoResultException ex) {
            //Nuevo
            google = new PortalcvGoogledata();
            fillWithData(datos, google);
            em.persist(google);
            em.flush();
        } catch (Exception ex) {
            loggerError.error("Error en saveFacebookData. email: " + email, ex);
        }
    }

    private void fillWithData(Map<String, String> datos, PortalcvGoogledata google) {
//        INFO: {
//            "id": "111357066704787593407",
//            "email": "hortelanobruno@gmail.com",
//            "verified_email": true,
//            "name": "Bruno Hortelano",
//            "given_name": "Bruno",
//            "family_name": "Hortelano",
//            "link": "https://plus.google.com/111357066704787593407",
//            "picture": "https://lh6.googleusercontent.com/-4u8ej6vEuEM/AAAAAAAAAAI/AAAAAAAAAAA/c1qJhzP9498/photo.jpg",
//            "gender": "male",
//            "birthday": "0000-12-15",
//            "locale": "en"
//           }
        logger.debug("Print Google map...");
        for (String pbj : datos.keySet()) {
            logger.debug("key: " + pbj + " " + datos.get(pbj));
        }
        logger.debug("End Print Google map.");
        try {
            google.setBirthday(datos.get("birthday"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Birthday.", ex);
        }
        try {
            google.setEmail(datos.get("email"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Email.", ex);
        }
        try {
            google.setFamilyName(datos.get("family_name"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with FamilyName.", ex);
        }
        try {
            google.setGender(datos.get("gender"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Gender.", ex);
        }
        try {
            google.setGivenName(datos.get("given_name"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with GivenName.", ex);
        }
//        try {
//            google.setId(new BigInteger(datos.get("id")));
//        } catch (Exception ex) {
//            loggerError.error("Error en fillWithData with Id.", ex);
//        }
        try {
            google.setLink(datos.get("link"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Link.", ex);
        }
        try {
            google.setName(datos.get("name"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Name.", ex);
        }
        try {
            google.setPicture(datos.get("picture"));
        } catch (Exception ex) {
            loggerError.error("Error en fillWithData with Picture.", ex);
        }

    }
}
