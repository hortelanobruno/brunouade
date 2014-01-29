/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PortalcvGoogledata;
import entity.PortalcvTwitterdata;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import twitter4j.User;

/**
 *
 * @author bruno
 */
@Stateless
public class PortalcvTwitterdataFacade extends AbstractFacade<PortalcvTwitterdata> {

    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortalcvTwitterdataFacade() {
        super(PortalcvTwitterdata.class);
    }

    public void saveTwitterData(User userTwitter) {
        PortalcvTwitterdata twitter = null;
        BigInteger id = null;
        try {
            id = new BigInteger("" + userTwitter.getId());
            twitter = (PortalcvTwitterdata) em.createNamedQuery("PortalcvTwitterdata.findById").setParameter("id", id).getSingleResult();
            //Existe, actualizar
            fillWithData(userTwitter, twitter);
            em.merge(twitter);
            em.flush();
        } catch (NoResultException ex) {
            //Nuevo
            twitter = new PortalcvTwitterdata();
            fillWithData(userTwitter, twitter);
            em.persist(twitter);
            em.flush();
        } catch (Exception ex) {
            loggerError.error("Error en saveFacebookData. id: " + id, ex);
        }
    }

    private void fillWithData(User userTwitter, PortalcvTwitterdata twitter) {
        twitter.setFollowersCount(userTwitter.getFollowersCount());
        twitter.setId(new BigInteger("" + userTwitter.getId()));
        twitter.setLocation(userTwitter.getLocation());
        twitter.setName(userTwitter.getName());
        twitter.setProfileImageUrl(userTwitter.getProfileImageURL());
        twitter.setScreenName(userTwitter.getScreenName());
        twitter.setUrl(userTwitter.getURL());
    }
}
