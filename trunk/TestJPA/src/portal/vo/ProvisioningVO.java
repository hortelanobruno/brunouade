/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.vo;

/**
 *
 * @author brunoli
 */
public class ProvisioningVO {

    private Long id;
    private String email;
    private String clientId;
    private String cmMac;

    public ProvisioningVO() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCmMac() {
        return cmMac;
    }

    public void setCmMac(String cmMac) {
        this.cmMac = cmMac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
