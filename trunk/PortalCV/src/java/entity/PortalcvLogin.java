/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_logins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvLogin.findAll", query = "SELECT p FROM PortalcvLogin p"),
    @NamedQuery(name = "PortalcvLogin.findByIdlogins", query = "SELECT p FROM PortalcvLogin p WHERE p.idlogins = :idlogins"),
    @NamedQuery(name = "PortalcvLogin.findByUsername", query = "SELECT p FROM PortalcvLogin p WHERE p.username = :username"),
    @NamedQuery(name = "PortalcvLogin.findByIpAddress", query = "SELECT p FROM PortalcvLogin p WHERE p.ipAddress = :ipAddress"),
    @NamedQuery(name = "PortalcvLogin.findByCpemac", query = "SELECT p FROM PortalcvLogin p WHERE p.cpemac = :cpemac"),
    @NamedQuery(name = "PortalcvLogin.findByTimestamp", query = "SELECT p FROM PortalcvLogin p WHERE p.timestamp = :timestamp"),
    @NamedQuery(name = "PortalcvLogin.findByLogintype", query = "SELECT p FROM PortalcvLogin p WHERE p.logintype = :logintype")})
public class PortalcvLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogins")
    private Integer idlogins;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "ipAddress")
    private String ipAddress;
    @Size(max = 45)
    @Column(name = "CPEMAC")
    private String cpemac;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "logintype")
    private String logintype;
    @JoinColumn(name = "aps_idAP", referencedColumnName = "idAP")
    @ManyToOne
    private PortalcvAp apsidAP;
    @JoinColumn(name = "useragents_idUserAgent", referencedColumnName = "idUserAgent")
    @ManyToOne
    private PortalcvUseragent useragentsidUserAgent;

    public PortalcvLogin() {
    }

    public PortalcvLogin(Integer idlogins) {
        this.idlogins = idlogins;
    }

    public Integer getIdlogins() {
        return idlogins;
    }

    public void setIdlogins(Integer idlogins) {
        this.idlogins = idlogins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCpemac() {
        return cpemac;
    }

    public void setCpemac(String cpemac) {
        this.cpemac = cpemac;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public PortalcvAp getApsidAP() {
        return apsidAP;
    }

    public void setApsidAP(PortalcvAp apsidAP) {
        this.apsidAP = apsidAP;
    }

    public PortalcvUseragent getUseragentsidUserAgent() {
        return useragentsidUserAgent;
    }

    public void setUseragentsidUserAgent(PortalcvUseragent useragentsidUserAgent) {
        this.useragentsidUserAgent = useragentsidUserAgent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogins != null ? idlogins.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvLogin)) {
            return false;
        }
        PortalcvLogin other = (PortalcvLogin) object;
        if ((this.idlogins == null && other.idlogins != null) || (this.idlogins != null && !this.idlogins.equals(other.idlogins))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvLogin[ idlogins=" + idlogins + " ]";
    }
}
