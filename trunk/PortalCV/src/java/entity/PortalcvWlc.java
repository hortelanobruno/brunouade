/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_wlcs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvWlc.findAll", query = "SELECT p FROM PortalcvWlc p"),
    @NamedQuery(name = "PortalcvWlc.findByIdwlcs", query = "SELECT p FROM PortalcvWlc p WHERE p.idwlcs = :idwlcs"),
    @NamedQuery(name = "PortalcvWlc.findByName", query = "SELECT p FROM PortalcvWlc p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvWlc.findByIp", query = "SELECT p FROM PortalcvWlc p WHERE p.ip = :ip"),
    @NamedQuery(name = "PortalcvWlc.findBySnmpPort", query = "SELECT p FROM PortalcvWlc p WHERE p.snmpPort = :snmpPort"),
    @NamedQuery(name = "PortalcvWlc.findBySnmpComunity", query = "SELECT p FROM PortalcvWlc p WHERE p.snmpComunity = :snmpComunity")})
public class PortalcvWlc implements Serializable {
    @Size(max = 45)
    @Column(name = "secret")
    private String secret;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idwlcs")
    private Integer idwlcs;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Size(max = 45)
    @Column(name = "snmpPort")
    private String snmpPort;
    @Size(max = 45)
    @Column(name = "snmpComunity")
    private String snmpComunity;

    public PortalcvWlc() {
    }

    public PortalcvWlc(Integer idwlcs) {
        this.idwlcs = idwlcs;
    }

    public Integer getIdwlcs() {
        return idwlcs;
    }

    public void setIdwlcs(Integer idwlcs) {
        this.idwlcs = idwlcs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSnmpPort() {
        return snmpPort;
    }

    public void setSnmpPort(String snmpPort) {
        this.snmpPort = snmpPort;
    }

    public String getSnmpComunity() {
        return snmpComunity;
    }

    public void setSnmpComunity(String snmpComunity) {
        this.snmpComunity = snmpComunity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idwlcs != null ? idwlcs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvWlc)) {
            return false;
        }
        PortalcvWlc other = (PortalcvWlc) object;
        if ((this.idwlcs == null && other.idwlcs != null) || (this.idwlcs != null && !this.idwlcs.equals(other.idwlcs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvWlc[ idwlcs=" + idwlcs + " ]";
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    
}
