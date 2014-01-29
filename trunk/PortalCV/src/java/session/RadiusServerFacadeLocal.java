/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;

/**
 *
 * @author bruno
 */
@Remote
public interface RadiusServerFacadeLocal {

    public void acctStartWLC(String cpeIP, String cpeMAC, String apMAC, String sessionId);

    public void acctAliveWLC(String cpeIP, String cpeMAC, String apMAC, String sessionId);

    public void acctStopWLC(String cpeIP, String cpeMAC, String apMAC, String sessionId);
}
