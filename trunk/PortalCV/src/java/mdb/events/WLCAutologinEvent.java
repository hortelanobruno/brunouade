/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb.events;

import java.io.Serializable;

/**
 *
 * @author bruno
 */
public class WLCAutologinEvent implements Serializable {

    private String cpeIP;
    private String cpeMac;

    public WLCAutologinEvent() {
    }

    public String getCpeIP() {
        return cpeIP;
    }

    public void setCpeIP(String cpeIP) {
        this.cpeIP = cpeIP;
    }

    public String getCpeMac() {
        return cpeMac;
    }

    public void setCpeMac(String cpeMac) {
        this.cpeMac = cpeMac;
    }

    @Override
    public String toString() {
        return "WLCAutologinEvent{" + "cpeIP=" + cpeIP + ", cpeMac=" + cpeMac + '}';
    }
}
