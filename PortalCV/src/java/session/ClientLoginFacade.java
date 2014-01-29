/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ClientLogin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class ClientLoginFacade extends AbstractFacade<ClientLogin> {
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientLoginFacade() {
        super(ClientLogin.class);
    }
    
}
