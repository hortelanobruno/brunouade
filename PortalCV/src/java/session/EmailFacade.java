/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Email;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class EmailFacade extends AbstractFacade<Email> {
    
    @PersistenceContext(unitName = "PortalCVPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EmailFacade() {
        super(Email.class);
    }
    
    public Email getEmailInfo() {
        return this.find(0);
    }
    
    public void updateEmail(String title, String content, String emailFrom) {
        try {
            Email email = this.find(0);
            if(email == null){
                email = new Email();
                email.setIdemail(0);
            }
            email.setTitle(title);
            email.setContent(content);
            email.setFromMail(emailFrom);
            em.persist(email);
            em.flush();
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }
}
